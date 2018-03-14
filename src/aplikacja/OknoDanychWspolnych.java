package aplikacja;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------- OKNO WPROWADZANIA DANYCH WSP�LNYCH ---------------*/
public class OknoDanychWspolnych extends JDialog implements ActionListener {

	private JButton wczytajDane;
	private JTextField tMigracja, tSzansaZar, tStadia;
	private JLabel lMigracja, lSzansaZar, lStadia;
	private boolean okData;

	private int migracja, szansaZar, stadia;

	public OknoDanychWspolnych(JFrame owner) {
		super(owner, "Wprowadzanie danych wsp�lnych", true);
		// G��wne okno
		setSize(600, 270);
		setTitle("Wprowad� Dane");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(400, 200);

		// JButton
		wczytajDane = new JButton("Wczytaj Dane");
		wczytajDane.setBounds(370, 180, 150, 30);
		add(wczytajDane);
		wczytajDane.addActionListener(this);

		// JLabel
		lMigracja = new JLabel("Wsp�czynnik migracji (%) <0,10>");
		lMigracja.setBounds(30, 30, 300, 30);
		lMigracja.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lMigracja);

		lSzansaZar = new JLabel("Szansa na zara�enie (%)  <20,80>");
		lSzansaZar.setBounds(30, 80, 300, 30);
		lSzansaZar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lSzansaZar);

		lStadia = new JLabel("Liczba stadi�w choroby  <3,5>");
		lStadia.setBounds(30, 130, 300, 30);
		lStadia.setFont(new Font("Times New Roman", Font.BOLD, 20));
		add(lStadia);

		// JTextField
		tMigracja = new JTextField();
		tMigracja.setBounds(370, 30, 150, 30);
		add(tMigracja);

		tSzansaZar = new JTextField();
		tSzansaZar.setBounds(370, 80, 150, 30);
		add(tSzansaZar);

		tStadia = new JTextField();
		tStadia.setBounds(370, 130, 150, 30);
		add(tStadia);

	}

	private boolean czyPoprawneDane() {
		// Migracja!!!!!!!!!!!!!!
		int wpisana = Integer.parseInt(tMigracja.getText());
		if (wpisana < 0 || wpisana > 10) {
			return false;
		}
		// Szanse zara�enia w procentach od 20 do 80
		wpisana = Integer.parseInt(tSzansaZar.getText());
		if ((wpisana < 20) || (wpisana > 80)) {
			return false;
		}
		// Stadium choroby min 3 max 5
		wpisana = Integer.parseInt(tStadia.getText());
		if ((wpisana < 3) || (wpisana > 5)) {
			return false;
		}

		return true;

	}

	private void przyjmijDane() {
		setMigracja(Integer.parseInt(tMigracja.getText()));
		setSzansaZar(Integer.parseInt(tSzansaZar.getText()));
		setStadia(Integer.parseInt(tStadia.getText()));
		okData = true;

	}

	public int getStadia() {
		return stadia;
	}

	public void setStadia(int stadia) {
		this.stadia = stadia;
	}

	public int getMigracja() {
		return migracja;
	}

	public void setMigracja(int migracja) {
		this.migracja = migracja;
	}

	public int getSzansaZar() {
		return szansaZar;
	}

	public void setSzansaZar(int szansaZar) {
		this.szansaZar = szansaZar;
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
