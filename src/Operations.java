

import java.util.ArrayList;

/**
 *
 * @author Cristina-Ramona
 */
public class Operations {

    /**
     * root - radacina sistemului de fisiere
     */
    public static final Directory root = new Directory("/");

    /**
     * directorul curent 
     */
    public static Directory currentDirectory = root;

    /**
     * Verifica daca o cale absoluta e valida
     * @param path cale absoluta a unui director
     * @return Directorul cu calea absoluta "path"
     */
    public static Directory ValidPath(String path) {

        //Set current to root / currentDirectory
        Directory current;
        if (path.charAt(0) == '/') {
            current = root;

            if (path.length() > 1) {
                path = path.substring(1);
            }

        } else {
            current = currentDirectory;
        }

        //Split path
        String[] directories = path.split("/");

        //Search through Files System
        for (int i = 0; i < directories.length; i++) {

            if (!directories[i].equals(".")) {

                if (directories[i].equals("..")) {
                    current = current.parent;

                } else if (!current.contine(directories[i])) {
                    return null;
                } else {
                    Entry entry = current.getEntry(directories[i]);
                    if (entry instanceof Files) {
                        return null;
                    }
                    current = (Directory) entry;
                }
            }
            if (current == null) {
                return null;
            }
        }
        return current;
    }
    
    /**
     * Copiaza recursiv un DirectorSursa in Destinatie
     * @param dirSource Directorul de copiat
     * @param dirDest Directorul in care se copiaza
     */

    static void recCopyDir(Directory dirSource, Directory dirDest) {

        dirDest.name = dirSource.name;
        for (Entry entry : dirSource.subEntries) {

            if (entry instanceof Files) {
                Files file = new Files(entry.name);
                file.parent = dirDest;
                dirDest.subEntries.add(file);

            } else if (entry instanceof Directory) {

                Directory dir = new Directory();
                dir.parent = dirDest;
                recCopyDir((Directory) entry, dir);
                dirDest.subEntries.add(dir);
            }
        }
    }
}
