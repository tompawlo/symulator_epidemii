package aplikacja;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.GroupLayout.SequentialGroup;

public class Panstwo {

	/*----- zmienne -----*/
	private int liczbaLudn;
	private int liczbaLudnKwar;
	private int liczbaChor;
	private int liczbaZgon;
	private int liczbaStadChor;
	private int ostStadiumIndex;
	private int szansaZarazenia;
	private int wskaznikMigracji;
	private double iloscSzczepProc;

	private boolean lek;

	private String nazwa;

	private int[] stadiumChoroby;
	public int[] emigranci = { 0, 0, 0, 0 };

	private final int STAD2 = 0;
	private final int STAD1 = 1;
	private final int KWARAN = 2;
	private final int ZDROWI = 3;

	/* ---- Konstruktor ----- */
	public Panstwo(String kraj, int populacja, int chorzy, int migracja, int stadia, int szansaZar) {
		setNazwa(kraj);
		// Choroba
		ostStadiumIndex = (stadia - 1); // index ostatniego stadium
		stadiumChoroby = new int[stadia];
		setLiczbaStadChor(stadia);

		chorzy = (int) (populacja * chorzy * 0.01);

		stadiumChoroby[0] = chorzy;
		setLiczbaChor(chorzy);
		populacja -= chorzy;
		setSzansaZarazenia(szansaZar);
		setIloscSzczepProc(0.00);
		setLiczbaLudnKwar(0);
		setLiczbaLudn(populacja);

		setWskaznikMigracji(migracja);
		setLek(false);

	}

	// *----- METODY -----*/
	// Zara�
	public void zarazanie() {
		Random rand = new Random();
		int zarazeni = 0;
		for (int i = 0; i < liczbaChor; i++) {
			if (rand.nextInt(101) < szansaZarazenia)
				zarazeni++;
		}
		// Nie mo�e si� pojawi� wi�cej Zara�onych ni� mamy ludzi
		if (liczbaLudn < zarazeni)
			zarazeni = liczbaLudn;
		liczbaLudn -= zarazeni;
		liczbaChor += zarazeni;
		stadiumChoroby[0] += zarazeni;

	}

	// Progresja Choroby
	public void progresjaChoroby() {

		liczbaZgon += stadiumChoroby[ostStadiumIndex];
		liczbaChor -= stadiumChoroby[ostStadiumIndex];
		stadiumChoroby[ostStadiumIndex] = 0;
		for (int i = ostStadiumIndex; i > 0; i--) {
			stadiumChoroby[i] = stadiumChoroby[i - 1];
		}
		stadiumChoroby[0] = 0;

	}

	// Wynajdz lek
	public boolean wynajdzLek() {
		if (!wynalezionoLek()) {
			if (((double) (((double) liczbaChor + (double) liczbaZgon)
					/ ((double) liczbaLudn + (double) liczbaChor + (double) liczbaZgon)) >= 0.10)) {
				setLek(true);
				return true;
			}
		}
		return false;
	}

	// Migruj
	public void migruj() {
		int kandydaci = liczbaLudn + liczbaLudnKwar + stadiumChoroby[0] + stadiumChoroby[1]; // kandydaci
																								// na
																								// migracje
		int kandydat = 0;
		// zerowanie tablicy migrant�w
		emigranci[STAD2] = 0;
		emigranci[STAD1] = 0;
		emigranci[KWARAN] = 0;
		emigranci[ZDROWI] = 0;

		Random rand = new Random(47);

		for (int i = 1; i <= (int) ((kandydaci * wskaznikMigracji) * 0.01); i++) {
			kandydat = rand.nextInt(kandydaci);

			if (kandydat <= stadiumChoroby[1]) { // STAD2
				if (stadiumChoroby[1] > 0) {
					stadiumChoroby[1]--;
					liczbaChor--;
					emigranci[STAD2]++;
				}

			} else if (kandydat < stadiumChoroby[0] + stadiumChoroby[1]) { // STAD1
				if (stadiumChoroby[0] > 0) {
					stadiumChoroby[0]--;
					liczbaChor--;
					emigranci[STAD1]++;
				}
			} else if (kandydat < stadiumChoroby[0] + stadiumChoroby[1] + liczbaLudnKwar) { // KWARAN
				if (liczbaLudnKwar > 0) {
					liczbaLudnKwar--;
					emigranci[KWARAN]++;
				}
			} else { // ZDROWI
				if (liczbaLudn > 0) {
					liczbaLudn--;
					emigranci[ZDROWI]++;
				}
			}
		}

	}

