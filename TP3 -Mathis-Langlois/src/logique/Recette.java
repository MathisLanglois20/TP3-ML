package logique;

import java.util.ArrayList;
import static logique.Ingredient.*;
/**
 * Author : Mathieu Bourgoin
 * Ordre de conception : 3e
 */
public class Recette
{
    public static final int MIN_CARACTERES_NOM_RECETTE = 10;
    public static final int DIFFICULTE_MIN = 1;
    public static final int DIFFICULTE_MAX = 5;

    public static final String MESSAGE_VALEUR_NULL_NON_PERMISE = "Valeur null non permise";
    public static final String MESSAGE_NOM_TROP_COURT = "Nom trop court";
    public static final String MESSAGE_DIFFICULTE_INVALIDE = "Difficulte invalide";
    public static final String MESSAGE_POINT_EXPERIENCE_INVALIDE = "Point experience invalide";
    public static final String MESSAGE_INGREDIENT_DOUBLE = "Ingredient deja present";

    private ArrayList<Ingredient> ingredients;
    private String nom;
    private int difficulte;
    private int pointExperience;

    public Recette(Ingredient ing1, Ingredient ing2, Ingredient ing3, String nom, int difficulte, int pointExperience)
    {
        if (ing1 == null || ing2 == null || ing3 == null) {
            throw new IllegalArgumentException(MESSAGE_VALEUR_NULL_NON_PERMISE);
        }else if (ing1.getNom().equals(ing2.getNom()) ||
                ing1.getNom().equals(ing3.getNom()) ||
                ing2.getNom().equals(ing3.getNom())) {
            throw new IllegalArgumentException(MESSAGE_INGREDIENT_DOUBLE);
    }
        this.ingredients = new ArrayList<Ingredient>();
        this.ingredients.add(ing1);
        this.ingredients.add(ing2);
        this.ingredients.add(ing3);

        this.setDifficulte(difficulte);
        this.setNom(nom);
        this.setPointExperience(pointExperience);
    }


    public String getNom() {
        return nom;
    }

    private void setNom(String nom) {
        if (nom == null) {
            throw new IllegalArgumentException(MESSAGE_VALEUR_NULL_NON_PERMISE);
        } else if (nom.length() < MIN_CARACTERES_NOM_RECETTE) {
            throw new IllegalArgumentException(MESSAGE_NOM_TROP_COURT);
        }
        this.nom = nom;
    }

    public int getDifficulte() {

        return difficulte;
    }

    private void setDifficulte(int difficulte) {
        if(difficulte < DIFFICULTE_MIN || difficulte > DIFFICULTE_MAX){
        this.difficulte = difficulte;
    }

    public int getPointExperience() {
        return pointExperience;
    }

    private void setPointExperience(int pointExperience) {
        if(pointExperience <= 0) {
            throw new IllegalArgumentException(MESSAGE_POINT_EXPERIENCE_INVALIDE);
        }
        this.pointExperience = pointExperience;
    }

    public int obtenirPrix()
    {
        int prixTotal = 0;

        for (Ingredient ing : this.ingredients)
            prixTotal += ing.getPrix();

        return prixTotal;
    }

    public boolean contientIngredient(String nom)
    {
        if (nom == null) {
            throw new IllegalArgumentException(MESSAGE_VALEUR_NULL_NON_PERMISE);
        }
        boolean estContenu = false;

        for (Ingredient ing : this.ingredients)
        {
            if (ing.getNom().equals(nom))
            {
                estContenu = true;
                break;
            }
        }

        return estContenu;
    }
    @Override
    public String toString()
    {
        return String.format("%s|%s|%s|%s|%s|%s", this.getNom(), this.ingredients.get(0).getNom(), this.ingredients.get(1).getNom(), this.ingredients.get(2).getNom(), this.getDifficulte(), this.getPointExperience());
    }


}
