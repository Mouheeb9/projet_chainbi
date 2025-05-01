package projetsem;



class NoeudBinaire {
    String valeur;
    NoeudBinaire gauche;
    NoeudBinaire droite;

    public NoeudBinaire(String valeur) {
        this.valeur = valeur;
        this.gauche = null;
        this.droite = null;
    }
}

