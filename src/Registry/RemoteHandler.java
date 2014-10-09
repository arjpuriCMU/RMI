package Registry;

import Messages.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RemoteHandler implements InvocationHandler {
	private String object_id;
	private int port;
	private String hostname;
	public RemoteHandler(String object_id, int port, String hostname) {
		this.object_id = object_id;
		this.port = port;
		this.hostname = hostname;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {

		// TODO Auto-generated method stub
        //Create socket connection to server, send marshalled packet, and for returned value
        Socket s = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        MethodCallMessage toServer = new MethodCallMessage(object_id,method,args);
        MethodReturnMessage fromServer = null;
        try {
            s = new Socket(this.hostname, this.port);
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);
            in = new ObjectInputStream(s.getInputStream());
            fromServer = (MethodReturnMessage) in.readObject();
            in.close();
            out.close();
            s.close();
            return fromServer.getRet();

        } catch (Exception e) {
            throw e;
        }
	}

    public String getObject_id()
    {
        return this.object_id;
    }


}
