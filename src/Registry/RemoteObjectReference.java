package Registry;

import java.io.Serializable;

public class RemoteObjectReference implements Serializable {
	private String hostname;
	private int port;
	private String interface_name;
	
	public RemoteObjectReference(String hostname, int port, String interface_name){
		this.hostname = hostname;
		this.port = port;
		this.interface_name = interface_name;
	}
	
	public String getInterfaceName(){
		return this.interface_name;
	}

}
