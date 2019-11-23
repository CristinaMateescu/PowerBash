

/**
 *
 * @author Cristina-Ramona
 */
public class PwdCmd implements Command {

    
    public PwdCmd() {
    }

    /**
     * Listeaza directorul curent
     */
    @Override
    public void execute() {
        Directory current = Operations.currentDirectory;
        String path = current.getAbsolutePath(current);
        System.out.println(path);
    }

}
