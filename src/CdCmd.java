
/**
 *
 * @author Cristina-Ramona
 */
public class CdCmd implements Command {

    private Directory argument;
    private String path;

    /**
     * Constructorul fara parametri
     */
    public CdCmd() {
    }

    /**
     * Constructorul cu parametri - seteaza atributele
     * @param arguments String; contine comanda si path-ul
     */
    public CdCmd(String[] arguments) {

        setArgs(arguments);

    }

    /**
     * Setarea atributelor cu argumentele
     * @param arguments comanda si path-ul
     */
    public void setArgs(String[] arguments) {

        path = arguments[1];
        argument = Operations.ValidPath(arguments[1]);

    }

    /**
     * Rularea comenzii "cd"
     * Schimba directorul curent cu unul dat
     */
    @Override
    public void execute() {

        if (argument == null) {
            System.err.println("cd: " + path + ": No such directory");
        } else {
            Operations.currentDirectory = argument;
        }
    }
}
