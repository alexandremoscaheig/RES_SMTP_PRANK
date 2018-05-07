package heigvd;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if(args.length != 3){
            throw new IllegalArgumentException();
        }

        String userDirectory = System.getProperty("user.dir");
        new SmtpPrank(args[0], Integer.valueOf(args[1]), userDirectory + File.separator + args[2]);
    }
}
