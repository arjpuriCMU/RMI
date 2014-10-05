package RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;
import java.util.concurrent.ConcurrentHashMap;

import Messages.RegistryJobMessage;
import Registry.RMIRegistry;


public class RMIServer {
	private int port_number;
	private ServerSocket server_socket;
	private Connector connection_manager;
	private RMIRegistry registry;
	private ConcurrentHashMap<Integer,RMICommunicator> communicator_cache; 
	public RMIServer(int port){
		this.port_number = port_number;
		this.communicator_cache = new ConcurrentHashMap<Integer,RMICommunicator>();
		registry = new RMIRegistry();
	}
	public void start() {
		connection_manager = new Connector(this);
		Thread connector = new Thread(connection_manager);
		Thread RMIRegistry = new Thread(registry);
		connector.start();
		RMIRegistry.start();
		try {
			server_socket = new ServerSocket(port_number);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		while(true){
			try {
				Socket socket = server_socket.accept();
				ObjectInputStream input_stream = new ObjectInputStream(socket.getInputStream());
				Object message = input_stream.readObject();
				if (message instanceof RegistryJobMessage){
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public int getPort() {
		return port_number;
	}
	
	public ConcurrentHashMap<Integer,RMICommunicator> getCommunicatorCache(){
		return this.communicator_cache;
	}

}

class Connector implements Runnable {
	/*Listens for incoming connections from worker servers
	 */
	private RMIServer master;
	private ServerSocket server_socket;
	private Socket client_socket;
	private boolean isClosed;
	
	Connector(RMIServer master){
		this.isClosed = false;
		this.master = master;
		
	}
	
	public void setClosed(boolean b) {
		this.isClosed = b;
		
	}

	public Socket getSocket(){
		return client_socket;
	}

	@Override
	public void run() {
		try {
			server_socket = new ServerSocket(master.getPort());
			while(true){
				client_socket = server_socket.accept();
				System.out.println("Server socket opened on port: " + server_socket.getLocalPort());
				System.out.print("Master -> ");
				RMICommunicator comm = new RMICommunicator(master,client_socket);
				Thread th = new Thread(comm);
				master.getCommunicatorCache().put(this.master.getCommunicatorCache().size(), comm);
				comm.setId(this.master.getCommunicatorCache().size());
				th.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
		}
		
	}
	
	public ServerSocket getServerSocket(){
		return this.server_socket;
	}
	

}