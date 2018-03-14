package aplikacja;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Plik {
	private String nazwaPlik;
	private File file;
	public Scanner skaner;

	public Plik(String nazwa) throws FileNotFoundException {
		nazwaPlik = nazwa;
		
			file = new File(nazwaPlik);
			skaner = new Scanner(file);


	}

	public boolean czyKoniecPliku() throws FileNotFoundException {
		if (skaner.hasNext()) {
			return false;
		}
		return true;
	}

	public String wczytajZmienne() throws FileNotFoundException {
		return skaner.next();
	}

	/*------------------------------- Wsp�lne dane ---------------------------*/
	// Migracja
	public int wczytajMigracja() {
		int wpisana = Integer.parseInt(skaner.next());
		if (wpisana < 0 || wpisana > 10)
			wpisana = 2;
		return wpisana;

	}

	// SzansaZara�enie
	public int wczytajSzansaZarazenia() {
		int wpisana = Integer.parseInt(skaner.next());
		if (wpisana < 20 || wpisana > 80)
			wpisana = 30;
		return wpisana;

	}

	// Stadia
	public int wczytajStadia() {
		int wpisana = Integer.parseInt(skaner.next());
		if (wpisana < 3 || wpisana > 5)
			wpisana = 3;
		return wpisana;

	}

	/*-------------------- Dane -----------------------------*/
	// Nazwa Pa�stwa
	public String wczytajNazwaPanstwa() {
		String wpisana = skaner.next();
		if (wpisana == "" || wpisana.length() > 10)
			wpisana = "Pa�stwoX";
		return wpisana;

	}

	// Liczba Ludno�ci
	public int wczytajPopulacja() {
		int wpisana = Integer.parseInt(skaner.next());
		if (wpisana < 300 || wpisana > 100000)
			wpisana = 10000;
		return wpisana;

	}

	// Liczba Chorych
	public int wczytajChorzy() {
		int wpisana = Integer.parseInt(skaner.next());
		if (wpisana < 0 || wpisana > 5)
			wpisana = 3;
		return wpisana;

	}

	public static void main(String[] args) {
		

	}

}
