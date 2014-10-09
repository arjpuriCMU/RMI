package Example;

import java.io.Serializable;

public class EchoObject implements Serializable, EchoInterface{
	
	private static final long serialVersionUID = -1073920152080307530L;
	private String stored_string;

    /* Object has unique string */
	public EchoObject(String s){
		this.stored_string = s;
	}

    /* Returns concatenation of two strings */
	public String echoCombineMessage(String s1, String s2){
		return s1 + " " + s2;
	}

    /* Returns concatenation of stored_string and stored_string of object o */
	public String combineWithEchoObject(EchoInterface o){
		return this.stored_string + o.getStoredString();
	}

    /* Returns stored_string */
	public String getStoredString(){
		return this.stored_string;
	}

}
