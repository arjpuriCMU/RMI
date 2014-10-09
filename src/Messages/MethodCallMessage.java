package Messages;

import Registry.RemoteHandler;
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
    //public String[] arg_types;
    public Class<?>[] arg_types;

    public MethodCallMessage(String object_id, Method method, Object[] args){
        this.object_id = object_id;
        this.method = method.getName();
        this.args = args;
        //this.arg_types = new String[args.length];
        this.arg_types = method.getParameterTypes();
        for (int i = 0; i < arg_types.length; i++){
            if(Proxy.isProxyClass(args[i].getClass())) //Checks if argument is a stub
            {
                //sets argument send to object_id of argument stub
                args[i] = ((RemoteHandler) Proxy.getInvocationHandler(args[i])).getObject_id();
                System.out.println(args[i].toString());
                //this.arg_types[i] = "RemoteObjectReference";
            }
            //else
        	    //arg_types[i] = args[i].getClass().getName();
        }
    }
    
}
