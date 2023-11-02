/*
 *  
 * Write the DogWalker program inside the main method
 * according to the assignment description.
 * 
 * To compile:
 *        javac DogWalker.java
 * To execute:
 *        java DogWalker 5
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 *
 */
public class DogWalker {

    public static void main(String[] args) {

       int n = Integer.parseInt(args[0]); // WRITE YOUR CODE HERE
       int x = 0; 
       int y = 0;
       int i = 0;
       System.out.println("(" + x + ", " + y + ")"); // Prints starting point
       while (i < n) 
       {
        int direction = (int) (Math.random()*4) + 1;
        if (direction == 1) // Moves north
        {
            y++;
        }
        else if (direction == 2) // Moves south
        {
            y--;
        }
        else if (direction == 3) // Moves east
        {
            x++;
        }
        else // Moves west
        {
            x--;
        }
        System.out.println("(" + x + ", " + y + ")");
        i++;
       }
       System.out.println("Squared distance = " + (Math.pow(x, 2) + Math.pow(y, 2)));
    }
}
