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
    public static Object lookup(String hostname, int port, String object_id)
    {
        //Create socket connection to server, send marshalled packet, and for returned value
        Socket s = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        RegistryJobMessage toServer = new RegistryJobMessage(Job.LOOKUP,null,object_id);
        RegistryJobMessage fromServer = null;
        boolean fail = false;
        try {
            s = new Socket(hostname, port);
            out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);
            in = new ObjectInputStream(s.getInputStream());
            fromServer = (RegistryJobMessage) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                fail = true;
            }
        }
        if(!fail)
            try {
                return fromServer.getRef().getStub();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        return null;
    }


}
