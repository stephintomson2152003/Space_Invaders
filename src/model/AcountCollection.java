package model;

/**
 * Author : Stephin Tomson, Vickram Sullhan, Tanishq Jaiswal, Anuj Jariwala
 */
import java.io.Serializable;

import java.util.ArrayList;

import javafx.collections.FXCollections;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class AcountCollection implements Serializable {
	private static final long serialVersionUID = 1L; // Recommended to handle versioning

	private ArrayList<Acount> acounts;
	private String fileName = "acountCollection.ser";

	public AcountCollection() {
		acounts = new ArrayList<Acount>();

	}

	@SuppressWarnings("unchecked")
	public void openCurrentList() {
		try {
			FileInputStream Bytes = new FileInputStream(fileName);
			ObjectInputStream inFile = new ObjectInputStream(Bytes);

			ArrayList<String> ids = (ArrayList<String>) inFile.readObject();
			ArrayList<String> password = (ArrayList<String>) inFile.readObject();
			ArrayList<Integer> points = (ArrayList<Integer>) inFile.readObject();

			inFile.close();

			acounts = new ArrayList<Acount>();
			for (int i = 0; i < ids.size(); i++) {
				acounts.add(new Acount(ids.get(i), password.get(i), points.get(i)));
			}
		} catch (Exception e) {
			System.out.println("Error in loading ser File");
		}

	}

	public void saveCurrentList() {
		// saves data in the todoList to file
		try {
			FileOutputStream bytesToDisk = new FileOutputStream(fileName);
			ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);

			outFile.writeObject(getIds());
			outFile.writeObject(getPasswords());
			outFile.writeObject(getPoints());
			outFile.close();
		} catch (IOException ioe) {
			System.out.println("Writing objects failed");
		}
	}

	public ArrayList<String> getIds() {
		ArrayList<String> ids = new ArrayList<String>();

		for (int i = 0; i < acounts.size(); i++) {
			ids.add(acounts.get(i).getId());
		}
		return ids;
	}

	public ArrayList<String> getPasswords() {
		ArrayList<String> passwords = new ArrayList<String>();

		for (int i = 0; i < acounts.size(); i++) {
			passwords.add(acounts.get(i).getPassword());
		}
		return passwords;
	}

	public ArrayList<Integer> getPoints() {
		ArrayList<Integer> points = new ArrayList<Integer>();
		for (int i = 0; i < acounts.size(); i++) {
			points.add(acounts.get(i).getTopScore());
		}
		return points;
	}

	public boolean addAccount(Acount a) {
		if (!acounts.contains(a)) {
			acounts.add(a);
			return true;
		}
		return false;
	}

	public ArrayList<Acount> getList() {
		return acounts;
	}

	public void setDefaults() {
		acounts = new ArrayList<Acount>();
		acounts.add(new Acount("Chris", "1", 0));
		acounts.add(new Acount("Devon", "22", 0));
		acounts.add(new Acount("River", "333", 0));
		acounts.add(new Acount("Ryan", "4444", 0));
	}

}
