package aplikacja;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class OknoWykres extends JDialog implements ActionListener, KeyListener {

	public OknoWykres(JFrame owner) {
		super(owner, "Wykres", true);
		setSize(1600, 900);
		setTitle("Wykres");
		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(true);
		setLocation(160, 60);
		addKeyListener(this);

	}

	public void rysujWykres(XYDataset data, String nazwaWykresu, String nazwaOY, String krajI, String krajII,
			String krajIII, int licznik) {
		JFreeChart xylineChart = ChartFactory.createXYLineChart(nazwaWykresu, "numer tygodnia", nazwaOY, data, PlotOrientation.VERTICAL, true, true, false);
		ChartPanel chartPanel = new ChartPanel(xylineChart);
		chartPanel.setPreferredSize(new java.awt.Dimension(1440, 810));
		final XYPlot plot = xylineChart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.GREEN);
		renderer.setSeriesPaint(1, Color.YELLOW);
		renderer.setSeriesPaint(2, Color.BLUE);
		renderer.setSeriesStroke(0, new BasicStroke(4.0f));
		renderer.setSeriesStroke(1, new BasicStroke(3.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		plot.setRenderer(renderer);
		setContentPane(chartPanel);		
		
		String nazwaPliku = Integer.toString(licznik)+" "+nazwaWykresu + " " + krajI + " " + krajII + " " + krajIII;
		nazwaPliku+=".jpeg";
		try {
			ChartUtilities.saveChartAsJPEG(new File(nazwaPliku), xylineChart, 1440, 810);
		} catch (IOException e) {
			{
				new Komunikat(this, "B��d").setVisible(true);

			}
		}

	}

	public void zapiszWykres() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public static void main(String[] args) {

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
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

}
