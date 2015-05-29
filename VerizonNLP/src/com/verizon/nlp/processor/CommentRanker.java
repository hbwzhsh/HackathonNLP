package com.verizon.nlp.processor;


import java.io.File;
import java.io.IOException;





import java.io.StringReader;

import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import com.verizon.nlp.util.SentiWordNet;
import com.verizon.nlp.util.SentiWordPOSTagger;

public class CommentRanker {
	
	private static SentiWordNet sentiWordNetData;
	private static final CommentRanker aCommentRanker = new CommentRanker();
	private static SentiWordPOSTagger sentiWordPOS =  SentiWordPOSTagger.getInstance();
	
	// Singleton class
	private CommentRanker(){
		
	}	
	
	/**
	 * This method will load the following to memory for processing.
	 * SentiWordNet Dictionary
	 *  
	 */
	private static void  init(){
		if (sentiWordNetData == null)
		{
			try {
				sentiWordNetData= new SentiWordNet("SentiWordNet.txt");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public static CommentRanker getInstance(){
		init();
		return aCommentRanker;
	}
	
	public Rank getRank(String aComment){
		double sentirank = 0;
		Rank rank = null;
		try{
		POSModel model = new POSModelLoader()	
		.load(new File("en-pos-maxent.zip"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);

		
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(aComment));

		perfMon.start();
		String line;
		
		while ((line = lineStream.read()) != null) {
			 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			
			String[] tags = tagger.tag(whitespaceTokenizerLine);
			
	        for (int intCount=0;intCount < whitespaceTokenizerLine.length; intCount++ ){
	        	
	        	try{	        	
	        		sentirank += getSentiWordValue(whitespaceTokenizerLine[intCount].toLowerCase(), tags[intCount]);
	        	}catch(Exception e){
	        		e.printStackTrace();
	        	}
	        	
	        }			perfMon.incrementCounter();
		}
		perfMon.stopAndPrintFinalResult();
		}catch(Exception e){
			
		}
		if(sentirank > 0.0)
			rank = Rank.Positive;
		if(sentirank < 0.0)
			return Rank.Negative;
		if(sentirank == 0.0)
			return Rank.Neutral;
		return rank;
	}
	private double getSentiWordValue(String word, String posTag){
		Double sentiValue = null;
		
		try{
		sentiValue=	sentiWordNetData.extract(word, sentiWordPOS.getPOS(posTag));
		if(sentiValue == null){
			// Word with POS not found in sentiword, going check for all possible POS
			String[] posArray = {"a","r","n","v"};
			for(String pos: posArray){
				sentiValue=	sentiWordNetData.extract(word, pos);
				if(sentiValue != null)
					break;
			}
		}
		}catch(NullPointerException ne){
			ne.printStackTrace();
		}
		if(sentiValue == null) sentiValue = 0.0;
		
		return sentiValue;
	}
	
	
	public static void main(String[] args) {
		try{
		CommentRanker cm= CommentRanker.getInstance();
		
		System.out.println(cm.getRank("blazing fast internet speeds. I'm blown away"));
		}catch(Exception e ){
			e.printStackTrace();
		}
	}

}
