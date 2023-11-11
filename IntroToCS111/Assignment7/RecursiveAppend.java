/*************************************************************************
 *  Compilation:  javac RecursiveAppend.java
 *  Execution:    java RecursiveAppend
 *
 *************************************************************************/

public class RecursiveAppend {

    /*
     * Returns the orginal string appended n times 
     *
     * This method does not print ANYTHING
     */
    public static String appendNTimes (String original, int n) {

        String value = original; 
        String output = value; 
        if (n > 0)
        {
            output = output + appendNTimes(original, n-1);
        }
        return output;
    }

    /*
     * Test client
     */
    public static void main (String[] args) {

	System.out.println(appendNTimes("cat", 0));
	System.out.println(appendNTimes("cat", 1));
	System.out.println(appendNTimes("cat", 2));
	
    }
}
