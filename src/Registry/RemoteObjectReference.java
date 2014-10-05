package Registry;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class RemoteObjectReference implements Serializable {
	private String hostname;
	private int port;
	private String interface_name;
	private String object_id;
	
	public RemoteObjectReference(String hostname, int port, String interface_name){
		this.hostname = hostname;
		this.port = port;
		this.interface_name = interface_name;
	}
	
	public String getInterfaceName(){
		return this.interface_name; 
	}
	
	public Object getStub() throws ClassNotFoundException{
		Class<?> class_name = Class.forName(this.interface_name);
		Class[] new_class = new Class[]{class_name};
		InvocationHandler remote_handler = new RemoteHandler(this.object_id,this.port,this.hostname);
		Object proxy = Proxy.newProxyInstance(class_name.getClassLoader(),new_class, remote_handler);
		
		return proxy;
	}

}
