package Registry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteHandler implements InvocationHandler {
	private String object_id;
	private int port;
	private String hostname;
	public RemoteHandler(String object_id, int port, String hostname) {
		this.object_id = object_id;
		this.port = port;
		this.hostname = hostname;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		return null;
	}

}
