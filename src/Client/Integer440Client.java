package Client;

import Example.Integer440Interface;
import Example.Integer440Object;


public class Integer440Client extends Client {
	 public static void main(String args[]) {
	        if (args.length != 2) {
	            System.out.println("Invalid Arguments: Provide Server Host and Port");
	            return;
	        }

	        String serverHost = args[0];
	        int serverPort = Integer.parseInt(args[1]);

	        System.out.println("Integer440Client has begun");

	        Integer440Interface int1 = null;
	        Integer440Interface int2 = null;
	        try {
	            int1 = (Integer440Interface) Client.lookup(serverHost, serverPort, "Integer440Object1");
	            int2 = (Integer440Interface) Client.lookup(serverHost, serverPort, "Integer440Object2");
	            System.out.println("Integer440Object1 Stub Acquired");
	            System.out.println("Integer440Object2 Stub Acquired");
	            System.out.println("Calculating Sum of Integer440Object1 and Integer440Object2");
	            int result1 = int1.add((Integer440Object) int2);
	            System.out.println("Sum Result: " + result1);
	            System.out.println("Calculating Difference of Integer440Object1 and Integer440Object2");
	            int result2 = int1.subtract((Integer440Object) int2);
	            System.out.println("Difference Result: " + result2);
	            System.out.println("Calculating Product of Integer440Object1 and Integer440Object2");
	            int result3 = int1.multiply((Integer440Object) int2);
	            System.out.println("Product Result: " + result3);
	            System.out.println("Calculating Division of Integer440Object1 and Integer440Object2");
	            int result4 = int1.divide((Integer440Object) int2);
	            System.out.println("Product Result: " + result3);
	        } catch (Exception e) {
	            System.out.print("Error in Lookup");
	            e.printStackTrace();
	        }
	 }

}
