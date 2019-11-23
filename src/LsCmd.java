

import java.util.regex.Pattern;

/**
 *
 * @author Cristina-Ramona
 */
public class LsCmd implements Command{

    private Directory argument;
    private String path;
    private boolean isRecursive = false;
  
    
    public LsCmd() {}

    /**
     * Constructorul cu parametri - seteaza atributele
     * @param arguments String; contine comanda, path-ul si/sau o optiune
     */
    public LsCmd (String[] arguments) {
        
            setArgs(arguments);

    }

    /**
     * Setarea atributelor cu argumentele
     * @param arguments comanda + argumente
     */
    public void setArgs (String[] arguments)
    {   
        argument = Operations.currentDirectory;
        int i;
        for ( i=1; i< arguments.length; i++ )
        {
            if (arguments[i].charAt(0)=='-')
                isRecursive = true;
            else{ 
                
              path = arguments[i];
              argument = Operations.ValidPath(arguments[i]);
            }      
        } 
    }

    /**
     * Afiseaza subdirectoarele recursiv/nerecursiv
     */
    @Override
    public void execute() {
        
       
          if (argument == null)
            System.err.println("ls: "+path+": No such directory");
        else if (!isRecursive)
            argument.printSubEntries();
        else {
           argument.printSubEntries();
           for (Entry entry: argument.subEntries){
               if (entry instanceof Directory){
                   argument = (Directory) entry;
                   execute();
                }   
            }
        }
    }
}
