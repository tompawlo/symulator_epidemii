package aplikacja;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*--------------- OProgramieOkno ---------------*/
public class OprogramieOkno extends JDialog implements ActionListener {
	private JButton bPotwierdz;
	private Obraz obraz;

	public OprogramieOkno(JFrame owner) {

		super(owner, "o programie", true);

		setSize(800, 750);
		setTitle("Powiadomienie");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(500, 150);

		obraz = new Obraz("oProgramieObraz.png");
		obraz.setBounds(0, 0, 800, 600); // po�o�enie xy i wymiary obrazka!
		add(obraz);

		// JButton
		bPotwierdz = new JButton("OK");
		bPotwierdz.setBounds(310, 620, 160, 80);
		bPotwierdz.setFont(new Font("Times New Roman", Font.BOLD, 26));
		add(bPotwierdz);
		bPotwierdz.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
