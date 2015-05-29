package com.verizon.nlp.util;

import java.util.Hashtable;

/**
 * 
 * @author Mughilan
 * This will be used to convert the OpenNLP POS tag to SentiWord POS Tag
 *
 */
public class SentiWordPOSTagger {
	private final static Hashtable<String, String> sentiWordPOS
    = new Hashtable<String, String>();
	private static SentiWordPOSTagger swtag = new SentiWordPOSTagger();
	private SentiWordPOSTagger(){
		
	}
	public static SentiWordPOSTagger getInstance(){
		load();
		return swtag;
	}
	public String getPOS(String aOpenNLPPos){
		return sentiWordPOS.get(aOpenNLPPos);
	}
    private static void load(){
    	if (sentiWordPOS.isEmpty() || sentiWordPOS == null)
    	{
    		//Adjective
    		sentiWordPOS.put("JJ", "a");
    		sentiWordPOS.put("JJR", "a");
    		sentiWordPOS.put("JJS", "a");
    		
    		//noun
    		sentiWordPOS.put("NN", "n");
    		sentiWordPOS.put("NNS", "n");
    		sentiWordPOS.put("NNP", "n");
    		sentiWordPOS.put("NNPS", "n");
    		sentiWordPOS.put("PRP", "n");
    		sentiWordPOS.put("PRP$", "n");
    		
    		//Adverb
    		sentiWordPOS.put("RB", "r");
    		sentiWordPOS.put("RBR", "r");
    		sentiWordPOS.put("RBS", "r");
    		sentiWordPOS.put("RP", "r");
    		
    		//Verb
    		sentiWordPOS.put("VB", "v");
    		sentiWordPOS.put("VBD", "v");
    		sentiWordPOS.put("VBG", "v");
    		sentiWordPOS.put("VBN", "v");
    		sentiWordPOS.put("VBP", "v");
    		sentiWordPOS.put("VBZ", "v");
    	}
    }
}
