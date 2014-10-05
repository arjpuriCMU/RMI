package Messages;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by karansharma on 10/2/14.
 */
public class MarshallMessage implements Serializable{

    public String object_id;
    public Method method;
    public Object[] args;

    public MarshallMessage(String object_id, Method method, Object[] args){
        this.object_id = object_id;
        this.method = method;
        this.args = args;

    }
}
