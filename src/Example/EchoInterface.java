package Example;

import RMIServer.Remote440;

/**
 * Created by karansharma on 10/7/14.
 */
public interface EchoInterface extends Remote440 {

    public String echoCombineMessage(String s1, String s2);
    public String combineWithEchoObject(EchoInterface o);
    public String getStoredString();
}
