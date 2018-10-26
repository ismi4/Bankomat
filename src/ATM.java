import java.util.Scanner;

public class ATM {

	static Scanner input = new Scanner (System.in);
	
	public static void main(String[] args) {
		
		glavniIzbornik();
		
	}
	
	public static void glavniIzbornik() {
		int opcija;
		
		System.out.println("---------- GLAVNI IZBORNIK ----------");
	    System.out.println("1. KREIRANJE NOVOG RACUNA");
	    System.out.println("2. TRANSFER NOVCA");
		System.out.println("3. ISPIS DETALJA POSTOJECEG RACUNA");
		System.out.println("--------------------------------------");
		
		opcija = input.nextInt();
		
		while (opcija != 1 && opcija != 2 && opcija != 3) {
			System.out.println("Unesite ispravan unos opcije!");
			opcija = input.nextInt();
		}
		
		switch (opcija) {
		
		case 1: kreiranjeRacuna(); break;
		case 2: transferNovca(); break;
		case 3: ispisRacuna(); break;
			
		}

	}
	
	
	public static void kreiranjeRacuna() {
		
		System.out.println("--------------------------------------");
		
		System.out.println("Unesite broj racuna: ");
		int brojRacuna = input.nextInt();
		
		
		System.out.println("Unesite iznos racuna: ");
		double iznosRacuna = input.nextDouble();
		
		System.out.println("Unesite vase ime: ");
		String ime = input.next();
		
		System.out.println("--------------------------------------");
		
		Racun racun = new Racun (brojRacuna, ime, iznosRacuna);
			
		glavniIzbornik();
		
		}
	
	public static void transferNovca () {
		System.out.println("--------------TRANSFER NOVCA------------");
		
		System.out.println("Unesite broj source racuna: ");
		
		int sourceRacun = input.nextInt();
		
		while (sourceRacun < 0) {
			System.out.println("Unesite valjan unos broja source racuna.");
			sourceRacun = input.nextInt();
		}
		
		System.out.println("Unesite broj target racuna: ");
		
		int targetRacun = input.nextInt();
		
		while (sourceRacun < 0) {
			System.out.println("Unesite valjan unos broja target racuna.");
			targetRacun = input.nextInt();
		}
		
		
		System.out.println("Unesite iznos transfera: ");
		
		double iznosTransfera = input.nextDouble();
		
		while (iznosTransfera <= 0) {
			System.out.println("Unesite valjan iznos transfera!");
			iznosTransfera = input.nextDouble();
		}
		
		Racun.transferNovca(sourceRacun, targetRacun, iznosTransfera);
		
		System.out.println("--------------------------------------");
		
		glavniIzbornik();
	}
	
	public static void ispisRacuna () {
		
		System.out.println("----------ISPIS RACUNA-----------");
		System.out.println("Unesite broj racuna: ");
		
		int number = input.nextInt();
		
		while (number < 0) {
			System.out.println("Unesite ispravan unos racuna: ");
			number = input.nextInt();
		}
		
		Racun.ispisRacuna(number);
		
		System.out.println("--------------------------------------");
		
		glavniIzbornik();
		
	}
				
		
	}


