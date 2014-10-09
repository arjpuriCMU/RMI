package Example;

import java.io.Serializable;

import RMIServer.Remote440;

public class Integer440Object extends Remote440 implements Serializable, Integer440Interface {
	private int data;
	public Integer440Object(int n){
		this.data = n;
	}
	
	public int add(Integer440Object n){
		return this.data + n.getData();
	}
	
	public int subtract(Integer440Object n){
		return this.data - n.getData();
	}
	
	public int multiply(Integer440Object n){
		return this.data * n.getData();
	}
	
	public int divide(Integer440Object n){
		return this.data / n.getData();
	}
	
	
	public int getData(){
		return this.data;
	}
}
