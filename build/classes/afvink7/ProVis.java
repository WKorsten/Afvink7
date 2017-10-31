
package Afvink7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

//import afvinkopdracht_5.NotAnAA;
//import afvinkopdracht_5.Translator;

import static javax.swing.JFrame.EXIT_ON_CLOSE;


public class ProVis extends JFrame implements ActionListener {

	
	
	JLabel label, label2, labellol;
	JPanel panel1, panel2;
	JButton button1, button2;
	JTextField field1;
	TextArea text;
	String inputVanDing;

	/**
	 * de main
	 * @param args haha het is een main gekkie
	 */
	public static void main(String[] args) {
		ProVis frame = new ProVis();
		frame.setSize(600, 400);
		frame.setTitle("ProVis");
		frame.createGUI();
		frame.setVisible(true);

	}

	/**
	 * functie die de GUI initialiseert.
	 */
	public void createGUI() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container window = getContentPane();
		// window.setLayout(new FlowLayout() );
		window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
		panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		window.add(panel1);

		label = new JLabel("Bestand  ");
		panel1.add(label);

		field1 = new JTextField();
		field1.setPreferredSize(new Dimension(150, 30));
		panel1.add(field1);

		button2 = new JButton("Blader");
		panel1.add(button2);
		button2.addActionListener(this);

		button1 = new JButton("Visualiseer");
		panel1.add(button1);
		button1.addActionListener(this);

		labellol = new JLabel("                                           ");
		panel1.add(labellol);

		label2 = new JLabel("informatie");
		panel1.add(label2);

		text = new TextArea();
		text.setPreferredSize(new Dimension(400, 100));
		panel1.add(text);

		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(600, 200));
		panel2.setBackground(Color.WHITE);
		panel1.add(panel2);

	}

	/**
	 * Litte bestandlezer
	 * @return String met daarin de sequentie.
	 */
	public String BestandLezer() {
		// File f = new File(",");
		// String pad = f.getAbsolutePath();
		// System.out.println(pad);
		// maakDieFile();

		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File pad = fc.getSelectedFile();
		
		
		field1.setText(pad.getPath());
		
		// System.out.println(pad);
		StringBuilder bob = new StringBuilder(); // haha snap je hem want bob de bouwer

		// BufferedReader lezer;
		try {
			BufferedReader lezer = new BufferedReader(new FileReader(pad));
			String regel = lezer.readLine();
			while (regel != null) {

				bob.append(regel);
				regel = lezer.readLine();
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "De file is niet gevonden");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(bob.toString());
		return (bob.toString());
	}

	/**
	 * 
	 * @param bobInput String met daarin een sequentie
	 * @return iets
	 */
	public String[] omzetten(String bobInput) {
		String input = bobInput;
		String[] output = new String[bobInput.length()];

		for (int i = 0; i < input.length(); i++) {
			try {
				output[i] = Translator.aa2state(String.valueOf(input.charAt(i)));
				text.setText("Het zijn allemaal juiste aminozuren");
			} catch (NotAnAA e1) {
				text.setText("u stupid dis " + (input.charAt(i)) + " not an Aminozuur u dumb");
				// e1.printStackTrace();
			}
			

		}
		
		System.out.println(output);
		return output;

	}
	
	private void teken(String[] omgezet) {
		Graphics g = panel2.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, panel2.getWidth(), panel2.getHeight());
		//g.fillRect(10, 10, , height);
		int breedte = panel2.getWidth();
		float perc = 1f/omgezet.length;
		int breedteVakje = (int)Math.ceil(perc*breedte);
		
		for(int i = 0; i < omgezet.length;i++) {
			
			
			if (omgezet[i].equals("polair")) {
				g.setColor(Color.PINK);
			}
			else if(omgezet[i].equals("apolair")) {
				g.setColor(Color.BLUE);
			}
			else {
				g.setColor(Color.ORANGE);
			}
			
			
			
			float xPos = i*perc*breedte;
			
			
			g.fillRect((int) xPos, 0, breedteVakje, 100);
			
			
		}
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button2) {
			System.out.println("bestandlezer");
			
			try {
				this.inputVanDing = BestandLezer();
			} catch (NullPointerException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				System.out.println("yooooz niet op cancel drukken plsz");
			}
			
		} else if (e.getSource() == button1) {
			System.out.print("omzetten");
			String[] omgezet = omzetten(this.inputVanDing);
			
			teken(omgezet);
		}

	}
}
