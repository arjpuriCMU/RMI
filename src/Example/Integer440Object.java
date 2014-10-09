package Example;

import java.io.Serializable;

public class Integer440Object implements Serializable, Integer440Interface {

	private static final long serialVersionUID = 3904811215141375312L;
	private int data;

    /* Object has unique data value */
	public Integer440Object(int n){
		this.data = n;
	}

    /* Returns data plus data of object n */
	public int add(Integer440Interface n){
		return this.data + n.getData();
	}

    /* Returns data minus data of object n */
	public int subtract(Integer440Interface n){
		return this.data - n.getData();
	}

    /* Returns data times data of object n */
	public int multiply(Integer440Interface n){
		return this.data * n.getData();
	}

    /* Returns data divided by data of object n */
	public int divide(Integer440Interface n){
		return this.data / n.getData();
	}

    /* Returns data */
	public int getData(){
		return this.data;
	}
}
