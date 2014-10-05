package Registry;

import java.util.HashMap;

import util.Group;

public class RMIRegistry {
	private HashMap<String, Group<RemoteObjectReference,Object>> remote_objects;
	private int port;
	
	
	
	public RMIRegistry(int port){
		this.remote_objects = new HashMap<String,Group<RemoteObjectReference,Object>>();
		this.port = port;
	}
	
	
	
	
}
