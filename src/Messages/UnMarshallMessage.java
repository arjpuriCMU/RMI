package Messages;

/**
 * Created by karansharma on 10/5/14.
 */
public class UnMarshallMessage {
    private Object ret;
    public UnMarshallMessage(Object ret)
    {
        this.ret = ret;
    }

    public Object getRet()
    {
        return this.ret;
    }
}
