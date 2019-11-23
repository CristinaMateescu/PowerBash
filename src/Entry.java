

import java.util.Objects;

/**
 *
 * @author Cristina-Ramona
 */
public abstract class Entry implements Comparable<Entry> {

    String name;
    Directory parent;
    
    @Override
    public int compareTo(Entry o) {
        return name.compareTo(o.name);
    }

}
