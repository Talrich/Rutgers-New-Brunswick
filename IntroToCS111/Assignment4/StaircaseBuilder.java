/*
 * Write your program inside the main method to build
 * a staicase in a 2D array of characters according
 * to the assignment description
 *
 * To compile:
 *        javac StaircaseBuilder.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class StaircaseBuilder {
    
    public static void main(String[] args) {

        int d = Integer.parseInt(args[0]); 
        int bricks = Integer.parseInt(args[1]); 
        String[][] stairs = new String[d][d]; 
        int y = d-1; 
        int x = 0; 
        int counter = 0;
        int j = 1; 

        while ((x <= (d-1)) && (bricks != 0)) 
        {
            stairs[x][y]="X";
            counter++;
            bricks--;
            if (counter == j)
            {
                x++;
                y = d-1;
                counter = 0;
                j++;
            }
            else if (y > 0)
            {
                y--;
            }
        }

        y = 0; 
        x = 0;

        while (y <= d-1)
        {
            while (x <= d-1)
            {
                if (stairs[x][y] == null)
                {
                    stairs[x][y] = " ";
                }
                System.out.print(stairs[x][y] + "");
                x++;
            }
            x = 0;
            System.out.println();
            y++;
        }
        System.out.println("Bricks remaining: " + bricks);
    }
}
