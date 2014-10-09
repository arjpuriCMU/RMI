package Example;

import RMIServer.Remote440;

public interface Integer440Interface extends Remote440 {
	public int add(Integer440Interface n);
	public int subtract(Integer440Interface n);
	public int multiply(Integer440Interface n);
	public int divide(Integer440Interface n);
	public int getData();
}
