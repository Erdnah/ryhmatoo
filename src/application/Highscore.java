package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Label;

public class Highscore {
	
	public static ArrayList<String> temp;
	static Label tabel;
	static String tekst;

	public static int saaNumber(String rida) {
		String[] jupid = rida.split(" ");
		return Integer.parseInt(jupid[0]);
	}

	public static void init() throws FileNotFoundException {
		try {
			tekst = "Top 10\n";
			temp = new ArrayList<String>();
			tabel = new Label();
			File file = new File("tulemus.txt");
			if (!file.exists())
				file.createNewFile();
			// DataInputStream in= new DataInputStream(new FileInputStream(file
			// ));
			BufferedReader in = new BufferedReader(new FileReader(file));
			String inputLine;
			// Loome arraylisti info jaoks,mida on vaja lisada ja või muuta
			while ((inputLine = in.readLine()) != null) {
				temp.add(inputLine // Loeme andmed sisse
						+ System.getProperty("line.separator"));
			}
			in.close();
			String nimi = Main.getNimi();
			int tulemus = (int) ManguLava.getTulemus();
			int size = temp.size();
			int kordused = 0;
			if (size == 0) {
				temp.add(tulemus + " " + nimi
						+ System.getProperty("line.separator"));
				kordused = 1;
			}

			// Olenevalt tulemusest leiame koha,kuhu kohta tulemus asetada
			else if (saaNumber(temp.get(size - 1)) < tulemus) {
				temp.add(tulemus + " " + nimi
						+ System.getProperty("line.separator"));
				kordused = temp.size();
			} else {
				for (int i = 0; i < size; i++) {
					if (saaNumber(temp.get(i)) > tulemus) {
						temp.add(
								i,
								tulemus + " " + nimi
										+ System.getProperty("line.separator"));
						break;
					}
				}
				kordused = temp.size();
			}
			BufferedWriter out = new BufferedWriter( // loome andmete
														// väljakirjutaja
					new FileWriter(file));
			for (int i = 0; i < kordused; i++) {
				out.write(temp.get(i)); // kirjutame andmed tagasi tekstifaili
			}
			out.close();
			int mitu = 0;
			if (temp.size() < 10) {
				mitu = temp.size();
			} else {
				mitu = 10;
			}
			for (int i = 0; i < mitu; i++) {
				String rida = temp.get(i);
				tekst = tekst + rida;
			}
			tabel.setText(tekst);
		} catch (IOException s) {
			// TODO Auto-generated catch block
			s.printStackTrace();
		}

	}

}