package test.Boutique;

import main.App;
import main.Joueur;
import main.Boutique.Boutique;
import main.Consommable.Bonheur;
import main.Roulette.Roulette;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BoutiqueTest {

    Boutique b;
    private Joueur joueurAppInitiale;

    @BeforeAll
    public static void beforeAllTests() {
        System.out.println("Start test series");
    }

    @AfterAll
	public static void afterAllTests() {
        System.out.println("End test series");
    }

    @BeforeEach
	public void beforeATest() {
		Roulette.setResultat(25);

        joueurAppInitiale = App.joueur;
        Joueur j1 = new Joueur("test",40,50,30,1);
        App.joueur = j1;
	}

    @AfterEach
	public void afterATest() {
        App.joueur = joueurAppInitiale;
        System.out.println("--------- end of a test---------");
    }

    @Test
    public void acheterNourritureTest(){

    }

    @Test
    public void acheterBonheurTest(){}

    @Test
    public void acheterToutTest(){}
}
