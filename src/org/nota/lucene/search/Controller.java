package org.nota.lucene.search;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Controller {

	public static ArrayList<String> readResource(String filePath) {
		ArrayList<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(Controller.class.getClassLoader().getResource(filePath).getFile()), StandardCharsets.UTF_8));) {
			String line = "";

			while ((line = br.readLine()) != null) {
				if (!line.equals(""))
					list.add(line);

			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			System.out.println("Default weight values will be used instead");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public static ArrayList<String> readFile(String filePath) {
		ArrayList<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));) {
			String line = "";

			while ((line = br.readLine()) != null) {
				if (!line.equals(""))
					list.add(line);

			}
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
			System.out.println("Default weight values will be used instead");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	public static Weights initiateWeights(String path) {
		Weights weights = new Weights();

		ArrayList<String> temp = readResource(path + "insertion.csv");
		weights.setInsertionWeights(temp);
		temp = readResource(path + "deletion.csv");
		weights.setDeletionWeights(temp);

		weights.setSubstitution(readWeights(path + "substitution.csv"));
		weights.setTransposition(readWeights(path + "transposition.csv"));

		return weights;
	}

	static Weight readWeights(String path) {

		ArrayList<String> temp = readResource(path);
		Weight weight = new Weight();
		weight.setWeights(temp);
		return weight;

	}
}
