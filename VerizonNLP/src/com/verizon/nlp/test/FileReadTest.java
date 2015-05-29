package com.verizon.nlp.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.verizon.nlp.util.NLPFileReader;

public class FileReadTest {

	@Test
	public void test() {
		try {
			assertEquals("SUCCESS", new NLPFileReader().readLocalTextFile("D:\\Hackathon\\sample\\inputsample.txt"), "SUCCESS");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
