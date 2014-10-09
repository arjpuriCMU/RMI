package Client;

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

    /* Wraps Registry lookup for clients */
    public static Object lookup(String hostname, int port, String object_id) throws Exception {
        /* Marshalls lookup */
        RegistryJobMessage toServer = new RegistryJobMessage(Job.LOOKUP, object_id);
        try {
            /* Establish Connection and sends marshalled call to Server */
            Socket s = new Socket(hostname, port);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);

            /* Gets job response packet from Server */
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            RegistryJobMessage fromServer = (RegistryJobMessage) in.readObject();

            /* Close connection */
            in.close();
            out.close();
            s.close();

            /* Return return_value */
            return fromServer.getRef().getStub();

        } catch (Exception e) {
            /* Forward Exception to Client */
            throw e;
        }
    }

    /* Wraps Registry list function for clients */
    public static String[] list(String hostname, int port) throws Exception {
        /* Marshalls list call */
        RegistryJobMessage toServer = new RegistryJobMessage(Job.LIST, null);
        try {
            /* Establish Connection and sends marshalled call to Server */
            Socket s = new Socket(hostname, port);
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            out.writeObject(toServer);

            /* Gets job response packet from Server */
            ObjectInputStream in = new ObjectInputStream(s.getInputStream());
            RegistryJobMessage fromServer = (RegistryJobMessage) in.readObject();

            /* Close connection */
            in.close();
            out.close();
            s.close();

            /* Return return_value */
            return fromServer.getList();

        } catch (Exception e) {
            /* Forward Exception to Client */
            throw e;
        }
    }
}
