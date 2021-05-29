import java.util.InputMismatchException;
import java.util.Scanner;

public class NombrePairImpair {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try{
            // Demande saisi utilisateur
            System.out.println("Veuillez saisir une nombre entier positif ou négatif");
            int saisie = scanner.nextInt();

            // Vérification si la saisie est zéro pas de parité
            if(saisie == 0){
                System.out.println("Le nombre est zéro (et il est pair)");
            }// Nombre négatif
            else if (saisie < 0){
                // Vérification de la parité
                if(saisie%2 == 0){
                    System.out.println("Le nombre est négatif et pair");
                }else{
                    System.out.println("Le nombre est négatif et impair");
                }
            }// Nombre positif
            else{
                // Vérification de la parité
                if (saisie%2 == 0){
                    System.out.println("Le nombre est positif et pair");
                }else{
                    System.out.println("Le nombre est positif et impair");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Erreur de saisie un nombre entier positif ou négatif est attendu");
        }
    }
}
