package Client;

import Example.nthPrimeInterface;
public class nthPrimeClient {
	 public static void main(String args[]) {
	        if (args.length != 2) {
	            System.out.println("Invalid Arguments: Provide Server Host and Port");
	            return;
	        }
	        String serverHost = args[0];
	        int serverPort = Integer.parseInt(args[1]);

	        System.out.println("nthPrimeClient has begun");

	        nthPrimeInterface prime1 = null;
	        nthPrimeInterface prime2 = null;
	        try {
	            prime1 = (nthPrimeInterface) Client.lookup(serverHost, serverPort, "nthPrimeObject1");
	            System.out.println("nthPrimeObject1 Stub Acquired");
	            System.out.println("Requesting for 3rd prime via nthPrime(int n)");
                int result1 = prime1.nthPrime(3);
	            System.out.println("Result(3rd Prime): " + result1);
	            prime2 = (nthPrimeInterface) Client.lookup(serverHost, serverPort, "nthPrimeObject2");
	            System.out.println("nthPrimeObject2 Stub Acquired");
	            System.out.println("Requesting for 50th prime via nthPrime(int n)");
	            int result2 = prime2.nthPrime(50);
	            System.out.println("Result(50th Prime)" + result2);
	        } catch (Exception e) {
	            System.out.println("Error in Lookup");
	            e.printStackTrace();
	        }
	 }

}
