package hu.office.model.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultWriter {
	
	public String write(String fileName, String result) {
		try (PrintWriter pr = new PrintWriter(new FileWriter(fileName))) {
			pr.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
