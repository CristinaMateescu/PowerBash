

/**
 *
 * @author Cristina-Ramona
 */
public class RmCmd implements Command {

    private Directory path;
    private String entry;
    private String fullPath;

    public RmCmd() {
    }

    /**
     * Seteaza argumentele
     * @param arguments String; comanda + argumente
     */
    public RmCmd(String[] arguments) {

        setArgs(arguments);
    }
    
    
    /**
     * Seteaza atributele cu argumentele
     * @param arguments comanda + argumente
     */
    private void setArgs(String[] arguments) {
        
        String[] entries = arguments[1].split("/");
        fullPath = arguments[1];

        if (entries[entries.length - 1].equals(".")
                || entries[entries.length - 1].equals("..")) {
            path = Operations.ValidPath(arguments[1]);
            entry = ".";
            
        } else if (entries.length == 1) {
            path = Operations.currentDirectory;
            entry = entries[0];
            
        } else {
            entry = entries[entries.length - 1];

            int index = arguments[1].length() - entry.length();
            path = Operations.ValidPath(arguments[1].substring(0, index));
        }

    }

    /**
     * Sterge o intrare cu un path dat
     */
    @Override
    public void execute() {

        if (path == null || !path.contine(entry)) {
            System.err.println("rm: cannot remove " + fullPath
                    + ": No such file or directory");
        } else {
            if (path.getEntry(entry) instanceof Directory) {
                Directory ent = (Directory) path.getEntry(entry);
                if (!path.contineRecursiv(ent, Operations.currentDirectory.name)
                        && !Operations.currentDirectory.name.equals(ent.name)) {
                    path.subEntries.remove(ent);
                }
            } else {
                Files ent = (Files) path.getEntry(entry);
                path.subEntries.remove(ent);
            }
        }
    }
}
