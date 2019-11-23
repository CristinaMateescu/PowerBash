

/**
 *
 * @author Cristina-Ramona
 */
public class TouchCmd implements Command {

   
    private Directory path;
    private String file;
    private String parentPath;

    
    public TouchCmd() {
    }

    /**
     * Constructorul - seteaza argumentele 
     * @param arguments comanda + argumente
     */
    public TouchCmd(String[] arguments) {

        setArgs(arguments);
        
    }

    /**
     * Setarea atributelor cu argumentele
     * @param arguments String; comanda + argumente
     */
    private void setArgs(String[] arguments) {

        
        String[] entries = arguments[1].split("/");

        if (entries.length == 1){
            path = Operations.currentDirectory;
            file = entries[0]; 
        }
        else if (entries.length == 2 && arguments[1].charAt(0) == '/') {
            path = Operations.root;
            file = entries[1];
        } else {
            
            file = entries[entries.length - 1];
            int index = arguments[1].length() - file.length() ;
            path = Operations.ValidPath(arguments[1].substring(0, index));
            
            if(path==null)
                parentPath = arguments[1].substring(0, index-1);
            
        }
    }

    /**
     * Creeaza fisierul cu o cale data
     */
    @Override
    public void execute() {
        if (path == null) {   
            System.err.println("touch: "+parentPath+": No such directory");
        } else {
            if (path.contine(file)) {
                String absolutePath = path.getAbsolutePath(path);
                if (absolutePath.equals("/"))
                    System.err.println("touch: cannot create file "
                        + absolutePath+file
                        + ": Node exists");
                else
                    System.err.println("touch: cannot create file "
                        + absolutePath+"/"+file
                        + ": Node exists");
            } else {
                Entry newfile = new Files(file);
                path.addEntry(newfile);
                newfile.parent=path;
            }
        }
    }
}
