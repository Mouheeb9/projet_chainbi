package projetsem;

import java.util.Scanner;

public class TransformationArbreBinaire {

    public static NoeudBinaire transformerEnBinaire(Noeud noeudGeneral) {
        if (noeudGeneral == null) return null;

        NoeudBinaire noeudBinaire = new NoeudBinaire(noeudGeneral.valeur);

        if (!noeudGeneral.enfants.isEmpty()) {
            noeudBinaire.gauche = transformerEnBinaire(noeudGeneral.enfants.get(0));

            NoeudBinaire courant = noeudBinaire.gauche;
            for (int i = 1; i < noeudGeneral.enfants.size(); i++) {
                courant.droite = transformerEnBinaire(noeudGeneral.enfants.get(i));
                courant = courant.droite;
            }
        }

        return noeudBinaire;
    }

    // Version simple d'affichage de l'arbre binaire
    public static void afficherArbreBinaireSimple(NoeudBinaire noeud, int niveau, String direction) {
        if (noeud == null) return;

        for (int i = 0; i < niveau; i++) {
            System.out.print("  ");
        }
        System.out.println(direction + ": " + noeud.valeur);

        afficherArbreBinaireSimple(noeud.gauche, niveau + 1, "G");
        afficherArbreBinaireSimple(noeud.droite, niveau + 1, "D");
    }

    // Calcul de la profondeur d’un nœud dans l’arbre général
    public static int calculerProfondeurGenerale(Noeud racine, String valeurRecherchee) {
        return profondeurRecursive(racine, valeurRecherchee, 0);
    }

    private static int profondeurRecursive(Noeud noeud, String valeurRecherchee, int profondeur) {
        if (noeud == null) return -1;

        if (noeud.valeur.equals(valeurRecherchee)) return profondeur;

        for (Noeud enfant : noeud.enfants) {
            int resultat = profondeurRecursive(enfant, valeurRecherchee, profondeur + 1);
            if (resultat != -1) return resultat;
        }

        return -1;
    }

    // Calcul de la hauteur de l’arbre binaire
    public static int calculerHauteur(NoeudBinaire noeud) {
        if (noeud == null) return 0;

        int hauteurGauche = calculerHauteur(noeud.gauche);
        int hauteurDroite = calculerHauteur(noeud.droite);

        return 1 + Math.max(hauteurGauche, hauteurDroite);
    }

    // Méthode principale
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("creation de l'arbre general :");
        Noeud racine = ArbreSaisie.creerGarbre(); // Ne pas oublier de corriger dans ArbreSaisie

        System.out.println("\naffichage de l'arbre general :");
        ArbreSaisie.afficherArbre(racine, "");

        NoeudBinaire racineBinaire = transformerEnBinaire(racine);

        System.out.println("\naffichage de l'arbre binaire transforme :");
        afficherArbreBinaireSimple(racineBinaire, 0, "R");

        System.out.print("\nEntrez la valeur du noeud dont vous voulez connaitre la profondeur : ");
        String valeurRecherchee = scanner.nextLine();

        int profondeur = calculerProfondeurGenerale(racine, valeurRecherchee);

        if (profondeur > 0) {
            System.out.println("la profondeur du noeud \"" + valeurRecherchee + "\" est : " + profondeur);
        } else if (profondeur == 0) {
            System.out.println("Le noeud \"" + valeurRecherchee + "\" est la racine, donc profondeur = 0.");
        } else {
            System.out.println("Le noeud \"" + valeurRecherchee + "\" n'a pas ete trouve dans l'arbre general.");
        }

        int hauteur = calculerHauteur(racineBinaire) - 1; // On ne compte pas la racine
        System.out.println("\nlsa hauteur de l'arbre binaire est : " + hauteur);

        scanner.close();
    }
}
