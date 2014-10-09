package RMIServer;

import java.util.Arrays;

import Example.EchoObject;
import Example.Integer440Object;
import Example.nthPrimeObject;

public class ServerWrapper {
	private static RMIServer rmi_server = new RMIServer();
	
	public static void main(String[] args){

        /* Test Server Application Using RMIServer */
		System.out.println("Starting Server...");

        /* Create Objects */
		EchoObject echo1 = new EchoObject("hello");
		EchoObject echo2 = new EchoObject("jojo");
        nthPrimeObject nth1 = new nthPrimeObject();
        nthPrimeObject nth2 = new nthPrimeObject();
        Integer440Object int1 = new Integer440Object(5);
        Integer440Object int2 = new Integer440Object(10);
        System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));

        /* Bind EchoObjects */
		rmi_server.getRMIRegistry().bind("EchoObject1", echo1);
		rmi_server.getRMIRegistry().bind("EchoObject2", echo2);
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));

        /* Test Unbind/Rebind */
		rmi_server.getRMIRegistry().unbind("EchoObject1");
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		rmi_server.getRMIRegistry().rebind("EchoObject1", echo1);
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));


        /* Bind remaining objects */
        rmi_server.getRMIRegistry().bind("nthPrimeObject1", nth1);
        rmi_server.getRMIRegistry().bind("nthPrimeObject2", nth2);
        System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));

        rmi_server.getRMIRegistry().bind("Integer440Object1", int1);
        rmi_server.getRMIRegistry().bind("Integer440Object2", int2);
        System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));

        /* Begin Server - start listening for client messages */
		rmi_server.start();
	}
	
	

}
