package util;

public class Group<A,B> {
	private A object1;
	private B object2;
	
	public Group(A obj1, B obj2){
		this.object1 = obj1;
		this.object2 = obj2;
	}
	
	public A getFirstObj(){
		return this.object1;
	}
	
	public B getSecondObj(){
		return this.object2;
	}

}
