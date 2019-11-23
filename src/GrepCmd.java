

/**
 *
 * @author Cristina-Ramona
 */
public class GrepCmd implements Command{
    
    private Directory argument;
    private String path;
    private boolean isRecursive = false;
    private String expression;
    
    
    public GrepCmd() {}

    /**
     * 
     * @param arguments String; comanda + argumente
     */
    public GrepCmd (String[] arguments) {
        setArgs(arguments);  
    }

    /**
     * Seteaza argumentele 
     * @param arguments String; comanda + argumente
     */
    public void setArgs (String[] arguments)
    {
        argument = Operations.currentDirectory;
        int i;
        for ( i=1; i< arguments.length; i++ )
        {
            if (arguments[i].charAt(0)=='-')
                isRecursive = true;
            else if (arguments[i].equals("|")){
                expression = arguments[i+2];
                expression = expression.substring(1, expression.length()-1);
                break;
            }
            else{     
              path = arguments[i];
              argument = Operations.ValidPath(arguments[i]);
            }      
        }
    }

    /**
     * Printeaza intrarile care fac match pe "expression"
     */
    @Override
    public void execute() {
        if (argument == null)
            System.err.println("ls: "+path+": No such directory");
        else if (!isRecursive)
            argument.grepPrintSubE(expression);
        else {
           argument.grepPrintSubE(expression);
           for (Entry entry: argument.subEntries){
               if (entry instanceof Directory){
                   argument = (Directory) entry;
                   execute();
                }   
           }
        }
    }
 
}
