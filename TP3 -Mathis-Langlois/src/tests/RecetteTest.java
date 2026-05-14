/**
 * Auteur : Mathis Langlois
 */

package tests;

import logique.Ingredient;
import logique.Recette;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;



public class RecetteTest {

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

    private Recette recette = null;

    private Ingredient ingredient1 = null;
    private Ingredient ingredient2 = null;
    private Ingredient ingredient3 = null;

    private String nomRecetteValide = "";
    private int difficulteValide = 0;
    private int pointExperienceValide = 0;

    private String alphabet = "";
    private Random random = null;

    @BeforeEach
    void setUp()
    {
        //Arrange
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        random = new Random();

        ingredient1 = new Ingredient("Mandragore", 10);
        ingredient2 = new Ingredient("Licorne", 20);
        ingredient3 = new Ingredient("Poudre", 30);

        nomRecetteValide = genererStringAleatoire(Recette.MIN_CARACTERES_NOM_RECETTE);
        difficulteValide = 3;
        pointExperienceValide = 100;

        recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                nomRecetteValide,
                difficulteValide,
                pointExperienceValide
        );
    }
    @Test
    void constructeur_Ingredient1_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    null,
                    ingredient2,
                    ingredient3,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void constructeur_Ingredient2_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    null,
                    ingredient3,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void constructeur_Ingredient3_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    null,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void constructeur_Ingredient_Double()
    {
        //Prepare
        Ingredient ingredientDouble = new Ingredient("Mandragore", 15);

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredientDouble,
                    ingredient3,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_INGREDIENT_DOUBLE, ex.getMessage());
    }

    @Test
    void constructeur_Nom_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    null,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void constructeur_Nom_PasAssezCaracteres()
    {
        //Prepare
        String nom = genererStringAleatoire(Recette.MIN_CARACTERES_NOM_RECETTE - 1);

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nom,
                    difficulteValide,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    void constructeur_Nom_AssezCaracteres()
    {
        //Prepare
        String nom = genererStringAleatoire(Recette.MIN_CARACTERES_NOM_RECETTE);

        //Act
        recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                nom,
                difficulteValide,
                pointExperienceValide
        );

        //Assert
        assertEquals(nom, recette.getNom());
    }

    @Test
    void constructeur_Difficulte_TropPetite()
    {
        //Prepare
        int difficulte = Recette.DIFFICULTE_MIN - 1;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nomRecetteValide,
                    difficulte,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_DIFFICULTE_INVALIDE, ex.getMessage());
    }

    @Test
    void constructeur_Difficulte_TropGrande()
    {
        //Prepare
        int difficulte = Recette.DIFFICULTE_MAX + 1;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nomRecetteValide,
                    difficulte,
                    pointExperienceValide
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_DIFFICULTE_INVALIDE, ex.getMessage());
    }
    @Test
    void constructeur_Difficulte_Min_Valide()
    {
        //Prepare
        int difficulte = Recette.DIFFICULTE_MIN;

        //Act
        recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                nomRecetteValide,
                difficulte,
                pointExperienceValide
        );

        //Assert
        assertEquals(difficulte, recette.getDifficulte());
    }

    @Test
    void constructeur_Difficulte_Max_Valide()
    {
        //Prepare
        int difficulte = Recette.DIFFICULTE_MAX;

        //Act
        recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                nomRecetteValide,
                difficulte,
                pointExperienceValide
        );

        //Assert
        assertEquals(difficulte, recette.getDifficulte());
    }

    @Test
    void constructeur_PointExperience_Zero()
    {
        //Prepare
        int pointExperience = 0;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperience
            );
        });
        //Assert
        assertEquals(Recette.MESSAGE_POINT_EXPERIENCE_INVALIDE, ex.getMessage());
    }

    @Test
    void constructeur_PointExperience_Negatif()
    {
        //Prepare
        int pointExperience = -1;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette = new Recette(
                    ingredient1,
                    ingredient2,
                    ingredient3,
                    nomRecetteValide,
                    difficulteValide,
                    pointExperience
            );
        });

        //Assert
        assertEquals(Recette.MESSAGE_POINT_EXPERIENCE_INVALIDE, ex.getMessage());
    }

    @Test
    void constructeur_PointExperience_Valide()
    {
        //Assert
        assertEquals(pointExperienceValide, recette.getPointExperience());
    }

    @Test
    void obtenirPrix_Valide()
    {
        //Act
        int prix = recette.obtenirPrix();

        //Assert
        assertEquals(60, prix);
    }

    @Test
    void contientIngredient_Nom_Null()
    {
        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            recette.contientIngredient(null);
        });

        //Assert
        assertEquals(Recette.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());
    }

    @Test
    void contientIngredient_IngredientPresent()
    {
        //Act
        boolean resultat = recette.contientIngredient("Mandragore");

        //Assert
        assertTrue(resultat);
    }

    @Test
    void contientIngredient_IngredientAbsent()
    {
        //Act
        boolean resultat = recette.contientIngredient("Dragon");

        //Assert
        assertFalse(resultat);
    }

    @Test
    void toString_Valide()
    {
        //Prepare
        recette = new Recette(
                ingredient1,
                ingredient2,
                ingredient3,
                "PotionTest",
                difficulteValide,
                pointExperienceValide
        );

        //Act
        String resultat = recette.toString();

        //Assert
        assertEquals("PotionTest|Mandragore|Licorne|Poudre|3|100", resultat);
    }

}
