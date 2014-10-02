package RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import Messages.ServerResponse;

class RMICommunicator implements Runnable{
	/* Each worker has its own communicator to allow transfer of messages
	 */
	private int id;
	private Socket socket;
	private RMIServer rmi_server;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	RMICommunicator(RMIServer master, Socket socket){
		this.rmi_server= master ;
		this.socket = socket;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public void setId(int id){
		this.id = id;
	}
	public ObjectInputStream getInput(){
		return input;
	}
	
	public ObjectOutputStream getOutput(){
		return output;
	}
	
	
	@Override
	public void run() {
		try {
			while(true){
				if (input == null){
					input = new ObjectInputStream(socket.getInputStream());}
				Object read_value =   input.readObject();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			
		}
	}
	
	public void pushMessageToWorker(ServerResponse msg) throws IOException, SocketException {
		if (output == null){
			output = new ObjectOutputStream(socket.getOutputStream());
		}
		output.writeObject(msg);
		output.flush();
	}
}