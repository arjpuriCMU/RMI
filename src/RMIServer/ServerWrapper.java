package RMIServer;

import java.util.Arrays;

import Example.EchoObject;
import Example.nthPrimeObject;

public class ServerWrapper {
	private static RMIServer rmi_server = new RMIServer();
	
	public static void main(String[] args){
		System.out.println("Starting Server...");
		EchoObject echo1 = new EchoObject("hello");
		EchoObject echo2 = new EchoObject("jojo");
        nthPrimeObject nth1 = new nthPrimeObject();
        nthPrimeObject nth2 = new nthPrimeObject();
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		rmi_server.getRMIRegistry().bind("EchoObject1", echo1);
		rmi_server.getRMIRegistry().bind("EchoObject2", echo2);
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		//rmi_server.getRMIRegistry().unbind("EchoObject1");
		//System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		//rmi_server.getRMIRegistry().rebind("EchoObject1", echo1);
		//System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
        rmi_server.getRMIRegistry().bind("nthPrimeObject1", nth1);
        rmi_server.getRMIRegistry().bind("nthPrimeObject2", nth2);
        System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		rmi_server.start();
	}
	
	

}
