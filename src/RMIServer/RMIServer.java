package RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.*;
import java.util.concurrent.ConcurrentHashMap;

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
	private ConcurrentHashMap<Integer,RMICommunicator> communicator_cache;

	public RMIServer()
    {
        try {
            this.hostname = InetAddress.getLocalHost().getHostName();
            server_socket = new ServerSocket(0);
            this.port = server_socket.getLocalPort();
        } catch (Exception e) {
            System.out.println("MASTER SERVER: Get Local Host and Port Failed");
            return;
        }

        System.out.println("Server Host: " + hostname);
        System.out.println("Server Port: " + port);

		this.communicator_cache = new ConcurrentHashMap<Integer,RMICommunicator>();
		registry = new RMIRegistry(hostname,port);
	}
	public void start() {
		Thread RMIRegistryThread = new Thread(registry);
		RMIRegistryThread.start();
		while(true){
			try {
				Socket socket = server_socket.accept();
				ObjectInputStream input_stream = new ObjectInputStream(socket.getInputStream());
				Object message = input_stream.readObject();
                ObjectOutputStream output_stream = new ObjectOutputStream(socket.getOutputStream());
                MethodReturnMessage msg = null;
				if (message instanceof RegistryJobMessage){
					RegistryJobMessage job_message = (RegistryJobMessage) message;
					switch (job_message.getJob()){
					
					case LOOKUP:
						Group<RemoteObjectReference,Object> object_group = registry.lookup(job_message.getObjectId());
                        if(object_group == null)
                            output_stream.writeObject(job_message);
                        else {
                            job_message.setRef(object_group.getFirstObj());
                            output_stream.writeObject(job_message);
                        }
                        break;
					case LIST:
						String[] list = registry.list();
						if (list == null){
							msg = new MethodReturnMessage(null, true, "No Objects Returned");
						}
						else{
							msg = new MethodReturnMessage(list, false, null);
						}
						
					default:
						break;
						
					
					}
				}
                else if(message instanceof MethodCallMessage)
                {
                    /* Un-marshall method call message, execute method call, and marshall/send return value */
                    MethodCallMessage call = (MethodCallMessage) message;
                    try {
                        Object localObj = this.registry.lookup(call.object_id).getSecondObj();
                        for (int i = 0; i < call.arg_types.length; i++) {
                            if(call.arg_types[i] == RemoteObjectReference.class) //argument is a ROR
                            {
                                RemoteObjectReference ror = (RemoteObjectReference) call.args[i];
                                call.args[i] = this.registry.lookup(ror.getObject_id()).getSecondObj();
                                call.arg_types[i] = Class.forName(ror.getInterfaceName());
                            }
                        }

                        Method method = localObj.getClass().getMethod(call.method, call.arg_types);
                        Object return_value = method.invoke(localObj, call.args);
                        output_stream.writeObject(new MethodReturnMessage(return_value,false,""));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        output_stream.writeObject(new MethodReturnMessage(null,true,e.getMessage()));
                        continue;
                    }
                }
                socket.close();
                input_stream.close();
                output_stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
	}
	
	public int getPort() {
		return port;
	}
	
	public ConcurrentHashMap<Integer,RMICommunicator> getCommunicatorCache(){
		return this.communicator_cache;
	}
	
	public RMIRegistry getRMIRegistry(){
		return this.registry;
	}

}

class Connector implements Runnable {
	/*Listens for incoming connections from worker servers
	 */
	private RMIServer master;
	private ServerSocket server_socket;
	private Socket client_socket;
	Connector(RMIServer master){
		this.master = master;
		
	}
	
	public void setClosed(boolean b) {
		
	}

	public Socket getSocket(){
		return client_socket;
	}

	@Override
	public void run() {
		try {
			server_socket = new ServerSocket(master.getPort());
			while(true){
				client_socket = server_socket.accept();
				System.out.println("Server socket opened on port: " + server_socket.getLocalPort());
				System.out.print("Master -> ");
				RMICommunicator comm = new RMICommunicator(master,client_socket);
				Thread th = new Thread(comm);
				master.getCommunicatorCache().put(this.master.getCommunicatorCache().size(), comm);
				comm.setId(this.master.getCommunicatorCache().size());
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
		}
	}
	
	public ServerSocket getServerSocket(){
		return this.server_socket;
	}


}