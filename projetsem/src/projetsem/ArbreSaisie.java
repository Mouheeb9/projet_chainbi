package projetsem;

import java.util.Scanner;

public class ArbreSaisie {

    static Scanner scanner = new Scanner(System.in);

    // Création de l'arbre général avec saisie utilisateur
    public static Noeud creerGarbre() {
        System.out.print("entrez la valeur de ce noeud : ");
        String valeur = scanner.nextLine();
        Noeud racine = new Noeud(valeur);

        System.out.print("ce noeud a-t-il des enfants ? (o/n) : ");
        String reponse = scanner.nextLine();

        while (reponse.equalsIgnoreCase("o")) {
            System.out.println("ajout d'un enfant pour le noeud " + valeur);
            Noeud enfant = creerGarbre();  // Appel récursif
            racine.enfants.add(enfant);

            System.out.print("ajouter un autre enfant à " + valeur + " ? (o/n) : ");
            reponse = scanner.nextLine();
        }

        return racine;
    }

    // Affichage simple de l'arbre avec indentation
    public static void afficherArbre(Noeud noeud, String prefixe) {
        System.out.println(prefixe + noeud.valeur);
        for (Noeud enfant : noeud.enfants) {
            afficherArbre(enfant, prefixe + "  ");
        }
    }

    // Pour tester la saisie et l'affichage indépendamment
    public static void main(String[] args) {
        System.out.println("creation de l'arbre general :");
        Noeud racine = creerGarbre();

        System.out.println("\nAffichage de l'arbre general :");
        afficherArbre(racine, "");
    }
}
