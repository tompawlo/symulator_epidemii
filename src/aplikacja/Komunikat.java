package aplikacja;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------- KOMUNIKAT ---------------*/
public class Komunikat extends JDialog implements ActionListener, KeyListener {
	private JLabel lWiadomosc;
	private JButton bPotwierdz, bAnuluj;

	public Komunikat(JDialog owner, String tresc) {

		super(owner, "Komunikat u�ytkownika", true);

		setSize(400, 250);
		setTitle("Powiadomienie");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(700, 250);

		// JButton
		bPotwierdz = new JButton("OK");
		bPotwierdz.setBounds(140, 150, 120, 50);
		add(bPotwierdz);
		bPotwierdz.addActionListener(this);
		bPotwierdz.addKeyListener(this);
		

		// JLabel
		lWiadomosc = new JLabel(tresc);
		lWiadomosc.setBounds(100, 50, 200, 50);
		lWiadomosc.setFont(new Font("Times New Roman", Font.BOLD, 24));
		add(lWiadomosc);

	}

	public Komunikat(JFrame owner, String tresc) {

		super(owner, "Komunikat u�ytkownika", true);

		setSize(400, 250);
		setTitle("Powiadomienie");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(700, 250);

		// JButton
		bPotwierdz = new JButton("OK");
		bPotwierdz.setBounds(140, 150, 120, 50);
		add(bPotwierdz);
		bPotwierdz.addActionListener(this);
		bPotwierdz.addKeyListener(this);

		// JLabel
		lWiadomosc = new JLabel(tresc, JLabel.CENTER);
		lWiadomosc.setBounds(30, 50, 340, 50);
		lWiadomosc.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lWiadomosc.setAlignmentX(CENTER_ALIGNMENT);
		add(lWiadomosc);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		int zrodlo = arg0.getKeyCode();
		if (zrodlo == KeyEvent.VK_ENTER)
			dispose();

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {

	}

}
