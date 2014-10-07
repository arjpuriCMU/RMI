package Client;

import Messages.MethodCallMessage;
import Messages.MethodReturnMessage;
import Messages.RegistryJobMessage;
import Registry.RemoteObjectReference;
import util.Job;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by karansharma on 10/7/14.
 */
public class Client {
    public static Object lookup(String hostname, int port, String object_id) throws IOException, ClassNotFoundException {
        //Create socket connection to server, send marshalled packet, and for returned value
        Socket s = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        RegistryJobMessage toServer = new RegistryJobMessage(Job.LOOKUP,null,object_id);
        RegistryJobMessage fromServer = null;
        try {
            s = new Socket(hostname, port);
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);
            in = new ObjectInputStream(s.getInputStream());
            fromServer = (RegistryJobMessage) in.readObject();
            in.close();
            out.close();
            s.close();
            RemoteObjectReference ror = fromServer.getRef();
            if(ror == null)
                return null;
            else
                return ror.getStub();

        } catch (Exception e) {
            throw e;
        }
    }
}
