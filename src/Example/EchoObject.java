package Example;

import java.io.Serializable;

public class EchoObject implements Serializable, EchoInterface{
	
	private static final long serialVersionUID = -1073920152080307530L;
	private String stored_string;
	
	public EchoObject(String s){
		this.stored_string = s;
	}

	public String echoCombineMessage(String s1, String s2){
		return s1 + " " + s2;
	}
	
	public String combineWithEchoObject(EchoInterface o){
		return this.stored_string + o.getStoredString();
	}
	
	public String getStoredString(){
		return this.stored_string;
	}

}
