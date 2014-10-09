package Registry;

import Messages.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteHandler implements InvocationHandler {

    private String hostname;
	private int port;
    private String interface_name;
	private String object_id;

	public RemoteHandler(String hostname, int port, String interface_name, String object_id) {
		this.hostname = hostname;
		this.port = port;
		this.interface_name = interface_name;
        this.object_id = object_id;
	}

    /* Invoke function is called anytime a method is called on a proxy stub */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

        //Marshalls method call
        MethodCallMessage toServer = new MethodCallMessage(object_id,method,args);

        try {
            /* Establish Connection and sends marshalled call to Server */
            Socket s = new Socket(this.hostname, this.port);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);

            /* Gets return_value packet from Server */
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            MethodReturnMessage fromServer = (MethodReturnMessage) in.readObject();

            /* Close connection */
            in.close();
            out.close();
            s.close();

            /* Return return_value */
            return fromServer.getRet();

        } catch (Exception e) {
            /* Forward Exception to Client */
            throw e;
        }
	}

    public String getHostname() {return  this.hostname;}

    public int getPort() {return  this.port;}

    public String getInterface_Name() {return this.interface_name;}

    public String getObject_id()
    {
        return this.object_id;
    }




}
