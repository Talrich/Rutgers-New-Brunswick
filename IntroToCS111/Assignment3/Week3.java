public class Week3 {
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        int f = 0; 
        int g = 1; 
        int i = 0; 
        while (i <= n)
        {
            System.out.println(f);
            f = f + g; 
            g = f - g;
            i++;
        }
    }
}
