package projetsem;

import java.util.Scanner;
import java.util.Stack;

public class DelimiteursChecker {

    // Vérifie si l'expression est bien équilibrée
    public static boolean estEquilibree(String expr) {
        Stack<Character> pile = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                pile.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (pile.isEmpty()) {
                    return false;
                }

                char ouvert = pile.pop();
                if (!coupleValide(ouvert, ch)) {
                    return false;
                }
            }
        }

        return pile.isEmpty();
    }

    // Vérifie la position de la première erreur
    public static int positionErreur(String expr) {
        Stack<Character> pile = new Stack<>();
        Stack<Integer> positions = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {
                pile.push(ch);
                positions.push(i);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (pile.isEmpty()) {
                    return i;
                }

                char ouvert = pile.pop();
                positions.pop();

                if (!coupleValide(ouvert, ch)) {
                    return i;
                }
            }
        }

        if (!pile.isEmpty()) {
            return positions.peek();
        }

        return -1;
    }

    // Vérifie si les deux caractères forment un couple valide
    public static boolean coupleValide(char ouvrant, char fermant) {
        return (ouvrant == '(' && fermant == ')') ||
               (ouvrant == '[' && fermant == ']') ||
               (ouvrant == '{' && fermant == '}');
    }

    // Méthode principale
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("entrez une expression avec () , [] , {}: ");
        String expr = scanner.nextLine();

        if (estEquilibree(expr)) {
            System.out.println("l'expression est equilibree ");
        } else {
            int pos = positionErreur(expr);
            System.out.println("l'expression est desequilibree !");
            System.out.println("erreur a la position : " + pos);
        }

        scanner.close();
    }
}
