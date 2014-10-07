package Example;

import java.io.Serializable;

import RMIServer.Remote440;

public class EchoObject extends Remote440 implements Serializable{
	private String msg;
	
	public EchoObject(String msg){
		this.msg = msg;
	}
	
	public String echoCombineMessage(String s1, String s2){
		return s1 + " " + s2;
	}

}
