

/**
 *
 * @author Cristina-Ramona
 */
public class CpCmd implements Command {

    private Directory sourceDir;
    private Directory destDir;
    private String entry;      //Intrarea de copiat
    private String sourcePath; //Path complet sursa  
    private String destPath;   //Path complet destinatie

    
    public CpCmd() {
    }

    /**
     * Constructorul cu parametri - seteaza atributele
     * @param arguments String; contine comanda si path-urile
     */
    public CpCmd(String[] arguments) {

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
            entry = ".";
                          
        }else if (entries.length == 1) {
            sourceDir = Operations.currentDirectory;
            entry = entries[0];
            
        } else if (entries.length == 2 && arguments[1].charAt(0) == '/') {
            sourceDir = Operations.root;
            entry = entries[1];
            
        } else {
            entry = entries[entries.length - 1];
            int index = arguments[1].length() - entry.length()-1;
            sourceDir = Operations.ValidPath(arguments[1].substring(0, index));
            
        }
    }

    /**
     * Copierea unei intrari din sursa in destinatie
     */
    @Override
    public void execute() {
        
        
        if (sourceDir == null || !sourceDir.contine(entry) ) {
            System.err.println("cp: cannot copy " + sourcePath
                    + ": No such file or directory");
            
        } else if (destDir == null) {
            System.err.println("cp: cannot copy into " + destPath
                    + ": No such directory");
            
        } else {
            if (destDir.contine(entry) && !entry.equals(".")) {
                System.err.println("cp: cannot copy " + sourcePath
                        + ": Node exists at destination");
                
            } else {
                if (sourceDir.getEntry(entry) instanceof Files) {
                    Files sourceFile = (Files) sourceDir.getEntry(entry);
                    Files destFile = new Files(sourceFile.name);
                    destFile.parent = destDir;
                    destDir.subEntries.add(destFile);
                    

                } else if (sourceDir.getEntry(entry) instanceof Directory) {
                    Directory dirToCopy = (Directory) sourceDir.getEntry(entry);
                    Directory dirCopied = new Directory();
                    Operations.recCopyDir(dirToCopy, dirCopied);
                    dirCopied.parent = destDir;
                    destDir.subEntries.add(dirCopied);
                    
                }
            }
        }
    }
}
