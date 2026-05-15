/**
 * Auteur : Mathis Langlois
 * Ordre de conception : 4e
 */

package tests;

import logique.Alchimiste;
import logique.Ingredient;
import logique.Laboratoire;
import logique.Recette;
import logique.ResultatExperience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class LaboratoireTest {

    private Laboratoire laboratoire = null;
    private Alchimiste alchimiste = null;

    private String nomValide = "";
    private String alphabet = "";
    private Random random = null;

    private String ingredient1Valide = "";
    private String ingredient2Valide = "";
    private String ingredient3Valide = "";

    private String genererStringAleatoire(int nbCaracteres)
    {
        String chaine = "";

        for(int i = 0; i < nbCaracteres; i++)
        {
            int randomCaracterePosition = random.nextInt(alphabet.length());
            chaine += alphabet.charAt(randomCaracterePosition);
        }

        return chaine;
    }

    @BeforeEach
    void setUp()
    {
        //Arrange
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        random = new Random();

        nomValide = genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM);
        alchimiste = new Alchimiste(nomValide);

        laboratoire = new Laboratoire(alchimiste);

        ingredient1Valide = "Poudre bleue";
        ingredient2Valide = "Poudre rose";
        ingredient3Valide = "Mandagore";
    }

    @Test
    void constructeur_Alchimiste_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire = new Laboratoire(null);
        });

        //Assert
        assertEquals(Ingredient.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void constructeur_Alchimiste_Valide()
    {
        //Assert
        assertEquals(alchimiste, laboratoire.getProprietaire());
    }

    @Test
    void constructeur_Ingredients_Charges()
    {
        //Assert
        boolean ingredientsCharges = laboratoire.getIngredients().size() > 0;

        assertTrue(ingredientsCharges);
    }

    @Test
    void constructeur_Recettes_Chargees()
    {
        //Assert
        boolean recettesChargees = laboratoire.getRecettes().size() > 0;

        assertTrue(recettesChargees);
    }

    @Test
    void trouverIngredient_Nom_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverIngredient(null);
        });

        //Assert
        assertEquals(Ingredient.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void trouverIngredient_IngredientPresent()
    {
        //Prepare
        String nomIngredient = "Poudre bleue";

        //Act
        Ingredient ingredient = laboratoire.trouverIngredient(nomIngredient);

        //Assert
        boolean ingredientExiste = ingredient != null;

        assertTrue(ingredientExiste);
        assertEquals(nomIngredient, ingredient.getNom());
    }

    @Test
    void trouverIngredient_IngredientAbsent()
    {
        //Prepare
        String nomIngredient = "IngredientInexistant";

        //Act
        Ingredient ingredient = laboratoire.trouverIngredient(nomIngredient);

        //Assert
        assertNull(ingredient);
    }

    @Test
    void trouverRecette_Ingredient1_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverRecette(null, ingredient2Valide, ingredient3Valide);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void trouverRecette_Ingredient2_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverRecette(ingredient1Valide, null, ingredient3Valide);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void trouverRecette_Ingredient3_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.trouverRecette(ingredient1Valide, ingredient2Valide, null);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void trouverRecette_RecettePresente()
    {
        //Act
        Recette recette = laboratoire.trouverRecette(
                ingredient1Valide,
                ingredient2Valide,
                ingredient3Valide
        );

        //Assert
        boolean recetteExiste = recette != null;

        assertTrue(recetteExiste);
    }

    @Test
    void trouverRecette_RecetteAbsente()
    {
        //Prepare
        String ingredient1 = "IngredientInexistant1";
        String ingredient2 = "IngredientInexistant2";
        String ingredient3 = "IngredientInexistant3";

        //Act
        Recette recette = laboratoire.trouverRecette(
                ingredient1,
                ingredient2,
                ingredient3
        );

        //Assert
        assertNull(recette);
    }

    @Test
    void fairePotion_Ingredient1_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.fairePotion(null, ingredient2Valide, ingredient3Valide);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void fairePotion_Ingredient2_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.fairePotion(ingredient1Valide, null, ingredient3Valide);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void fairePotion_Ingredient3_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.fairePotion(ingredient1Valide, ingredient2Valide, null);
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void fairePotion_RecettePresente()
    {
        //Act
        ResultatExperience resultat = laboratoire.fairePotion(
                ingredient1Valide,
                ingredient2Valide,
                ingredient3Valide
        );

        //Assert
        boolean resultatExiste = resultat != null;

        assertTrue(resultatExiste);
    }

    @Test
    void fairePotion_RecetteAbsente()
    {
        //Prepare
        String ingredient1 = "IngredientInexistant1";
        String ingredient2 = "IngredientInexistant2";
        String ingredient3 = "IngredientInexistant3";

        //Act
        ResultatExperience resultat = laboratoire.fairePotion(
                ingredient1,
                ingredient2,
                ingredient3
        );

        //Assert
        boolean resultatExiste = resultat != null;

        assertTrue(resultatExiste);
    }

    @Test
    void creerNouvellePotion_IngredientInexistant()
    {
        //Prepare
        String ingredient1 = "IngredientInexistant1";
        String ingredient2 = "IngredientInexistant2";
        String ingredient3 = "IngredientInexistant3";
        String nomRecette = "PotionTest";
        int difficulte = Recette.DIFFICULTE_MIN;
        int pointExperience = 100;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            laboratoire.creerNouvellePotion(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nomRecette,
                    difficulte,
                    pointExperience
            );
        });

        //Assert
        assertEquals(Laboratoire.MESSAGE_INGREDIENT_INEXISTANT, ex.getMessage());
    }

    @Test
    void creerNouvellePotion_RecetteExisteDeja()
    {
        //Prepare
        int nombreRecettesAvant = laboratoire.getRecettes().size();

        //Act
        ResultatExperience resultat = laboratoire.creerNouvellePotion(
                ingredient1Valide,
                ingredient2Valide,
                ingredient3Valide,
                "PotionTest",
                Recette.DIFFICULTE_MIN,
                100
        );

        //Assert
        boolean resultatExiste = resultat != null;

        assertTrue(resultatExiste);
        assertEquals(nombreRecettesAvant, laboratoire.getRecettes().size());
    }
}