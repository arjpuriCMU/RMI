package Client;

import Example.EchoObject;
import Messages.MethodCallMessage;
import Messages.MethodReturnMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by karansharma on 10/7/14.
 */
public class EchoClient extends Client{
    public static void main(String args[]) {
        //Make sure server connection information provided
        if (args.length != 2) {
            System.out.println("Invalid Arguments: Provide Server Host and Port");
            return;
        }

        String serverHost = args[0];
        int serverPort = Integer.parseInt(args[1]);

        EchoObject echo1 = (EchoObject) Client.lookup(serverHost, serverPort, "EchoClient1");
        System.out.println(echo1.echoCombineMessage("TESTING", "testing"));

    }

}
