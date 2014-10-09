package Example;

import java.io.Serializable;

import RMIServer.Remote440;

public class EchoObject extends Remote440 implements Serializable, EchoInterface{
	private String stored_string;
	
	public EchoObject(String s){
		this.stored_string = s;
	}

	public String echoCombineMessage(String s1, String s2){
		return s1 + " " + s2;
	}
	
	public String combineWithEchoObject(EchoObject o){
		return this.stored_string + o.getStoredString();
	}
	
	public String getStoredString(){
		return this.stored_string;
	}

}
