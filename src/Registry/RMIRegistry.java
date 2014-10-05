package Registry;

import java.util.HashMap;

import util.Group;

public class RMIRegistry implements Runnable {
	private HashMap<String, Group<RemoteObjectReference,Object>> remote_objects;
	
	public RMIRegistry(){
		this.remote_objects = new HashMap<String,Group<RemoteObjectReference,Object>>();
	}
	
	public void run(){
		
	}
	
	
	
	
}
