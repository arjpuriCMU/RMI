package Client;

import Example.EchoInterface;

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

        EchoInterface echo1 = null;
        EchoInterface echo2 = null;
        try {
            echo1 = (EchoInterface) Client.lookup(serverHost, serverPort, "EchoObject1");
            System.out.println("EchoObject1 Stub Acquired");
            String result = echo1.echoCombineMessage("TESTING", "testing");
            System.out.println("Result: " + result);
            echo2 = (EchoInterface) Client.lookup(serverHost, serverPort, "EchoObject2");
            System.out.println("EchoObject2 Stub Acquired");
            String return_string = echo2.combineWithEchoObject(echo1);
            System.out.println("Result:" + return_string);
        } catch (Exception e) {
            System.out.println("Error in Lookup");
            e.printStackTrace();
        }

    }

}
