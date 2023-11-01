/*
 * Write the Palindrome program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac Palindrome.java
 * To execute:
 *        java Palindrome 5 4 6 6 4 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */

public class Palindrome {
    public static void main(String[] args) {
       
        int first = Integer.parseInt(args[0]); // WRITE YOUR CODE HERE
        int second = Integer.parseInt(args[1]);
        int third = Integer.parseInt(args[2]);
        int fourth = Integer.parseInt(args[3]);
        int fifth = Integer.parseInt(args[4]);
        int sixth = Integer.parseInt(args[5]);

        System.out.println((first == sixth) && (second == fifth) && (third == fourth)); 
    }
}
