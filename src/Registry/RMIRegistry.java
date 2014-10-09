package Registry;

import java.util.Arrays;
import java.util.HashMap;

import util.Group;

public class RMIRegistry implements Runnable {


	private HashMap<String, Group<RemoteObjectReference,Object>> remote_objects;
    private String hostname;
    private int port;
	
	public RMIRegistry(String hostname, int port)
    {
        /* Creates hashmap from object_id to (ROR,Object) pair */
		this.remote_objects = new HashMap<String,Group<RemoteObjectReference,Object>>();
        this.hostname = hostname;
        this.port = port;
	}

	public void run(){}

    /* Adds given mapping to hashmap */
    public void bind(String object_id, Object obj)
    {
        String interface_name = obj.getClass().getName().replace("Object","Interface");
        RemoteObjectReference ror = new RemoteObjectReference(this.hostname,this.port,interface_name,object_id);
        this.remote_objects.put(object_id, new Group<RemoteObjectReference, Object>(ror,obj));
    }

    /* Returns string array of all object_id's in the hashmap */
    public String[] list()
    {
    	Object[] arr = this.remote_objects.keySet().toArray();
    	String[] stringArray = Arrays.copyOf(arr, arr.length, String[].class);
        return stringArray;
    }

    /* Returns (ROR,Object) tuple for given object_id, if it exists */
    public Group<RemoteObjectReference,Object> lookup(String object_id)
    {
        return this.remote_objects.get(object_id);
    }

    /* Changes mapping of given object_id from existing mapping to given object */
    public void rebind(String object_id, Object obj)
    {
        unbind(object_id);
        bind(object_id,obj);
    }

    /* Removes mapping of given object_id */
    public void unbind(String object_id)
    {
        this.remote_objects.remove(object_id);
    }
}
