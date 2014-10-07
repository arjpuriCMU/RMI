package Example;

import java.io.Serializable;

import RMIServer.Remote440;

public class EchoObject extends Remote440 implements Serializable, EchoObjectInterface{
	
	
	public String echoCombineMessage(String s1, String s2){
		return s1 + " " + s2;
	}

}
