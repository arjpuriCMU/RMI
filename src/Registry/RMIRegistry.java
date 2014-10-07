package Registry;

import java.rmi.Remote;
import java.util.Arrays;
import java.util.HashMap;

import util.Group;

public class RMIRegistry implements Runnable {


	private HashMap<String, Group<RemoteObjectReference,Object>> remote_objects;
    private String hostname;
    private int port;
	
	public RMIRegistry(String hostname, int port)
    {
		this.remote_objects = new HashMap<String,Group<RemoteObjectReference,Object>>();
        this.hostname = hostname;
        this.port = port;
	}

	public void run(){
		
	}

    public void bind(String object_id, Object obj)
    {
        String interface_name = "";
        RemoteObjectReference ror = new RemoteObjectReference(this.hostname,this.port,interface_name);
        this.remote_objects.put(object_id, new Group(ror,obj));
    }

    public String[] list()
    {
    	Object[] arr = this.remote_objects.keySet().toArray();
    	String[] stringArray = Arrays.copyOf(arr, arr.length, String[].class);
        return stringArray;
    }

    public Group<RemoteObjectReference,Object> lookup(String object_id)
    {
        return this.remote_objects.get(object_id);
    }

    public void rebind(String object_id, Object obj)
    {
        unbind(object_id);
        bind(object_id,obj);
    }

    public void unbind(String object_id)
    {
        this.remote_objects.remove(object_id);
    }
	
	
	
	
}
