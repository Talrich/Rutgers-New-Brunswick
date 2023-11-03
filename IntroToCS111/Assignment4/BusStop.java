/*
 * Write your program inside the main method to find the order
 * which the bus the student needs to take will arrive
 * according to the assignemnt description. 
 *
 * To compile:
 *        javac BusStop.java
 * 
 * DO NOT change the class name
 * DO NOT use System.exit()
 * DO NOT change add import statements
 * DO NOT add project statement
 * 
 */
public class BusStop {

    public static void main(String[] args) {

        int[] buses = new int[args.length];
        for (int i =0; i<=(args.length-1); i++)
        {
            buses[i] = Integer.parseInt(args[i]);
        }
        
        int search = buses[args.length-1];
        int num = 1000;
        boolean present = false;
        for (int j =0; j <=args.length-2; j++)
        {
            if (buses[j] == search)
            {
                present = true;
                num = j+1;
                System.out.println(num);
                break;
            }
        }
        if (present != true)
        {
            System.out.println(num);
        }
    }
}
