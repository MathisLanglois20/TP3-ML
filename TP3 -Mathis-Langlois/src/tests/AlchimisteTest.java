/**
 * Auteur : Mathis Langlois
 */

package tests;


import logique.Alchimiste;
import logique.Ingredient;
import logique.Recette;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class AlchimisteTest {

    private Alchimiste alchimiste = null;
    private String nomValide = "";
    private String alphabet = "";
    private Random random = null;

    private Ingredient ingredient1 = null;
    private Ingredient ingredient2 = null;
    private Ingredient ingredient3 = null;


    private String genererStringAleatoire(int nbCaracteres) {
        String chaine = "";
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        for (int i = 0; i < nbCaracteres; i++) {
            int randomCaracterePosition = random.nextInt(alphabet.length());
            chaine += alphabet.charAt(randomCaracterePosition);
        }

        return chaine;
    }

    @BeforeEach
    void setUp() {
        //Arrange
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        random = new Random();

        nomValide = genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM);
        alchimiste = new Alchimiste(nomValide);

        ingredient1 = new Ingredient("Mandragore", 10);
        ingredient2 = new Ingredient("Licorne", 20);
        ingredient3 = new Ingredient("Poudre", 30);
    }

    @Test
    void constructeur_Nom_Null() {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(null);
        });
        //Assert
        assertEquals("Valeur null non permise", ex.getMessage());
    }

    @Test
    void constructeur_Nom_PasAssezCaracteres() {
        //Prepare
        String nom = genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM - 1);

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste = new Alchimiste(nom);
        });

        //Assert
        assertEquals(Ingredient.MESSAGE_NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    void constructeur_Nom_Valide() {
        //Assert
        assertEquals(nomValide, alchimiste.getNom());
    }

    @Test
    void constructeur_Niveau_Default() {
        //Assert
        assertEquals(1, alchimiste.getNiveau());
    }

    @Test
    void constructeur_Experience_Defaut() {
        //Assert
        assertEquals(0, alchimiste.getExperience());
    }

    @Test
    void constructeur_AvecNiveau_Valide() {
        //Prepare
        int niveau = 5;

        //Act
        alchimiste = new Alchimiste(nomValide, niveau);

        //Assert
        assertEquals(niveau, alchimiste.getNiveau());
    }

    @Test
    void fairePotion_Recette_Null() {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            alchimiste.fairePotion(null);
        });

        //Assert
        assertEquals(Ingredient.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void fairePotion_ReussiteGarantie() {

        //Prepare
        int niveauReussiteGarantie = 5;
        int difficulte = 1;
        int pointExperience = 100;

        alchimiste = new Alchimiste(nomValide, niveauReussiteGarantie);

        Recette recette = new Recette(ingredient1, ingredient2, ingredient3, "PotionTest", difficulte, pointExperience);

        //Act
        boolean resultat = alchimiste.fairePotion(recette);

        //Assert
        assertTrue(resultat);
        assertEquals(pointExperience, alchimiste.getExperience());
    }

    @Test
    void fairePotion_EchecGaranti() {
        //Prepare
        int niveauEchecGaranti = 1;
        int difficulte = 5;
        int pointExperience = 100;

        alchimiste = new Alchimiste(nomValide, niveauEchecGaranti);

        Recette recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                "PotionTest",
                difficulte,
                pointExperience
        );

        //Act
        boolean resultat = alchimiste.fairePotion(recette);

        //Assert
        assertFalse(resultat);
        assertEquals(0, alchimiste.getExperience());
    }


}