	// Przujmij imigrant�w
	public void przyjmijImigrantow(int grupa) {
		// PAMIETAJ!: STAD2 = 0; STAD1 = 1; KWARAN = 2; ZDROWI = 3
		if (grupa == 0) {
			liczbaChor++;
			stadiumChoroby[1]++;
		} else if (grupa == 1) {
			liczbaChor++;
			stadiumChoroby[0]++;
		} else if (grupa == 2) {
			liczbaLudnKwar++;
		} else {
			liczbaLudn++;
		}

	}

	// Lecz
	public void lecz() {
		if (wynalezionoLek()) {
			if (getIloscSzczepProc() < 0.50)
				setIloscSzczepProc(getIloscSzczepProc() + 0.05);

			Random rand = new Random(47);

			int kandydaci = liczbaLudn + stadiumChoroby[0] + stadiumChoroby[1]; // kandydaci
																				// na
																				// szczepienie
			int kandydat = 0;

			for (int i = 1; i <= (int) (kandydaci * getIloscSzczepProc()); i++) {
				kandydat = rand.nextInt(kandydaci);

				if (kandydat <= stadiumChoroby[1]) {
					if (stadiumChoroby[1] > 0) {
						if (!(rand.nextBoolean())) {
							stadiumChoroby[1]--;
							liczbaChor--;
							liczbaLudnKwar++;
						}
					}

				} else if (kandydat < stadiumChoroby[0] + stadiumChoroby[1]) {
					if (stadiumChoroby[0] > 0) {
						if (!(rand.nextBoolean())) {
							stadiumChoroby[0]--;
							liczbaChor--;
							liczbaLudnKwar++;
						}
					}
				} else {
					if (liczbaLudn > 0) {
						if (!(rand.nextBoolean())) {
							liczbaLudn--;
							liczbaLudnKwar++;
						}
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return nazwa;
	}

	/*----- gettery i settery -----*/
	// LiczbaLudn
	public int getLiczbaLudn() {
		return liczbaLudn;
	}

	public void setLiczbaLudn(int liczbaLudn) {
		this.liczbaLudn = liczbaLudn;
	}

	// LiczbaLudnKwar
	public int getLiczbaLudnKwar() {
		return liczbaLudnKwar;
	}

	public void setLiczbaLudnKwar(int liczbaLudnKwar) {
		this.liczbaLudnKwar = liczbaLudnKwar;
	}

	// liczbaChor
	public int getLiczbaChor() {
		return liczbaChor;
	}

	public void setLiczbaChor(int liczbaChor) {
		this.liczbaChor = liczbaChor;
	}

	// wska�nikMigracji
	public int getWskaznikMigracji() {
		return wskaznikMigracji;
	}

	public void setWskaznikMigracji(int wskaznikMigracji) {
		this.wskaznikMigracji = wskaznikMigracji;
	}

	// lek
	public boolean wynalezionoLek() {
		return lek;
	}

	public void setLek(boolean lek) {
		this.lek = lek;
	}

	// Nazwa Pa�stwa
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	// Liczba stadi�w choroby
	public int getLiczbaStadChor() {
		return liczbaStadChor;
	}

	public void setLiczbaStadChor(int liczbaStadChor) {
		this.liczbaStadChor = liczbaStadChor;
	}

	// Szansa Zara�enia
	public int getSzansaZarazenia() {
		return szansaZarazenia;
	}

	public void setSzansaZarazenia(int szansaZarazenia) {
		this.szansaZarazenia = szansaZarazenia;
	}

	// Liczba Zgon�w
	public int getLiczbaZgon() {
		return liczbaZgon;
	}

	public void setLiczbaZgon(int liczbaZgon) {
		this.liczbaZgon = liczbaZgon;
	}

	public double getIloscSzczepProc() {
		return iloscSzczepProc;
	}

	public void setIloscSzczepProc(double skutSzczep) {
		this.iloscSzczepProc = skutSzczep;
	}

	/* ----- G��wna ----- */
	public static void main(String[] args) {

	}

}
