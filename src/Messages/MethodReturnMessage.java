package Messages;

import java.io.Serializable;

/**
 * Created by karansharma on 10/5/14.
 */
public class MethodReturnMessage implements Serializable{
    private Object ret;
    private boolean exception;
    public MethodReturnMessage(Object ret, boolean exception)
    {
        this.ret = ret;
        this.exception = exception;
    }

    public Object getRet() throws Exception {
        if(exception)
            throw new Exception();
        return this.ret;
    }
}
