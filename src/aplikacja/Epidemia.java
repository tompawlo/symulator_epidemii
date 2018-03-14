package aplikacja;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Epidemia extends JFrame implements ActionListener {

	private JButton startReczny, startPlik, oProgramie, nastepny, automat;
	private JPanel glownyObraz;
	private JLabel lTytu , lNazwaKraju, lPopulacja, lZdrowi, lChorzy, lZaszczepieni, lZgony, lSzczep;
	private JLabel lMigracja, lSzansaZar, lStadia, lTydzien;
	private JLabel lMigracjaDane, lSzansaZarDane, lStadiaDane, lTydzienDane;
	private JLabel lTytul1, lNazwaKraju1, lPopulacja1, lZdrowi1, lChorzy1, lZaszczepieni1, lZgony1, lSzczep1;
	private JLabel lTytul2, lNazwaKraju2, lPopulacja2, lZdrowi2, lChorzy2, lZaszczepieni2, lZgony2, lSzczep2;
	private JLabel lTytul3, lNazwaKraju3, lPopulacja3, lZdrowi3, lChorzy3, lZaszczepieni3, lZgony3, lSzczep3;
	private OknoDanych dane;
	private OknoDanychWspolnych daneWspolne;

	private Panstwo krajI, krajII, krajIII;
	private int migracja, szansaZar, stadia, tydzien;

	private final int STAD2 = 0;
	private final int STAD1 = 1;
	private final int KWARAN = 2;
	private final int ZDROWI = 3;

	private XYSeries wykrKrajIChor, wykrKrajIIChor, wykrKrajIIIChor;
	private XYSeries wykrKrajIPop, wykrKrajIIPop, wykrKrajIIIPop;
	private XYSeriesCollection dataset;

	private Plik plik;
	private OknoPlik oknoPlik;
	private boolean otwartoOknoPlik, symulacjaZakonczona;
	private static int licznikSym = 0;

	/** ---------- Konstruktor ---------- */
	public Epidemia() {

		tydzien = 0;
		setOtwartoOknoPlik(false);

		// G owne okno
		setSize(1600, 900);
		setTitle("Epidemia");
		setLayout(null);
		// Przyciski
		startReczny = new JButton("Wpisz recznie");
		startReczny.setBounds(30, 30, 140, 70);
		startReczny.setFont(new Font("Times New Roman", Font.BOLD, 18));
		startReczny.addActionListener(this);
		add(startReczny);

		startPlik = new JButton("Wczytaj plik");
		startPlik.setBounds(30, 120, 140, 70);
		startPlik.setFont(new Font("Times New Roman", Font.BOLD, 18));
		startPlik.addActionListener(this);
		add(startPlik);

		oProgramie = new JButton("O Programie");
		oProgramie.setBounds(200, 30, 140, 70);
		oProgramie.setFont(new Font("Times New Roman", Font.BOLD, 18));
		oProgramie.addActionListener(this);
		add(oProgramie);

		nastepny = new JButton("Nastepny tydzien");
		nastepny.setBounds(370, 30, 140, 70);
		nastepny.setFont(new Font("Times New Roman", Font.BOLD, 12));
		nastepny.setEnabled(false);
		nastepny.addActionListener(this);
		add(nastepny);

		automat = new JButton("Automat");
		automat.setBounds(540, 30, 140, 70);
		automat.setFont(new Font("Times New Roman", Font.BOLD, 16));
		automat.setEnabled(false);
		automat.addActionListener(this);
		add(automat);

		/*-------------------- TABELA --------------------*/

		lTytu  = new JLabel("Symulator Epidemii", JLabel.CENTER);
		lTytu .setBounds(800, 50, 700, 100);
		lTytu .setFont(new Font("Times New Roman", Font.BOLD, 66));
		add(lTytu );

		// KRAJ I
		lTytul1 = new JLabel("Kraj I", JLabel.CENTER);
		lTytul1.setBounds(950, 250, 150, 40);
		lTytul1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lTytul1);

		lNazwaKraju1 = new JLabel("0", JLabel.CENTER);
		lNazwaKraju1.setBounds(950, 290, 150, 40);
		lNazwaKraju1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lNazwaKraju1);

		lPopulacja1 = new JLabel("0", JLabel.CENTER);
		lPopulacja1.setBounds(950, 330, 150, 40);
		lPopulacja1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lPopulacja1);

		lZdrowi1 = new JLabel("0", JLabel.CENTER);
		lZdrowi1.setBounds(950, 370, 150, 40);
		lZdrowi1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZdrowi1);

		lChorzy1 = new JLabel("0", JLabel.CENTER);
		lChorzy1.setBounds(950, 410, 150, 40);
		lChorzy1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lChorzy1);

		lZaszczepieni1 = new JLabel("0", JLabel.CENTER);
		lZaszczepieni1.setBounds(950, 450, 150, 40);
		lZaszczepieni1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZaszczepieni1);

		lZgony1 = new JLabel("0", JLabel.CENTER);
		lZgony1.setBounds(950, 490, 150, 40);
		lZgony1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZgony1);

		lSzczep1 = new JLabel("Brak", JLabel.CENTER);
		lSzczep1.setBounds(950, 530, 150, 40);
		lSzczep1.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(lSzczep1);

		// KRAJ II
		lTytul2 = new JLabel("Kraj II", JLabel.CENTER);
		lTytul2.setBounds(1100, 250, 150, 40);
		lTytul2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lTytul2.setBackground(Color.RED);
		add(lTytul2);

		lNazwaKraju2 = new JLabel("0", JLabel.CENTER);
		lNazwaKraju2.setBounds(1100, 290, 150, 40);
		lNazwaKraju2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lNazwaKraju2);

		lPopulacja2 = new JLabel("0", JLabel.CENTER);
		lPopulacja2.setBounds(1100, 330, 150, 40);
		lPopulacja2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lPopulacja2);

		lZdrowi2 = new JLabel("0", JLabel.CENTER);
		lZdrowi2.setBounds(1100, 370, 150, 40);
		lZdrowi2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZdrowi2);

		lChorzy2 = new JLabel("0", JLabel.CENTER);
		lChorzy2.setBounds(1100, 410, 150, 40);
		lChorzy2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lChorzy2);

		lZaszczepieni2 = new JLabel("0", JLabel.CENTER);
		lZaszczepieni2.setBounds(1100, 450, 150, 40);
		lZaszczepieni2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZaszczepieni2);

		lZgony2 = new JLabel("0", JLabel.CENTER);
		lZgony2.setBounds(1100, 490, 150, 40);
		lZgony2.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZgony2);

		lSzczep2 = new JLabel("Brak", JLabel.CENTER);
		lSzczep2.setBounds(1100, 530, 150, 40);
		lSzczep2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(lSzczep2);

		// Kraj III
		lTytul3 = new JLabel("Kraj III", JLabel.CENTER);
		lTytul3.setBounds(1250, 250, 150, 40);
		lTytul3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lTytul3.setBackground(Color.RED);
		add(lTytul3);

		lNazwaKraju3 = new JLabel("0", JLabel.CENTER);
		lNazwaKraju3.setBounds(1250, 290, 150, 40);
		lNazwaKraju3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lNazwaKraju3);

		lPopulacja3 = new JLabel("0", JLabel.CENTER);
		lPopulacja3.setBounds(1250, 330, 150, 40);
		lPopulacja3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lPopulacja3);

		lZdrowi3 = new JLabel("0", JLabel.CENTER);
		lZdrowi3.setBounds(1250, 370, 150, 40);
		lZdrowi3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZdrowi3);

		lChorzy3 = new JLabel("0", JLabel.CENTER);
		lChorzy3.setBounds(1250, 410, 150, 40);
		lChorzy3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lChorzy3);

		lZaszczepieni3 = new JLabel("0", JLabel.CENTER);
		lZaszczepieni3.setBounds(1250, 450, 150, 40);
		lZaszczepieni3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZaszczepieni3);

		lZgony3 = new JLabel("0", JLabel.CENTER);
		lZgony3.setBounds(1250, 490, 150, 40);
		lZgony3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(lZgony3);

		lSzczep3 = new JLabel("Brak", JLabel.CENTER);
		lSzczep3.setBounds(1250, 530, 150, 40);
		lSzczep3.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(lSzczep3);

		// Opisy

		lNazwaKraju = new JLabel("Nazwa Panstwa", JLabel.RIGHT);
		lNazwaKraju.setBounds(770, 290, 180, 40);
		lNazwaKraju.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lNazwaKraju);

		lPopulacja = new JLabel("Liczba ludno ci", JLabel.RIGHT);
		lPopulacja.setBounds(770, 330, 180, 40);
		lPopulacja.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lPopulacja);

		lZdrowi = new JLabel("Liczba zdrowych", JLabel.RIGHT);
		lZdrowi.setBounds(770, 370, 180, 40);
		lZdrowi.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lZdrowi);

		lChorzy = new JLabel("Liczba zara onych", JLabel.RIGHT);
		lChorzy.setBounds(770, 410, 180, 40);
		lChorzy.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lChorzy);

		lZaszczepieni = new JLabel("Liczba zaszczepionych", JLabel.RIGHT);
		lZaszczepieni.setBounds(770, 450, 180, 40);
		lZaszczepieni.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lZaszczepieni);

		lZgony = new JLabel("Liczba zgonow", JLabel.RIGHT);
		lZgony.setBounds(770, 490, 180, 40);
		lZgony.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lZgony);

		lSzczep = new JLabel("Szczepionka", JLabel.RIGHT);
		lSzczep.setBounds(770, 530, 180, 40);
		lSzczep.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lSzczep);

		// Wpolny opis

		lMigracja = new JLabel("Migracja (%)", JLabel.CENTER);
		lMigracja.setBounds(950, 690, 150, 40);
		lMigracja.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lMigracja);

		lSzansaZar = new JLabel("Szansa zar. (%)", JLabel.CENTER);
		lSzansaZar.setBounds(1100, 690, 150, 40);
		lSzansaZar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lSzansaZar);

		lStadia = new JLabel("Stadia choroby", JLabel.CENTER);
		lStadia.setBounds(1250, 690, 150, 40);
		lStadia.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lStadia);

		lTydzien = new JLabel("Tydzien", JLabel.CENTER);
		lTydzien.setBounds(1400, 690, 150, 40);
		lTydzien.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lTydzien);

		// Wspone dane

		lMigracjaDane = new JLabel("-", JLabel.CENTER);
		lMigracjaDane.setBounds(950, 730, 150, 40);
		lMigracjaDane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lMigracjaDane);

		lSzansaZarDane = new JLabel("-", JLabel.CENTER);
		lSzansaZarDane.setBounds(1100, 730, 150, 40);
		lSzansaZarDane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lSzansaZarDane);

		lStadiaDane = new JLabel("-", JLabel.CENTER);
		lStadiaDane.setBounds(1250, 730, 150, 40);
		lStadiaDane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lStadiaDane);

		lTydzienDane = new JLabel("-", JLabel.CENTER);
		lTydzienDane.setBounds(1400, 730, 150, 40);
		lTydzienDane.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lTydzienDane);

		// Obraz
		glownyObraz = new Obraz("Countries0.png");
		glownyObraz.setBounds(0, 200, 814, 627); // po o enie xy i wymiary
													// obrazka!
		add(glownyObraz);

	}

	// Wprowadza dane recznie
	private void wprowadzDane() {

		if (krajI == null && krajII == null && krajIII == null) {
			daneWspolne = new OknoDanychWspolnych(this);
			daneWspolne.setVisible(true);
			migracja = daneWspolne.getMigracja();
			szansaZar = daneWspolne.getSzansaZar();
			stadia = daneWspolne.getStadia();

		}

		if (krajI == null) {
			dane = new OknoDanych(this, "CountryI.png");
			dane.setVisible(true);

			if (dane.isOK()) {
				krajI = dane.stworzPanstwo(migracja, szansaZar, stadia);
			}
		}

		// Kraj II
		if (krajII == null) {
			dane = new OknoDanych(this, "CountryII.png");
			dane.setVisible(true);
			if (dane.isOK()) {
				krajII = dane.stworzPanstwo(migracja, szansaZar, stadia);
			}
		}

		// Kraj III
		if (krajIII == null) {
			dane = new OknoDanych(this, "CountryIII.png");
			dane.setVisible(true);
			if (dane.isOK()) {
				krajIII = dane.stworzPanstwo(migracja, szansaZar, stadia);
			}
		}
		if (krajI != null && krajII != null && krajIII != null) {
			tydzien = 0;
			startReczny.setEnabled(false);
			startPlik.setEnabled(false);
			nastepny.setEnabled(true);
			automat.setEnabled(true);
			wpiszWspolneDaneDoTabeli();
			wpiszDaneDoTabeli();
			stworzDaneWykres();
			lSzczep1.setText("Brak");
			lSzczep2.setText("Brak");
			lSzczep3.setText("Brak");
		}
	}

	// MIGRACJA
	public void migracja() {
		krajI.migruj();
		krajII.migruj();
		krajIII.migruj();

		// PAMIETAJ!: STAD2 = 0; STAD1 = 1; KWARAN = 2; ZDROWI = 3

		int liczbaStad2 = krajI.emigranci[STAD2] + krajII.emigranci[STAD2] + krajIII.emigranci[STAD2];
		int liczbaStad1 = krajI.emigranci[STAD1] + krajII.emigranci[STAD1] + krajIII.emigranci[STAD1];
		int liczbaKwaran = krajI.emigranci[KWARAN] + krajII.emigranci[KWARAN] + krajIII.emigranci[KWARAN];
		int liczbaZdrowi = krajI.emigranci[ZDROWI] + krajII.emigranci[ZDROWI] + krajIII.emigranci[ZDROWI];
		losuj(liczbaStad2, STAD2);
		losuj(liczbaStad1, STAD1);
		losuj(liczbaKwaran, KWARAN);
		losuj(liczbaZdrowi, ZDROWI);
	}

	// LOSUJ KTO DOSTANIE IMIGRANToW :)
	public void losuj(int imigranci, int grupa) {
		Random rand = new Random();
		int cel; // 0 - krajI, 1 - krajII, 2 - krajIII
		for (int i = 1; i <= imigranci; i++) {
			cel = rand.nextInt(3);
			if (cel == 0) {
				krajI.przyjmijImigrantow(grupa);
			} else if (cel == 1) {
				krajII.przyjmijImigrantow(grupa);
			} else {
				krajIII.przyjmijImigrantow(grupa);
			}

		}
	}

	/** Wpisz dane do tabeli */
	public void wpiszDaneDoTabeli() {
		tydzien++;
		// Kraj I
		lNazwaKraju1.setText(krajI.getNazwa());
		lPopulacja1
				.setText(Integer.toString(krajI.getLiczbaLudn() + krajI.getLiczbaChor() + krajI.getLiczbaLudnKwar()));
		lZdrowi1.setText(Integer.toString(krajI.getLiczbaLudn() + krajI.getLiczbaLudnKwar()));
		lChorzy1.setText(Integer.toString(krajI.getLiczbaChor()));
		lZaszczepieni1.setText(Integer.toString(krajI.getLiczbaLudnKwar()));
		lZgony1.setText(Integer.toString(krajI.getLiczbaZgon()));
		// Kraj II
		lNazwaKraju2.setText(krajII.getNazwa());
		lPopulacja2.setText(
				Integer.toString(krajII.getLiczbaLudn() + krajII.getLiczbaChor() + krajII.getLiczbaLudnKwar()));
		lZdrowi2.setText(Integer.toString(krajII.getLiczbaLudn() + krajII.getLiczbaLudnKwar()));
		lChorzy2.setText(Integer.toString(krajII.getLiczbaChor()));
		lZaszczepieni2.setText(Integer.toString(krajII.getLiczbaLudnKwar()));
		lZgony2.setText(Integer.toString(krajII.getLiczbaZgon()));
		// Kraj III
		lNazwaKraju3.setText(krajIII.getNazwa());
		lPopulacja3.setText(
				Integer.toString(krajIII.getLiczbaLudn() + krajIII.getLiczbaChor() + krajIII.getLiczbaLudnKwar()));
		lZdrowi3.setText(Integer.toString(krajIII.getLiczbaLudn() + krajIII.getLiczbaLudnKwar()));
		lChorzy3.setText(Integer.toString(krajIII.getLiczbaChor()));
		lZaszczepieni3.setText(Integer.toString(krajIII.getLiczbaLudnKwar()));
		lZgony3.setText(Integer.toString(krajIII.getLiczbaZgon()));
		// Licznik tygodni
		lTydzienDane.setText(Integer.toString(tydzien));
	}

	/** Wpisz wspolne dane do tabeli */
	public void wpiszWspolneDaneDoTabeli() {
		lMigracjaDane.setText(Integer.toString(krajI.getWskaznikMigracji()));
		lSzansaZarDane.setText(Integer.toString(krajI.getSzansaZarazenia()));
		lStadiaDane.setText(Integer.toString(krajI.getLiczbaStadChor()));
	}

	private void symulacjaKrok() {
		// I - Lecz

		dodajDaneWykres();

		krajI.lecz();
		krajII.lecz();
		krajIII.lecz();

		// II - progresja choroby
		krajI.progresjaChoroby();
		krajII.progresjaChoroby();
		krajIII.progresjaChoroby();

		// III - zaraz
		krajI.zarazanie();
		krajII.zarazanie();
		krajIII.zarazanie();

		// IV - Migracja
		migracja();

		// V - Wynajdz lek

		if (krajI.wynajdzLek())
			lSzczep1.setText("TAK");
		if (krajII.wynajdzLek())
			lSzczep2.setText("TAK");
		if (krajIII.wynajdzLek())
			lSzczep3.setText("TAK");

		// VI - Wpisz Dane do tabeli I dodaj dane wykresu
		wpiszDaneDoTabeli();

		// VII - Sprawdz czy zakonczona symulacja (nikt nie jest chory)
		if (czyKoniec()) {
			dodajDaneWykres();
			koniecSymulacji();
		}
	}

	private void symulacjaAutomat() {
		while (krajI != null) {
			symulacjaKrok();
		}
	}

	private void inicjujKolejnaSymulacje() {

		stworzDaneWykres();
		try{
		oknoPlik.wczytajDaneWspolne();
		} catch(FileNotFoundException exception){
			new Komunikat(this, "Problem z plikiem!").setVisible(true);
		}

		migracja = oknoPlik.migracja;
		szansaZar = oknoPlik.szansaZar;
		stadia = oknoPlik.stadia;

		krajI = oknoPlik.StworzPanstwo();
		krajII = oknoPlik.StworzPanstwo();
		krajIII = oknoPlik.StworzPanstwo();

		if (krajI != null && krajII != null && krajIII != null) {
			tydzien = 0;
			startReczny.setEnabled(false);
			startPlik.setEnabled(false);
			nastepny.setEnabled(true);
			automat.setEnabled(true);
			wpiszWspolneDaneDoTabeli();
			wpiszDaneDoTabeli();
			lSzczep1.setText("Brak");
			lSzczep2.setText("Brak");
			lSzczep3.setText("Brak");
		}

	}

	private void koniecSymulacji() {
		Komunikat wiadomosc = new Komunikat(this, "Symulacja zakonczona");
		wiadomosc.setVisible(true);
		
		
		OknoWykres oknoWykr = new OknoWykres(this);
		oknoWykr.rysujWykres(stworzDataset(1), "Wykres liczby chorych", "Liczba osob chorych", krajI.getNazwa(),
				krajII.getNazwa(), krajIII.getNazwa(), ++licznikSym);
		oknoWykr.pack();
		oknoWykr.setVisible(true);

		oknoWykr = new OknoWykres(this);
		oknoWykr.rysujWykres(stworzDataset(2), "Wykres liczby populacji", "Liczba populacji", krajI.getNazwa(),
				krajII.getNazwa(), krajIII.getNazwa(),licznikSym);
		oknoWykr.pack();
		oknoWykr.setVisible(true);
		if (otwartoOknoPlik) { // Je eli otwarto oknoPlik
			try {
				if (!oknoPlik.plik.czyKoniecPliku()) { // Je eli nie jest to
														// koniec pliku
					inicjujKolejnaSymulacje();
					return;
				} else {
					oknoPlik.zamknijPlik();
					oknoPlik.dispose();
					setOtwartoOknoPlik(false);
				}
			} catch (FileNotFoundException e) {
				System.err.println("Nieznaleziono pliku!!");
			}
		}
		krajI = null;
		krajII = null;
		krajIII = null;
		startReczny.setEnabled(true);
		startPlik.setEnabled(true);
		nastepny.setEnabled(false);
		automat.setEnabled(false);

	}

	private boolean czyKoniec() {
		// liczbaLudn oznacza liczbe ludno ci niezaszczepiona i nie chora
		if (krajI.getLiczbaChor() == 0) {
			if (krajII.getLiczbaChor() == 0) {
				if (krajIII.getLiczbaChor() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public void wprowadzDanePlik() {
		if (!otwartoOknoPlik) {
			oknoPlik = new OknoPlik(this);
			setOtwartoOknoPlik(true);
		}
		oknoPlik.setVisible(true);
		if (oknoPlik.isPotwierdzono()) {
			migracja = oknoPlik.migracja;
			szansaZar = oknoPlik.szansaZar;
			stadia = oknoPlik.stadia;

			krajI = oknoPlik.StworzPanstwo();
			krajII = oknoPlik.StworzPanstwo();
			krajIII = oknoPlik.StworzPanstwo();

			if (krajI != null && krajII != null && krajIII != null) {
				tydzien = 0;
				startReczny.setEnabled(false);
				startPlik.setEnabled(false);
				nastepny.setEnabled(true);
				automat.setEnabled(true);
				wpiszWspolneDaneDoTabeli();
				wpiszDaneDoTabeli();
				stworzDaneWykres();

			}
		}

	}

	public boolean isOtwartoOknoPlik() {
		return otwartoOknoPlik;
	}

	public void setOtwartoOknoPlik(boolean otwartoOknoPlik) {
		this.otwartoOknoPlik = otwartoOknoPlik;
	}

	public boolean isSymulacjaZakonczona() {
		return symulacjaZakonczona;
	}

	public void setSymulacjaZakonczona(boolean symulacjaZakonczona) {
		this.symulacjaZakonczona = symulacjaZakonczona;
	}

	public void stworzDaneWykres() {
		wykrKrajIChor = new XYSeries("Kraj I");
		wykrKrajIIChor = new XYSeries("Kraj II");
		wykrKrajIIIChor = new XYSeries("Kraj III");

		wykrKrajIPop = new XYSeries("Kraj I");
		wykrKrajIIPop = new XYSeries("Kraj II");
		wykrKrajIIIPop = new XYSeries("Kraj III");
	}

	public void dodajDaneWykres() {
		wykrKrajIChor.add(tydzien, krajI.getLiczbaChor());
		wykrKrajIIChor.add(tydzien, krajII.getLiczbaChor());
		wykrKrajIIIChor.add(tydzien, krajIII.getLiczbaChor());

		wykrKrajIPop.add(tydzien, (krajI.getLiczbaLudn() + krajI.getLiczbaLudnKwar() + krajI.getLiczbaChor()));
		wykrKrajIIPop.add(tydzien, (krajII.getLiczbaLudn() + krajII.getLiczbaLudnKwar() + krajII.getLiczbaChor()));
		wykrKrajIIIPop.add(tydzien, (krajIII.getLiczbaLudn() + krajIII.getLiczbaLudnKwar() + krajIII.getLiczbaChor()));

	}

	public XYDataset stworzDataset(int seriaDanych) {
		final XYSeriesCollection dataset = new XYSeriesCollection();
		if (seriaDanych == 1) {
			dataset.addSeries(wykrKrajIChor);
			dataset.addSeries(wykrKrajIIChor);
			dataset.addSeries(wykrKrajIIIChor);
		} else {
			dataset.addSeries(wykrKrajIPop);
			dataset.addSeries(wykrKrajIIPop);
			dataset.addSeries(wykrKrajIIIPop);
		}
		return dataset;

	}

	/*----- ActionListener -----*/
	@Override
	public void actionPerformed(ActionEvent akcja) {
		Object zrodlo = akcja.getSource();
		// ---------- Start ----------
		if (zrodlo == startReczny) {
			wprowadzDane();
		} else if (zrodlo == oProgramie) {
			OprogramieOkno okno = new OprogramieOkno(this);
			okno.setVisible(true);
		} else if (zrodlo == nastepny) {
			symulacjaKrok();
		} else if (zrodlo == startPlik) {
			wprowadzDanePlik();
		} else if (zrodlo == automat) {
			symulacjaAutomat();
		}

	}

	public static void main(String[] args) {
		// Wyswietl Okno
		Epidemia grypa = new Epidemia();
		grypa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		grypa.setResizable(false);
		grypa.setLocation(160, 20);
		grypa.setVisible(true);
	}

}