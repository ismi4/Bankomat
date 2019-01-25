package ba.bildit.code;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Ismail Brkic
 * @version final_1
 *	<p>Klasa koja predstavlja racun bankomata</p>
 */
@SuppressWarnings("serial")
public class Racun implements Serializable {

	public static ArrayList<Racun> racuni = new ArrayList<>();

	private int brojRacuna;
	private String imeVlasnika;
	private double trenutnoStanje;

	public Racun() {

	}

	/**
	 * 
	 * @param brojRacuna
	 * @param imeVlasnika
	 * @param trenutnoStanje
	 * @throws IOException
	 * <p>Konstruktor za inicijalizaciju polja objekta tipa Racun</p>
	 */
	public Racun(int brojRacuna, String imeVlasnika, double trenutnoStanje) throws IOException {

		if (checkForCreation(brojRacuna, trenutnoStanje)) {
			this.brojRacuna = brojRacuna;
			this.imeVlasnika = imeVlasnika;
			this.trenutnoStanje = trenutnoStanje;

			racuni.add(this);

			System.out.println("Racun je uspjesno kreiran!");

		} else
			System.out.println("Racun nije uspjesno kreiran!");
	}

	/**
	 * 
	 * @param brojRacuna
	 * @return boolean
	 * <p>Metoda koja ispisujue racun</p>
	 */
	public static boolean ispisRacuna(int brojRacuna) {

		for (int i = 0; i < racuni.size(); i++)
			if (racuni.get(i).brojRacuna == brojRacuna) {
				System.out.println(racuni.get(i).toString());
				return true;
			}

		System.out.println("Unijeti racun nije pronadjen.");
		return false;
	}

	@Override
	public String toString() {
		return "\n Broj racuna: " + brojRacuna + "\n Ime: " + imeVlasnika + "\n Trenutno stanje: " + trenutnoStanje;
	}

	/**
	 * 
	 * @param brojRacuna
	 * @param trenutnoStanje
	 * @return boolean
	 * <p>Metoda koja provjerava da li racun moze biti kreiran sa proslijedjenim poljima</p>
	 */
	public static boolean checkForCreation(int brojRacuna, double trenutnoStanje) {

		if (brojRacuna < 0) {
			System.out.println("Racun nije kreiran jer broj racuna ne moze biti negativan broj.");
			return false;
		}

		if (trenutnoStanje < 0) {
			System.out.println("Racun nije kreiran jer trenutno stanje ne moze biti negativan broj.");
			return false;	
		}

		for (int i = 0; i < racuni.size(); i++)
			if (racuni.get(i).brojRacuna == brojRacuna) {
				System.out.println("Racun nije kreiran jer unijeti broj racuna vec postoji.");
				return false;
			}

		return true;




	}
/**
 * 
 * @param sourceRacun
 * @param targetRacun
 * @param iznos
 * @return boolean
 * <p>Metoda koja provjerava da li transfer moze biti izvrsen sa proslijedjenim poljima </p>
 */
	public static boolean checkForTransfer (int sourceRacun, int targetRacun, double iznos) {

		boolean sourceExists = false;
		boolean targetExists = false;
		boolean enoughMoney = false;


		for (int i = 0; i < racuni.size(); i++) {

			if (racuni.get(i).brojRacuna == sourceRacun) {

				sourceExists = true;

				if ((racuni.get(i).trenutnoStanje - iznos) >= 0)
					enoughMoney = true;

			}

			if (racuni.get(i).brojRacuna == targetRacun)
				targetExists = true;

		}


		if (!sourceExists) 
			System.out.println("Unijeti broj racuna za slanje ne postoji.");
		if (!targetExists) 
			System.out.println("Unijeti broj racuna za primanje ne postoji.");
		if (!enoughMoney)
			System.out.println("Nema dovoljno novca za transfer.");

		if (!sourceExists || !targetExists || !enoughMoney)
			return false;
		else
			return true;




	}

	/**
	 * 
	 * @param sourceRacun
	 * @param targetRacun
	 * @param iznos
	 * @return boolean
	 * <p>Metoda koja izvrsava transfer novca</p>
	 */
	public static boolean transferNovca (int sourceRacun, int targetRacun, double iznos) {

		if (checkForTransfer(sourceRacun, targetRacun, iznos)) {

			for (int i = 0; i < racuni.size(); i++) {

				if (racuni.get(i).brojRacuna == sourceRacun)
					racuni.get(i).trenutnoStanje -= iznos;

				if (racuni.get(i).brojRacuna == targetRacun)
					racuni.get(i).trenutnoStanje += iznos;
			}

			
			
			System.out.println("Transfer je uspjesno izvrsen!");
			return true; 

		} else {
			System.out.println("Transfer nije uspjesno izvrsen!");
			return false;
		}


	}

	/**
	 * 
	 * @param racun
	 * <p>Metoda koja pri ucitavanju file-a citajuci objekt tipa Racun dodaje ga listi objekata</p>
	 */
	public static void addOnLoad(Racun racun) {
		racuni.add(racun);
	}

	/**
	 * 
	 * @param i
	 * @return Racun
	 * <p>Getter za Racun</p>
	 */
	public static Racun getRacun(int i) {
		return racuni.get(i);
	}
	
	/**
	 * 
	 * @return int
	 * <p>Getter za brojRacuna</p>
	 */
	public int getBrojRacuna() {
		return this.brojRacuna;
	}
	
	/**
	 * 
	 * @return int
	 * <p>Getter za trenutnoStanje</p>
	 */
	

	/**
	 * 
	 * @return int
	 * <p>Getter za velicinu liste</p>
	 */
	public static int getSizeOfList() {
		return racuni.size();
	}




}
