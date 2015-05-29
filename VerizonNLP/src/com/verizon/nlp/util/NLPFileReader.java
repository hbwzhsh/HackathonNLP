package com.verizon.nlp.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 
 * @author Mughilan
 * This is the base class which we be used to read the file on the given path.
 */
public class NLPFileReader {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	public String readLocalTextFile(String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
	       System.out.println(scanner.nextLine());
	      }      
	    }
	    return "SUCCESS";
	  }

}
