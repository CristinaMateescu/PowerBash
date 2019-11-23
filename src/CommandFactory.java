

/**
 *
 * @author Cristina-Ramona
 */
public class CommandFactory {

    private static final CommandFactory INSTANCE = new CommandFactory();

    private CommandFactory() {
    }

    /**
     * 
     * @return (singura) instanta a clasei
     */
    public static CommandFactory getFactory() {
        return INSTANCE;
    }

    /**
     * Creeaza si returneaza o instanta a unei clase de comanda
     * @param operation comanda + argumente
     * @return cmd - o comanda de un anumit tip
     */
    public Command createCommand(String[] operation) {
        Command cmd = null;
        String commandName = operation[0];
        switch (commandName) {
            case "ls":
                if (operation.length > 3) {
                    cmd = new GrepCmd(operation);
                } else {
                    cmd = new LsCmd(operation);
                }
                break;
            case "pwd":
                cmd = new PwdCmd();
                break;
            case "cd":
                cmd = new CdCmd(operation);
                break;
            case "cp":
                cmd = new CpCmd(operation);
                break;
            case "mv":
                cmd = new MvCmd(operation);
                break;
            case "rm":
                cmd = new RmCmd(operation);
                break;
            case "touch":
                cmd = new TouchCmd(operation);
                break;
            case "mkdir":
                cmd = new MkdirCmd(operation);
                break;
        }
        return cmd;
    }

}
