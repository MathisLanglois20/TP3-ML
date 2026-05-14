package logique;

public class Ingredient
{
    public static final int MIN_CARACTERS_NOM=6;
    public static final int PRIX_INVALIDE=0;
    public static final String MESSAGE_VALEUR_NULL_NON_PERMISE = "Valeur null non permise";
    public static final String MESSAGE_NOM_TROP_COURT = "Nom trop court";
    public static final String MESSAGE_PRIX_INVALIDE = "Prix invalide";

    private String nom;
    private int prix;

    public Ingredient(String nom, int prix)
    {
        this.setNom(nom);
        this.setPrix(prix);
    }

    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom == null){
            throw new IllegalArgumentException(MESSAGE_VALEUR_NULL_NON_PERMISE);
        } else if (nom.length() < MIN_CARACTERS_NOM) {
            throw new IllegalArgumentException(MESSAGE_NOM_TROP_COURT);
        }
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    private void setPrix(int prix) {
       if(prix <= PRIX_INVALIDE){
           throw new IllegalArgumentException(MESSAGE_PRIX_INVALIDE);
       }
        this.prix = prix;
    }

    @Override
    public String toString()
    {
        return "Ingredient{" + "nom=" + nom + ", prix=" + prix + '}';
    }
}