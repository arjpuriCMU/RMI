package Registry;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RemoteObjectReference implements Serializable {
	
	private static final long serialVersionUID = 5314219215092632325L;
	private String hostname;
	private int port;
	private String interface_name;
	private String object_id;
	
	public RemoteObjectReference(String hostname, int port, String interface_name, String object_id){
        /* ROR stores host/port of server as well as the interface and object names of the object */
		this.hostname = hostname;
		this.port = port;
		this.interface_name = interface_name;
        this.object_id = object_id;
	}
	
	public String getInterfaceName(){
		return this.interface_name; 
	}

    public String getObject_id() {return this.object_id;}
	
	public Object getStub() throws ClassNotFoundException{
        /* Creates/Returns Proxy around interface for given interface */
		Class<?> class_name = Class.forName(this.interface_name);
		Class[] new_class = new Class[]{class_name};
		InvocationHandler remote_handler = new RemoteHandler(this.hostname, this.port, this.interface_name, this.object_id);
		Object proxy = Proxy.newProxyInstance(class_name.getClassLoader(),new_class, remote_handler);
		
		return proxy;
	}

}
