package Afvink7;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ProVis extends JFrame implements ActionListener {

    JLabel label, label2, label3;
    JPanel panel1, panel2;
    JButton button1, button2;
    JTextField field1;
    TextArea text;
    String inputVanDing;

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

        window.setLayout(new BoxLayout(window, BoxLayout.Y_AXIS));
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        window.add(panel1);

        label = new JLabel("Bestand  ");
        panel1.add(label);

        field1 = new JTextField();
        field1.setPreferredSize(new Dimension(150, 30));
        panel1.add(field1);

        button2 = new JButton("Browse");
        panel1.add(button2);
        button2.addActionListener(this);

        button1 = new JButton("Visualise");
        panel1.add(button1);
        button1.addActionListener(this);

        label3 = new JLabel("                                           ");
        panel1.add(label3);

        label2 = new JLabel("Information");
        panel1.add(label2);

        text = new TextArea();
        text.setPreferredSize(new Dimension(400, 100));
        panel1.add(text);

        panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(600, 200));
        panel2.setBackground(Color.WHITE);
        panel1.add(panel2);

    }

    public String BestandLezer() {

        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File pad = fc.getSelectedFile();

        field1.setText(pad.getPath());

        StringBuilder build = new StringBuilder();

        try {
            BufferedReader lezer = new BufferedReader(new FileReader(pad));
            String regel = lezer.readLine();
            while (regel != null) {

                build.append(regel);
                regel = lezer.readLine();
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "De file is niet gevonden");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println(build.toString());
        return (build.toString());
    }

    public String[] omzetten(String bobInput) throws NotAnAA {
        String input = bobInput;
        String[] output = new String[bobInput.length()];

        for (int i = 0; i < input.length(); i++) {
            output[i] = Translator.aa2state(String.valueOf(input.charAt(i)));
            text.setText("Het zijn allemaal juiste aminozuren");

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
        float perc = 1f / omgezet.length;
        int breedteVakje = (int) Math.ceil(perc * breedte);

        for (int i = 0; i < omgezet.length; i++) {

            if (omgezet[i].equals("polair")) {
                g.setColor(Color.PINK);
            } else if (omgezet[i].equals("apolair")) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.ORANGE);
            }

            float xPos = i * perc * breedte;

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

                System.out.println("Don't press cancel plz");
            }

        } else if (e.getSource() == button1) {
            System.out.print("omzetten");
            String[] omgezet = null;
            try {
                omgezet = omzetten(this.inputVanDing);
            } catch (NotAnAA ex) {
                Logger.getLogger(ProVis.class.getName()).log(Level.SEVERE, null, ex);
            }

            teken(omgezet);
        }

    }
}
