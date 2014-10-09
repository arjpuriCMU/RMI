package Example;

import java.io.Serializable;

public class Integer440Object implements Serializable, Integer440Interface {

	private static final long serialVersionUID = 3904811215141375312L;
	private int data;
	public Integer440Object(int n){
		this.data = n;
	}
	
	public int add(Integer440Interface n){
		return this.data + n.getData();
	}
	
	public int subtract(Integer440Interface n){
		return this.data - n.getData();
	}
	
	public int multiply(Integer440Interface n){
		return this.data * n.getData();
	}
	
	public int divide(Integer440Interface n){
		return this.data / n.getData();
	}
	
	
	public int getData(){
		return this.data;
	}
}
