/**
 * Auteur : Mathis Langlois
 */

package tests;

import logique.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    private Ingredient ingredient  = null;
    private String nomValide = "";
    private int prixValide = 0;
    private String alphabet ="";
    private Random random = null;

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
    void setUp(){

        //Arrange
        alphabet = "abcdefghijklmnopqrstuvwxyz";
        random = new Random();

        nomValide = genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM );
        prixValide = 10;

        ingredient = new Ingredient(nomValide, prixValide);

    }

    @Test
    void construncteur_Nom_Null(){

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () ->{
            ingredient = new Ingredient(null, prixValide);
        });

//Asssert
        assertEquals(Ingredient.MESSAGE_VALEUR_NULL_NON_PERMISE, ex.getMessage());

    }

    @Test
    void constructeur_Nom_PasAssezCaracteres(){
        //Arrange
        String nom =genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM - 1);

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            ingredient = new Ingredient(nom, prixValide);
        });
        //Assert
        assertEquals(Ingredient.MESSAGE_NOM_TROP_COURT, ex.getMessage());
    }

    @Test
    void constructeur_Nom_AssezCaracteres(){
        //Arrange
        String nom =genererStringAleatoire(Ingredient.MIN_CARACTERS_NOM );

        //Act

        ingredient = new Ingredient(nom, prixValide);

    }

    @Test
    void constructeur_Prix_Zero()
    {
        //Prepare
        int prix = 0;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            ingredient = new Ingredient(nomValide, prix);
        });

        //Assert
        assertEquals(Ingredient.MESSAGE_PRIX_INVALIDE, ex.getMessage());
    }

    @Test
    void constructeur_Prix_Negatif(){

        //Prepare
        int prix =-1;

        //Act
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            ingredient = new Ingredient(nomValide, prix);
        });
        //Assert
        assertEquals(Ingredient.MESSAGE_PRIX_INVALIDE, ex.getMessage());

    }
    @Test
    void constructeur_Nom_Valide()
    {
        //Assert
        assertEquals(nomValide, ingredient.getNom());
    }

    @Test
    void constructeur_Prix_Valide()
    {
        //Assert
        assertEquals(prixValide, ingredient.getPrix());
    }

    @Test
    void toString_Valide()
    {
        //Prepare
        String nom = "Poudre";
        int prix = 15;
        ingredient = new Ingredient(nom, prix);

        //Act
        String resultat = ingredient.toString();

        //Assert
        assertEquals("Ingredient{nom=Poudre, prix=15}", resultat);
    }

}
