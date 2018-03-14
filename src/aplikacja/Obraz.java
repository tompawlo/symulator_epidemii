package aplikacja;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Obraz extends JPanel{
	private BufferedImage glownyO;
	public Obraz(String nazwaObrazu){
		File imageFile = new File(nazwaObrazu);	// wczyta obraz, kt�ry podamy do konstruktora
		try{
			glownyO = ImageIO.read(imageFile);
		} catch(IOException e) {
			System.err.println("Nie mo�na wczyta� obrazka!");
			e.printStackTrace();
		}
		Dimension dimension = new Dimension(glownyO.getWidth(), glownyO.getHeight());
		setPreferredSize(dimension);
		
	}
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(glownyO, 0, 0, this);
	}

}
