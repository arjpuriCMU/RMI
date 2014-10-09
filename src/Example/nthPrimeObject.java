package Example;

import java.io.Serializable;

import RMIServer.Remote440;

public class nthPrimeObject extends Remote440 implements Serializable, nthPrimeInterface {
	
	
	public int nthPrime(int num){
		int n = 1;
		int test = 2;
		int prime = test;
		while (n <= num){
			if (isPrime(test)){
				prime = test;
				n++;
			}
			test++;
		}
		return prime;
	}
	private boolean isPrime(int n){
		if (n <= 1){
			return false;
		}
		else if (n == 2){
			return true;
		}
		else{
			int i = 2;
			while (i <= Math.sqrt(n)){
				if (n % i == 0){
					return false;
				}
				i++;
			}
		}
		return true;
	}
	
	public static void main(String[] args){
		nthPrimeObject n = new nthPrimeObject();
		System.out.println(n.nthPrime(1));
		System.out.println(n.nthPrime(2));
		System.out.println(n.nthPrime(3));
		System.out.println(n.nthPrime(10));
	}
	
}
