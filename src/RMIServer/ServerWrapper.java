package RMIServer;

import java.util.Arrays;

import Example.EchoObject;

public class ServerWrapper {
	private static RMIServer rmi_server = new RMIServer();
	
	public static void main(String[] args){
		System.out.println("Starting Server...");
		EchoObject echo1 = new EchoObject();
		EchoObject echo2 = new EchoObject();
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		rmi_server.getRMIRegistry().bind("EchoObject1", echo1);
		rmi_server.getRMIRegistry().bind("EchoObject2", echo2);
		System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		//rmi_server.getRMIRegistry().unbind("EchoObject1");
		//System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		//rmi_server.getRMIRegistry().rebind("EchoObject1", echo1);
		//System.out.println(Arrays.toString(rmi_server.getRMIRegistry().list()));
		rmi_server.start();
	}
	
	

}
