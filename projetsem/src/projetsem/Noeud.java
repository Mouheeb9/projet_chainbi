package projetsem;

import java.util.ArrayList;
import java.util.List;


class Noeud {
    String valeur;
    List<Noeud> enfants;

    public Noeud(String valeur) {
        this.valeur = valeur;
        this.enfants = new ArrayList<>();
    }
}


