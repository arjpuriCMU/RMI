package Client;

import Example.EchoObjectInterface;
import Example.EchoObject;
import Messages.MethodCallMessage;
import Messages.MethodReturnMessage;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by karansharma on 10/7/14.
 */
public class EchoClient extends Client {
    public static void main(String args[]) {
        //Make sure server connection information provided
        if (args.length != 2) {
            System.out.println("Invalid Arguments: Provide Server Host and Port");
            return;
        }

        String serverHost = args[0];
        int serverPort = Integer.parseInt(args[1]);

        System.out.println("Within Client");

        EchoObjectInterface echo1 = null;
        try {
            echo1 = (EchoObjectInterface) Client.lookup(serverHost, serverPort, "EchoObject1");
            System.out.println("Stub Acquired");
            String result = echo1.echoCombineMessage("TESTING", "testing");
            System.out.println("Result: " + result);
        } catch (Exception e) {
            System.out.print("Error in Lookup");
            e.printStackTrace();
        }

    }

}
