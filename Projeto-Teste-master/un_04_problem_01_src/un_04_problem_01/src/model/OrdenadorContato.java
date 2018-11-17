
package model;

import java.util.Comparator;

public class OrdenadorContato implements Comparator<Contato>{
 
    @Override
    public int compare(Contato um, Contato dois) {
        return um.getNome().toLowerCase().compareTo(dois.getNome());
    }

    
}
