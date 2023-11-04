public class powerOf3 {
    public static boolean isPower3(int number)
    {
        if (number == 0)
        return false; 
        while (number!=1)
        {
            if (number%3 != 0)
            {
                return false;
            }
            number = number/3; 
        }
        return false; 
    }

    public static void multiplicationTable(int n)
    {
        for (int row = 1; row <= n; row++)
        {
            for (int col = 1; col <=n; col++)
            {
                System.out.print(row*col + "\t");
            }
        }

    }
    
    public static void main(String[] args)
    {
        isPower3(9);
        isPower3(15);
    }
    
}
