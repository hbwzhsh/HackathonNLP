package com.verizon.nlp.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.verizon.nlp.util.SentiWordNet;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class CommentProcessor {
	final static Charset ENCODING = StandardCharsets.UTF_8;
	
	public static void processCommentsFromFile(String inFile, String outFilePath){
		CommentRanker cm= CommentRanker.getInstance();
		PrintWriter out =null;
		try{
		 out = new PrintWriter(new BufferedWriter(new FileWriter(outFilePath, true)));
		}catch(IOException e){
			e.printStackTrace();
		}
	    
		Path path = Paths.get(inFile);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	      while (scanner.hasNextLine()){
	        //process each line in some way
//	    	  rankComment(scanner.nextLine());
	    	  out.println(cm.getRank(scanner.nextLine()));
	  	    
//	    	  System.out.println();
	    	  
	      }      
	    } catch (IOException e) {			
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
  
//  public static void rankByEmoticons
  public static void main(String[] args) throws IOException {
	new CommentProcessor().processCommentsFromFile("D:\\Hackathon\\IO\\HackathonInput.txt","D:\\Hackathon\\IO\\Solo_Out.txt");
}

}
