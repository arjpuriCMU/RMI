package Messages;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by karansharma on 10/2/14.
 */
public class MethodCallMessage implements Serializable{


    public String object_id;
    public Method method;
    public Object[] args;
    public String[] arg_types;

    public MethodCallMessage(String object_id, Method method, Object[] args){
        this.object_id = object_id;
        this.method = method;
        this.args = args;
        this.arg_types = new String[args.length];
        for (int i = 0; i < arg_types.length; i++){
        	arg_types[i] = args[i].getClass().getName();
        }
    }
    
}
