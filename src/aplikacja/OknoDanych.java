package aplikacja;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------- OKNO WPROWADZANIA DANYCH PA�STWA ---------------*/
public class OknoDanych extends JDialog implements ActionListener {

	private JButton wczytajDane;
	private JLabel lNazwaKraju, lPopulacja, lChorzy, lMigracja, lSzansaZar, lStadia, lTytul;
	private JTextField tNazwaKraju, tPopulacja, tChorzy, tMigracja, tSzansaZar, tStadia;
	private boolean okData;
	private JPanel obraz;

	private String nazwaKraju;
	private int populacja, chorzy, migracja, szansaZar, stadia;

	public OknoDanych(JFrame owner, String obrazek) {

		super(owner, "Wprowadzanie danych", true);
		// G��wne okno
		setSize(1000, 400);
		setTitle("Wprowad� Dane");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(400, 200);
		// JPanel - Obrazek
		obraz = new Obraz(obrazek);
		obraz.setBounds(20, 50, 400, 300); // po�o�enie xy i wymiary obrazka!
		add(obraz);

		// OKData
		okData = false;

		// JButton
		wczytajDane = new JButton("Wczytaj Dane");
		wczytajDane.setBounds(800, 320, 120, 30);
		add(wczytajDane);
		wczytajDane.addActionListener(this);

		lNazwaKraju = new JLabel("Nazwa Pa�stwa (max 10 znak�w)");
		lNazwaKraju.setBounds(650, 20, 300, 30);
		lNazwaKraju.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lNazwaKraju);

		lPopulacja = new JLabel("Liczba populacji  <300,100000> ");
		lPopulacja.setBounds(650, 70, 300, 30);
		lPopulacja.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lPopulacja);

		lChorzy = new JLabel("Liczba chorych (%)  <0,5>");
		lChorzy.setBounds(650, 120, 300, 30);
		lChorzy.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lChorzy);

		// JTextField - Pola tekstowe
		tNazwaKraju = new JTextField();
		tNazwaKraju.setBounds(450, 20, 150, 30);
		add(tNazwaKraju);

		tPopulacja = new JTextField();
		tPopulacja.setBounds(450, 70, 150, 30);
		add(tPopulacja);

		tChorzy = new JTextField();
		tChorzy.setBounds(450, 120, 150, 30);
		add(tChorzy);

	}

	// Sprawd� wszystkie okienka
	private boolean czyPoprawneDane() {
		// Nazwa kraju musi zawiera� conajmniej jeden znak
		// i by� kr�tsza ni� 10 znak�w
		if (tNazwaKraju.getText() == "" || tNazwaKraju.getText().length() > 10) {
			return false;
		}
		int wpisana; // zmienna pomocnicza
		// Liczba ludno�ci w kraju musi zawiera� si� pomi�dzy 300 a 100000
		wpisana = Integer.parseInt(tPopulacja.getText());
		// int populacja = wpisana;
		if ((wpisana < 300) || (wpisana > 100000)) {
			return false;
		}
		// Liczba chorych 0 - 5 (%)
		wpisana = Integer.parseInt(tChorzy.getText());
		if ((wpisana < 0) || (wpisana > 5)) {
			return false;
		}

		return true;

	}

	private void przyjmijDane() {
		nazwaKraju = tNazwaKraju.getText();
		populacja = Integer.parseInt(tPopulacja.getText());
		chorzy = Integer.parseInt(tChorzy.getText());
		okData = true;

	}

	public boolean isOK() {
		return okData;
	}

	public Panstwo stworzPanstwo(int migracja, int szansaZar, int stadia) {
		return new Panstwo(nazwaKraju, populacja, chorzy, migracja, stadia, szansaZar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object zrodlo = e.getSource();
		if (zrodlo == wczytajDane) {
			if (czyPoprawneDane() == false) {
				Komunikat niepoprDane = new Komunikat(this, "Niepoprawne Dane");
				niepoprDane.setVisible(true);

			} else {
				przyjmijDane();
				setVisible(false);

			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
