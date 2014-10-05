package Messages;

/**
 * Created by karansharma on 10/5/14.
 */
public class MethodReturnMessage {
    private Object ret;
    public MethodReturnMessage(Object ret)
    {
        this.ret = ret;
    }

    public Object getRet()
    {
        return this.ret;
    }
}
