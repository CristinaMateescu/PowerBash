

/**
 *
 * @author Cristina-Ramona
 */
public class MkdirCmd implements Command {

    private Directory path;
    private String dir;
    private String parentPath;

    
    public MkdirCmd() {
    }

    /**
     * Constructorul - seteaza argumentele 
     * @param arguments comanda + argumente
     */
    public MkdirCmd (String[] arguments) {

        setArgs(arguments);

    }
    
    /**
     * Setarea atributelor cu argumentele
     * @param arguments String; comanda + argumente
     */

    private void setArgs(String[] arguments) {

        String[] entries = arguments[1].split("/");

        if (entries.length == 1) {
            path = Operations.currentDirectory;
            dir = entries[0];
            
        } else if (entries.length == 2 && arguments[1].charAt(0) == '/') {
            path = Operations.root;
            dir = entries[1];
            
        } else {
            dir = entries[entries.length - 1];
            int index = arguments[1].length() - dir.length();
            path = Operations.ValidPath(arguments[1].substring(0, index)); 
            
            if (path == null)
                parentPath = arguments[1].substring(0, index-1);
        }
    }

    /**
     * Creeaza director cu o cale data
     */
    @Override
    public void execute() {
        if (path == null) {
            System.err.println("mkdir: "+parentPath+": No such directory");
        } else {

            if (path.contine(dir)) {
                String absolutePath = path.getAbsolutePath(path);
                
                if (absolutePath.equals("/"))
                    System.err.println("mkdir: cannot create directory "
                        + absolutePath+dir
                        + ": Node exists");
                else
                    System.err.println("mkdir: cannot create directory "
                        + absolutePath +"/"+ dir
                        + ": Node exists");
            } else {
                Entry newdir = new Directory(dir);
                path.addEntry(newdir);
                newdir.parent = path;
               
            }
        }
    }
}
