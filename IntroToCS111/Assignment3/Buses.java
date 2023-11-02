/*
 *
 * Write the Buses program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Buses.java
 * To execute:
 *        java Buses 7302
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Buses {
    public static void main(String[] args) {

        int busCodeNumber = Integer.parseInt(args[0]); // WRITE YOUR CODE HERE
        if (busCodeNumber < 0) 
        {
            System.out.println("ERROR");
        }
        else 
        {
            String str = busCodeNumber + "";
            int num1 = Integer.parseInt(str.substring(0,1)); 
            int num2 = Integer.parseInt(str.substring(1,2)); 
            int num3 = Integer.parseInt(str.substring(2,3)); 
            int num4 = Integer.parseInt(str.substring(3,4)); 
            int sum = num1+num2+num3+num4;
            if (sum % 2 == 0)
            {
                System.out.println("LX");
            }
            else
            {
                System.out.println("H");
            }
        }
    }
}
