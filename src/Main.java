

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Cristina-Ramona
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException{
         
    Scanner scan = new Scanner(new File(args[0]));
    PrintStream out = new PrintStream(args[1]);
    PrintStream  err = new PrintStream(args[2]);
    System.setOut(out);
    System.setErr(err);
    
    //Citire input
    int i=1;
    while (scan.hasNext()) {
            System.out.println(i);
            System.err.println(i++);
            String operation = scan.nextLine();
            String parts[] = operation.split(" ");
            
            CommandFactory cmdFactory = CommandFactory.getFactory();
            Command cmd = cmdFactory.createCommand(parts);
            CommandRun.run(cmd);
            
        }

    }
    
    
    
}
