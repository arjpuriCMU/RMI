package Messages;

import Registry.RemoteHandler;
import Registry.RemoteObjectReference;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by karansharma on 10/2/14.
 */
public class MethodCallMessage implements Serializable{

	private static final long serialVersionUID = -7637433779619529656L;
	public String object_id;
    public String method;
    public Object[] args;
    public Class<?>[] arg_types;

    public MethodCallMessage(String object_id, Method method, Object[] args){
        this.object_id = object_id;     //Object_id
        this.method = method.getName(); //Method Name
        this.args = args;   //Parameters
        this.arg_types = method.getParameterTypes();  //Types of Parameters

        /* Change any references to other stubs to their RORs */
        for (int i = 0; i < arg_types.length; i++){
            if(Proxy.isProxyClass(args[i].getClass())) //Checks if argument is a stub
            {
                //sets argument sent to ROR of argument object
                RemoteHandler handler = ((RemoteHandler) Proxy.getInvocationHandler(args[i]));
                args[i] = new RemoteObjectReference(handler.getHostname(), handler.getPort(),
                        handler.getInterface_Name(), handler.getObject_id());
                this.arg_types[i] = RemoteObjectReference.class;
            }
        }
    }
    
}
