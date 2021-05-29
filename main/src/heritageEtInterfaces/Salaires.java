package heritageEtInterfaces;

import java.util.Date;

/* *************************************************************
 * ****************** Classe Employe ***************************
 * ************************************************************/
abstract class Employe{
    private String prenom;
    private String nom;
    private int age;
    private String dateEntree;

    public Employe(String prenom, String nom, int age, String dateEntree) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.dateEntree = dateEntree;
    }

    public abstract double calculerSalaire();

    public String getType(){
        return "L'employé ";
    }

    public String getNom(){
        return getType() + prenom + " " + nom;
    }
}

/* *************************************************************
 * ****************** Classe Commercial  ***********************
 * *************** Vendeur et représentant *********************
 * ************************************************************/
abstract class Commercial extends Employe{
    private double chiffreAffaire;

    public Commercial(String prenom, String nom, int age, String dateEntree, double chiffreAffaire) {
        super(prenom, nom, age, dateEntree);
        this.chiffreAffaire = chiffreAffaire;
    }

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }
}

/* *************************************************************
 * ****************** Classe Vendeur ***************************
 * ************************************************************/
class Vendeur extends Commercial{
    private final static double POURCENTAGE_VENDEUR = 0.2; // 20%
    private final static int BONUS_VENDEUR = 400;

    public Vendeur(String nom, String prenom, int age, String dateEntree, double chiffreAffaire) {
        super(nom, prenom, age, dateEntree, chiffreAffaire);
    }

    @Override
    public double calculerSalaire() {
        return (POURCENTAGE_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
    }

    @Override
    public String getType() {
        return "Le vendeur ";
    }
}

/* *************************************************************
 * ***************** Classe Representant ***********************
 * ************************************************************/
class Representant extends Commercial{
    private final static double POURCENTAGE_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 800;


    public Representant(String nom, String prenom, int age, String dateEntree, double chiffreAffaire) {
        super(nom, prenom, age, dateEntree, chiffreAffaire);
    }

    @Override
    public double calculerSalaire() {
        return (POURCENTAGE_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT;
    }

    @Override
    public String getType() {
        return "Le représentant ";
    }
}

/* *************************************************************
 * ****************** Classe Technicien ************************
 * ********************** Production ***************************
 * ************************************************************/
class Technicien extends Employe{
    private int nombreUniteProduite;
    private final static double FACTEUR_UNITE = 5.0;

    public Technicien(String nom, String prenom, int age, String dateEntree, int nombreUniteProduite) {
        super(nom, prenom, age, dateEntree);
        this.nombreUniteProduite = nombreUniteProduite;
    }

    @Override
    public double calculerSalaire() {
        return nombreUniteProduite * FACTEUR_UNITE;
    }

    @Override
    public String getType() {
        return "Le technicien ";
    }
}

/* *************************************************************
 * **************** Classe Manutentionnaire ********************
 * ************************************************************/
class Manutentionnaire extends Employe{
    private final static double SALAIRE_HORAIRE = 65.0;
    private int nombreHeure;

    public Manutentionnaire(String nom, String prenom, int age, String dateEntree, int nombreHeure) {
        super(nom, prenom, age, dateEntree);
        this.nombreHeure = nombreHeure;
    }

    @Override
    public double calculerSalaire() {
        return nombreHeure * SALAIRE_HORAIRE;
    }
}

/* *************************************************************
 * **************** Interface A risque *************************
 * ************************************************************/
interface ARisque{
    int PRIME_MENSUELLE = 200;
}

/* *************************************************************
 * ************** Classe Technicien à risque *******************
 * ************************************************************/
class TechnARisque extends Technicien implements ARisque{

    public TechnARisque(String nom, String prenom, int age, String dateEntree, int nombreUniteProduite) {
        super(nom, prenom, age, dateEntree, nombreUniteProduite);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME_MENSUELLE;
    }
}

/* *************************************************************
 * *********** Classe Manutentionnaire à risque ****************
 * ************************************************************/
class ManutARisque extends Manutentionnaire implements ARisque{

    public ManutARisque(String nom, String prenom, int age, String dateEntree, int nombreHeure) {
        super(nom, prenom, age, dateEntree, nombreHeure);
    }

    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME_MENSUELLE;
    }
}

/* *************************************************************
 * ******************* Classe Personnel ************************
 * ************************************************************/
class Personnel{
    private Employe[] equipe;
    private int nombreEmploye;
    private final static int MAX_EMPLOYE = 1000;

    public Personnel() {
        equipe = new Employe[MAX_EMPLOYE];
        nombreEmploye = 0;
    }

    public void ajouterEmploye(Employe employe){
        ++nombreEmploye;
        if (nombreEmploye <= MAX_EMPLOYE){
            equipe[nombreEmploye - 1] = employe;
        } else {
            System.out.println("La limite maximum d'employé est atteinte");
        }
    }

    public double salaireMoyen(){
        double somme = 0.0;
        for (int i = 0; i < nombreEmploye; i++){
            somme += equipe[i].calculerSalaire();
        }
        return somme / nombreEmploye;
    }

    public void  afficherSalaires(){
        for (int i = 0; i < nombreEmploye; i++){
            System.out.println(equipe[i].getNom() + " gagne " + equipe[i].calculerSalaire() + " francs.");
        }
    }
}

public class Salaires {

    public static void main(String[] args) {
        Personnel p = new Personnel();
        p.ajouterEmploye(new Vendeur("Pierre", "Business", 45, "1995", 30000));
        p.ajouterEmploye(new Representant("Léon", "Vendtout", 25, "2001", 20000));
        p.ajouterEmploye(new Technicien("Yves", "Bosseur", 28, "1998", 1000));
        p.ajouterEmploye(new Manutentionnaire("Jeanne", "Stocketout", 32, "1998", 45));
        p.ajouterEmploye(new TechnARisque("Jean", "Flippe", 28, "2000", 1000));
        p.ajouterEmploye(new ManutARisque("Al", "Abordage", 30, "2001", 45));

        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de " + p.salaireMoyen() + " francs.");
    }
}
