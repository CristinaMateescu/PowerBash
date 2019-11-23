

/**
 *
 * @author Cristina-Ramona
 */
public class MvCmd implements Command {
    
    private Directory sourceDir;
    private Directory destDir;
    private String entry;      //Intrarea de mutat
    private String sourcePath; //Path complet sursa
    private String destPath;   //Path complet destinatie
    
    
    public MvCmd() {
    }

    /**
     * Constructorul cu parametri - seteaza atributele
     * @param arguments String; contine comanda si path-urile
     */
    public MvCmd(String[] arguments) {

        setArgs(arguments);

    }
    
    /**
     * Setarea atributelor cu argumentele
     * @param arguments comanda si path-urile
     */

    private void setArgs(String[] arguments) {

        sourcePath = arguments[1];
        destPath = arguments[2];
        destDir = Operations.ValidPath(destPath);

        String[] entries = arguments[1].split("/");

        //Parsare Path sursa
        //Si setare atribute
        if (entries[entries.length-1].equals(".")||
            entries[entries.length-1].equals("..")){
            sourceDir =Operations.ValidPath(arguments[1]);
            if (sourceDir!=null)
                entry = ".";
        }
        else if (entries.length == 1) {
            sourceDir = Operations.currentDirectory;
            entry = entries[0];
        } else if (entries.length == 2 && arguments[1].charAt(0) == '/') {
            sourceDir = Operations.root;
            entry = entries[1];
        } else {
            entry = entries[entries.length - 1];
            int index = arguments[1].length() - entry.length();
            sourceDir = Operations.ValidPath(arguments[1].substring(0, index));
        }
          
    }
    
    /**
     * Mutarea unei intrari din sursa in destinatie
     */
    @Override
    public void execute() {
       
        
        if (sourceDir == null || !sourceDir.contine(entry)) {
            System.err.println("mv: cannot move " + sourcePath
                    + ": No such file or directory");
        } else if (destDir == null) {
            System.err.println("mv: cannot move into " + destPath
                    + ": No such directory");
        } else {
            if (destDir.contine(entry) && !entry.equals(".")) {
                System.err.println("mv: cannot move " + sourcePath
                        + ": Node exists at destination"); 
            } else {
                
                Entry ent = sourceDir.getEntry(entry);
                      
                destDir.subEntries.add(ent);
                ent.parent.subEntries.remove(ent);
                ent.parent=destDir;
                
            }
        }
   }
}
