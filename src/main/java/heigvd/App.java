package heigvd;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(args.length != 2){
            throw new IllegalArgumentException();
        }
        new SmtpPrank(args[0], Integer.valueOf(args[1]), "C:\\Users\\Alex\\Documents\\HEIG-VD\\S6\\RES\\smtp_prank\\example.xml");
    }
}
