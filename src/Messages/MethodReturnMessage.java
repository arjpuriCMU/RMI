package Messages;

import java.io.Serializable;

/**
 * Created by karansharma on 10/5/14.
 */
public class MethodReturnMessage implements Serializable{

	private static final long serialVersionUID = -7963640528520898743L;
	private Object ret;
    private boolean exception;
    private String errorMessage;

    public MethodReturnMessage(Object ret, boolean exception, String errorMessage)
    {
        this.ret = ret;
        this.exception = exception;
        this.errorMessage = errorMessage;
    }

    /* If exception throw it, otherwise return return_value */
    public Object getRet() throws Exception {
        if(exception)
            throw new Exception(this.errorMessage);
        return this.ret;
    }
    
    public Object getObject(){
    	return this.ret;
    }
}
