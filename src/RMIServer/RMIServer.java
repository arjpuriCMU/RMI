package RMIServer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.*;

import Messages.MethodCallMessage;
import Messages.MethodReturnMessage;
import util.Group;
import Messages.RegistryJobMessage;
import Registry.RMIRegistry;
import Registry.RemoteObjectReference;


public class RMIServer {
    private String hostname;
	private int port;
	private ServerSocket server_socket;
	private RMIRegistry registry;

	public RMIServer()
    {
        /* Looks up and prints local hostname and port */
        try {

            this.hostname = InetAddress.getLocalHost().getHostName();
            server_socket = new ServerSocket(0);
            this.port = server_socket.getLocalPort();
            System.out.println("Server Host: " + hostname);
            System.out.println("Server Port: " + port);
        } catch (Exception e) {
            System.out.println("SERVER: Get Local Host and Port Failed");
            return;
        }

        /* Construct Registry */
		registry = new RMIRegistry(hostname,port);
	}
	public void start() {
        /* Launches Registry in Thread */
		Thread RMIRegistryThread = new Thread(registry);
		RMIRegistryThread.start();

        /* Loops for each socket connection */
		while(true){
			try
            {
                /* Makes connection and reads in message from client, which is one of two types */
				Socket socket = server_socket.accept();
				ObjectInputStream input_stream = new ObjectInputStream(socket.getInputStream());
				Object message = input_stream.readObject();
                ObjectOutputStream output_stream = new ObjectOutputStream(socket.getOutputStream());
				if (message instanceof RegistryJobMessage)  //message is a Registry Job Message
                {
					RegistryJobMessage job_message = (RegistryJobMessage) message;
                    try {
                        switch (job_message.getJob()) //Case on the type of Job being requested (Lookup or List)
                        {
                            case LOOKUP:
                                /* Marshall ROR corresponding to given object_id by looking up registry */
                                RemoteObjectReference ror = this.registry.lookup(job_message.getObjectId()).getFirstObj();
                                job_message.setRef(ror);
                                break;

                            case LIST:
                                /* Marshall list of objects within registry */
                                job_message.setList(this.registry.list());
                                break;

                            default:
                                throw new Exception("Invalid Job Requested");
                        }
                        /* Send back message */
                        output_stream.writeObject(job_message);
                    }
                    catch (Exception e)
                    {
                        /* Print local stack trace on Server then marshall exception and send message */
                        e.printStackTrace();
                        job_message.sendException(e.getMessage());
                        output_stream.writeObject(job_message);
                        continue;
                    }
				}
                else if(message instanceof MethodCallMessage)   //message is a Method Call Message
                {
                    /* Un-marshall method call message, execute method call, and marshall/send return value */
                    MethodCallMessage call = (MethodCallMessage) message;
                    try {
                        Object localObj = this.registry.lookup(call.object_id).getSecondObj();

                        /* Replace all ROR parameters to their local object equivalent */
                        for (int i = 0; i < call.arg_types.length; i++)
                        {
                            if(call.arg_types[i] == RemoteObjectReference.class) //argument is a ROR
                            {
                                RemoteObjectReference ror = (RemoteObjectReference) call.args[i];
                                call.args[i] = this.registry.lookup(ror.getObject_id()).getSecondObj();
                                call.arg_types[i] = Class.forName(ror.getInterfaceName());
                            }
                        }

                        /* Get method on local object and invoke it */
                        Method method = localObj.getClass().getMethod(call.method, call.arg_types);
                        Object return_value = method.invoke(localObj, call.args);

                        /* Marshall and return return_value */
                        output_stream.writeObject(new MethodReturnMessage(return_value,false,""));
                    }
                    catch (Exception e)
                    {
                        /* Print local stack trace on Server then marshall exception and send message */
                        e.printStackTrace();
                        output_stream.writeObject(new MethodReturnMessage(null,true,e.getMessage()));
                        continue;
                    }
                }
                /* Close connection */
                socket.close();
                input_stream.close();
                output_stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
	}
	
	public int getPort() {
		return port;
	}

	
	public RMIRegistry getRMIRegistry(){
		return this.registry;
	}

}
