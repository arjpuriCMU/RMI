package RMIServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import Messages.ServerResponse;

class RMICommunicator implements Runnable{
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	
	RMICommunicator(RMIServer master, Socket socket){
		this.socket = socket;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public void setId(int id){
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
				input.readObject();
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