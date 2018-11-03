import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class Racun implements Serializable {

	private static ArrayList<Racun> racuni = new ArrayList<>();
	
	private int brojRacuna;
	private String imeVlasnika;
	private double trenutnoStanje;
	
	public Racun() {
		
	}
	
	public Racun(int brojRacuna, String imeVlasnika, double trenutnoStanje) throws IOException {
	
			if (checkForCreation(brojRacuna, trenutnoStanje)) {
					this.brojRacuna = brojRacuna;
					this.imeVlasnika = imeVlasnika;
					this.trenutnoStanje = trenutnoStanje;
		
					racuni.add(this);
					File file = new File("racuni.txt");
					FileOutputStream in = new FileOutputStream(file);
					ObjectOutputStream oin = new ObjectOutputStream(in);
					oin.writeObject(this);
					System.out.println("Racun je uspjesno kreiran!");
			
			} else
					System.out.println("Racun nije uspjesno kreiran!");
	}
	
	public static void ispisRacuna(int brojRacuna) {
		
		for (int i = 0; i < racuni.size(); i++)
			if (racuni.get(i).brojRacuna == brojRacuna) {
				System.out.println(racuni.get(i).toString());
				return;
			}
		
		System.out.println("Unijeti racun nije pronadjen.");
	}
	
	@Override
	public String toString() {
		return "\n Broj racuna: " + brojRacuna + "\n Ime: " + imeVlasnika + "\n Trenutno stanje: " + trenutnoStanje;
	}

	private boolean checkForCreation(int brojRacuna, double trenutnoStanje) {
		
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
	
	private static boolean checkForTransfer (int sourceRacun, int targetRacun, double iznos) {
		
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
	
	public static void transferNovca (int sourceRacun, int targetRacun, double iznos) {
		
		if (checkForTransfer(sourceRacun, targetRacun, iznos)) {
		
		for (int i = 0; i < racuni.size(); i++) {
			
			if (racuni.get(i).brojRacuna == sourceRacun)
				racuni.get(i).trenutnoStanje -= iznos;
			
			if (racuni.get(i).brojRacuna == targetRacun)
				racuni.get(i).trenutnoStanje += iznos;
		}
			
		System.out.println("Transfer je uspjesno izvrsen!");
		
		} else
			System.out.println("Transfer nije uspjesno izvrsen!");
		
		
	}
	
	public static void addOnLoad(Racun racun) {
		racuni.add(racun);
	}
	
	
	
	
}
