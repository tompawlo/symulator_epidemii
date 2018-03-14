package aplikacja;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------- OKNO WPROWADZANIA DANYCH Z PLIKU ---------------*/
public class OknoPlik extends JDialog implements ActionListener{

	private JButton bWczytaj, bAnuluj;
	private JLabel lTytul;
	private JTextField tNazwa;

	public int migracja, szansaZar, stadia;
	public Plik plik;

	private boolean otwarty, potwierdzono;

	public OknoPlik(JFrame owner) {
		// Okno
		super(owner, "Wprowadzanie danych", true);
		setSize(400, 200);
		setTitle("Wprowad� Dane");
		setLayout(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setLocation(400, 200);
		// JButton
		bWczytaj = new JButton("Wczytaj");
		bWczytaj.setBounds(90, 100, 90, 40);
		bWczytaj.setFont(new Font("Times New Roman", Font.BOLD, 16));
		bWczytaj.addActionListener(this);
		add(bWczytaj);
		
		bAnuluj = new JButton("Anuluj");
		bAnuluj.setBounds(220, 100, 90, 40);
		bAnuluj.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(bAnuluj);
		bAnuluj.addActionListener(this);
		// JLabel
		lTytul = new JLabel("    Nazwa pliku:");
		lTytul.setBounds(5, 20, 130, 50);
		lTytul.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add(lTytul);
		// JTextField
		tNazwa = new JTextField("dane.txt");
		tNazwa.setBounds(150, 30, 230, 30);
		tNazwa.setFont(new Font("Times New Roman", Font.BOLD, 16));
		add(tNazwa);

		setOtwarty(false);
		setPotwierdzono(false);
	}

	public void wczytajDaneWspolne() throws FileNotFoundException {
		if (!otwarty) {
			plik = new Plik(tNazwa.getText());
			
			setOtwarty(true);
		}

		migracja = plik.wczytajMigracja();
		szansaZar = plik.wczytajSzansaZarazenia();
		stadia = plik.wczytajStadia();

	}

	public void zamknijPlik() {
		try {
			if (plik.czyKoniecPliku()) {
				setOtwarty(false);
				plik = null;
			}

		} catch (FileNotFoundException e) {
			System.err.println("Brak Pliku!");
		}

	}

	public Panstwo StworzPanstwo() {

		String nazwa = plik.wczytajNazwaPanstwa();
		int populacja = plik.wczytajPopulacja();
		int chorzy = plik.wczytajChorzy();
		return new Panstwo(nazwa, populacja, chorzy, migracja, stadia, szansaZar);
	}
	// Gettery i Settery
	public boolean isOtwarty() {
		return otwarty;
	}

	public void setOtwarty(boolean otwarty) {
		this.otwarty = otwarty;
	}
	
	public boolean isPotwierdzono() {
		return potwierdzono;
	}

	public void setPotwierdzono(boolean potwierdzono) {
		this.potwierdzono = potwierdzono;
	}
	// Action Performed
	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if (zrodlo == bWczytaj) {
			setPotwierdzono(true);
			try{
			wczytajDaneWspolne();
			} catch (FileNotFoundException exception){
				new Komunikat(this, "      Brak pliku!").setVisible(true);
				setPotwierdzono(false);
			}
			setVisible(false);
		} else if(zrodlo == bAnuluj){
			setPotwierdzono(false);
			setVisible(false);
		}

	}

	public static void main(String[] args) {

	}

}
