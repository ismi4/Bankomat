package ba.bildit.test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ba.bildit.code.Racun;



public class RacunTest {
	
	Racun test;
	Racun posiljaoc;
	Racun primatelj;
	
	@Before
	public void setUp() throws IOException{
		test = new Racun(1, "Test", 100);
		posiljaoc = new Racun(2, "Posiljaoc", 100);
		primatelj = new Racun(3, "Primatelj", 100);
	}
	
	@After
	public void tearDown() throws IOException{
		
	}
	
	@Test
	public void ispisRacuna_racunPostoji_vracaTrue() throws IOException {
		assertTrue(Racun.ispisRacuna(1));
	}
	
	@Test
	public void ispisRacuna_racunNePostoji_vracaFalse() throws IOException {
		
		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 1) 
				Racun.racuni.remove(i);
			
		assertFalse(Racun.ispisRacuna(1));
	}
	
	@Test
	public void checkForCreation_negativanBrojRacuna_vracaFalse() {
		assertFalse(Racun.checkForCreation(-500, 0));
	}

	@Test
	public void checkForCreation_negativanBrojTrenutnogStanja_vracaFalse() {
		assertFalse(Racun.checkForCreation(0, -500));
	}
	
	@Test
	public void checkForCreation_racunVecPostoji_vracaFalse() {
		assertFalse(Racun.checkForCreation(1, 0));
	}

	@Test
	public void checkForCreation_ispravniSviUslovi_vracaTrue() {
		
		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 2) 
				Racun.racuni.remove(i);
		
		assertTrue(Racun.checkForCreation(2, 0));
	}
	
	@Test
	public void checkForTransfer_racunPosiljaocaNePostoji_vracaFalse() {

		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 2) 
				Racun.racuni.remove(i);
		
	assertFalse(Racun.checkForTransfer(2, 3, 10));
	
	}
	
	@Test
	public void checkForTransfer_racunPrimateljaNePostoji_vracaFalse() {
		
		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 3) 
				Racun.racuni.remove(i);
		
	assertFalse(Racun.checkForTransfer(2, 3, 10));
		
	}
	
	@Test
	public void checkForTransfer_nemaDovoljnoNovcaNaRacunuPosiljaoca_vracaFalse() {
		
		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 2) 
				Racun.racuni.remove(i);
		
	assertFalse(Racun.checkForTransfer(2, 3, 10000));
		
	}
	
	@Test
	public void checkForTransfer_ispravniSviUslovi_vracaTrue() {
		assertTrue(Racun.checkForTransfer(2, 3, 10));
	}
	
	@Test
	public void transferNovca_nijeIzvrsenTransfer_vracaFalse() {
		
		for (int i = 0; i < Racun.racuni.size(); i++)
			if (Racun.getRacun(i).getBrojRacuna() == 3) 
				Racun.racuni.remove(i);
		
		
		assertFalse(Racun.transferNovca(2, 3, 10));
		
	}
	
	@Test
	public void transferNovca_uspjesnoIzvrsenTransfer_vracaTrue() {
		assertTrue(Racun.transferNovca(2, 3, 10));
	}



}
