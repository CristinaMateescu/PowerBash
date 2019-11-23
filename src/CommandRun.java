

/**
 *
 * @author Cristina-Ramona
 */
public class CommandRun {

    /**
     * Ruleaza comanda
     * @param cmd O comanda, instanta a unei clase de comanda
     */
    public static void run(Command cmd) {
        cmd.execute();
    }
}
