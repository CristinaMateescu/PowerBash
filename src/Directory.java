
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *
 * @author Cristina-Ramona
 */
public class Directory extends Entry {
    
    List<Entry> subEntries = new ArrayList<>();
    
    
    public Directory() {
    }
    
    /**
     * Initializeaza numele directorului
     * @param name denumire director
     */
    public Directory(String name) {
        this.name = name;
    }
    
    /**
     * Seteaza numele directorului
     * @param name noua denumire a directorului
     */
    public void setDir(String name) {
        this.name = name;
    }
    
    /**
     * Afiseaza un director si intrarile sale
     */
    public void printSubEntries() {
        
        Collections.sort(subEntries);
        System.out.println(getAbsolutePath(this) + ":");
        
        for (Entry entry : subEntries) {
            System.out.print(getAbsolutePath(entry));
            if (subEntries.indexOf(entry) != subEntries.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.print("\n\n");
    }
    
    /**
     * Construieste calea absoluta a unei intrari
     * @param entry denumirea intrarii
     * @return calea absoluta a unei intrari
     */
    public String getAbsolutePath(Entry entry) {
        if (entry.parent == null) {
            return "/";
        } else if (entry.parent.name.equals("/")) {
            return getAbsolutePath(entry.parent) + entry.name;
        }
        return getAbsolutePath(entry.parent) + "/" + entry.name;
    }
    
    /**
     * Adauga intrare
     * @param entry
     */
    public void addEntry(Entry entry) {
        subEntries.add(entry);
    }
    
    /**
     * Verifica daca directorul contine o anumita intrare
     * @param name denumirea intrarii
     * @return true daca directorul contine intrarea "name"
     */
    public boolean contine(String name) {
        for (Entry entry : subEntries) {
            
            if (entry.name.equals(name)) {
                return true;
            }
        }
        if (name.equals(".")) {
            return true;
        }
        return false;
        
    }
    
    /**
     * Verifica daca intrarea exista in subdirectoarele directorului, recursiv
     * @param dir directorul in care se verifica
     * @param name denumirea intrarii de verificat
     * @return true daca intrarea este gasita
     */
    public boolean contineRecursiv(Directory dir, String name) {
        boolean contains = false;
        
        for (Entry entry : dir.subEntries) {
            if (entry.name.equals(name)) {
                return true;
            }
            if (entry instanceof Directory) {
                contains = contineRecursiv((Directory) entry, name);
                if (contains) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returneaza o intrare dupa "name"
     * @param name
     * @return o instanta Entry corespunzatoare denumirii "name"
     */
    public Entry getEntry(String name) {
        
        for (Entry entry : subEntries) {
            if (entry.name.equals(name)) {
                return entry;
            }
        }
        if (name.equals(".")) {
            return this;
        }
        return null;
    }
    
    /**
     * Printeaza doar intrarile care fac match pe "expression"
     * @param expression o expresie Regex
     * Bonus1
     */
    public void grepPrintSubE(String expression) {
        
        Collections.sort(subEntries);
        System.out.println(getAbsolutePath(this) + ":");
        
        int nrEntries=0;
        for (Entry entry : subEntries) {
            
            if (entry.name.matches(expression)){
                if (nrEntries>0) 
                    System.out.print(" ");
                System.out.print(getAbsolutePath(entry));
                nrEntries++;} 
        }
        System.out.print("\n\n");
    }
    
}
