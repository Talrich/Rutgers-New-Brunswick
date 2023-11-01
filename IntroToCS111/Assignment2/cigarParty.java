public class cigarParty {
    public static void main(String[] args)
    {
        int cigars;
        cigars = Integer.parseInt(args[0]);
        boolean isWeekend = Boolean.parseBoolean(args[1]); 
        if (cigars > 40 && cigars <= 60 && isWeekend == false)
        {
            System.out.println(true);  
        }
        else if (cigars > 40 && cigars > 60 && isWeekend == false)
        {
            System.out.println(true); 
        }
        else
        {
            System.out.println(false); 
        }
    }
    
}
