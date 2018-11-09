import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class ATM  {

	static Scanner input = new Scanner (System.in);

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		load();
		glavniIzbornik();

	}

	public static void load() throws IOException, ClassNotFoundException {

		if (doesFileExists()) {

			FileInputStream in = new FileInputStream("racuni.txt");
			ObjectInputStream oin = new ObjectInputStream(in);

			try {
				while (true) 
					Racun.addOnLoad((Racun)oin.readObject());
			}
			catch (EOFException ex) {}

			oin.close();
			
		} else {
			@SuppressWarnings("unused")
			File file = new File ("racuni.txt");
		}

	}

	public static void glavniIzbornik() throws IOException {
		
		int opcija;

		System.out.println("---------- GLAVNI IZBORNIK ----------");
		System.out.println("1. KREIRANJE NOVOG RACUNA");
		System.out.println("2. TRANSFER NOVCA");
		System.out.println("3. ISPIS DETALJA POSTOJECEG RACUNA");
		System.out.println("4. IZLAZAK IZ APLIKACIJE");
		System.out.println("--------------------------------------");

		opcija = unosIntegera();

		while (opcija != 1 && opcija != 2 && opcija != 3 && opcija != 4) {
			System.out.println("Unesite ispravan unos opcije!");
			opcija = unosIntegera();
		}

		switch (opcija) {

		case 1: kreiranjeRacuna(); break;
		case 2: transferNovca(); break;
		case 3: ispisRacuna(); break;
		case 4: save(); System.exit(0);

		}

	}


	public static void kreiranjeRacuna() throws IOException {

		System.out.println("--------------------------------------");

		System.out.println("Unesite broj racuna: ");
		int brojRacuna = unosIntegera();


		System.out.println("Unesite iznos racuna: ");
		double iznosRacuna = unosDouble();

		System.out.println("Unesite vase ime: ");
		String ime = input.next();

		System.out.println("--------------------------------------");

		new Racun (brojRacuna, ime, iznosRacuna);

		glavniIzbornik();

	}

	public static void transferNovca () throws IOException {
		System.out.println("--------------TRANSFER NOVCA------------");

		System.out.println("Unesite broj source racuna: ");

		int sourceRacun = unosIntegera();

		while (sourceRacun < 0) {
			System.out.println("Unesite valjan unos broja source racuna.");
			sourceRacun = unosIntegera();
		}

		System.out.println("Unesite broj target racuna: ");

		int targetRacun = unosIntegera();

		while (sourceRacun < 0) {
			System.out.println("Unesite valjan unos broja target racuna.");
			targetRacun = unosIntegera();
		}


		System.out.println("Unesite iznos transfera: ");

		double iznosTransfera = unosDouble();

		while (iznosTransfera <= 0) {
			System.out.println("Unesite valjan iznos transfera!");
			iznosTransfera = unosDouble();
		}

		Racun.transferNovca(sourceRacun, targetRacun, iznosTransfera);

		System.out.println("--------------------------------------");

		glavniIzbornik();
	}

	public static void ispisRacuna () throws IOException {

		System.out.println("----------ISPIS RACUNA-----------");
		System.out.println("Unesite broj racuna: ");

		int number = unosIntegera();

		while (number < 0) {
			System.out.println("Unesite ispravan unos racuna: ");
			number = unosIntegera();
		}

		Racun.ispisRacuna(number);

		System.out.println("--------------------------------------");

		glavniIzbornik();

	}

	public static void save() throws IOException {


		FileOutputStream in = new FileOutputStream("racuni.txt");
		ObjectOutputStream oin = new ObjectOutputStream(in);

		for (int i = 0; i < Racun.getSizeOfList(); i++) 
			oin.writeObject(Racun.getRacun(i));
		
		oin.close();


	}

	public static boolean doesFileExists() throws IOException {
		try {
			FileInputStream test = new FileInputStream("racuni.txt");
			test.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	}

	public static int unosIntegera () {

		int uneseniBroj = 0;

		while (true)
			try {
				uneseniBroj = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("Unesite ispravan unos!");
				input.nextLine();
				continue;
			}

		return uneseniBroj;

	}

	public static double unosDouble () {

		double uneseniBroj = 0;

		while (true)
			try {
				uneseniBroj = input.nextDouble();
				break;
			} catch (Exception e) {
				System.out.println("Unesite ispravan unos!");
				input.nextLine();
				continue;
			}

		return uneseniBroj;

	}


}
