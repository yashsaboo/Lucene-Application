package experimentalFramework;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class CreateDummyDatasetMadeUpOfNumbers {

	String path = "src/";
	
	public int getRandomNumberForARange(int min, int max)//both min and max inclusive
	{
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public String getRandomPara(int rangeOfLengthOfParaMin, int rangeOfLengthOfParaMax, int distinctNoOfKeywords)
	{
		int getLength = getRandomNumberForARange(rangeOfLengthOfParaMin, rangeOfLengthOfParaMax);
		String para = "";
		for(int i=0; i<getLength; i++)
		{
			para += getRandomNumberForARange(0, distinctNoOfKeywords-1) + " ";
		}
		return para.trim();
	}
	
	public String appendABlockToDataset(int url, int distinctNoOfKeywords)
	{
		String titleLine = url + "|t|" + getRandomPara(4, 25, distinctNoOfKeywords);
		String abstractLine = url + "|a|" + getRandomPara(20, 150, distinctNoOfKeywords);
		String entityLine = url + "\t" + "0" + "\t" + "0" + "\t" + "entityName" + "\t" + "category" + "\t" + "someId";
		String emptyLine = "";
		return (titleLine + "\n" +  abstractLine + "\n" +  entityLine + "\n" +  emptyLine + "\n");
	}
	
	public void createArtificialPubmedDataset(int distinctNoOfKeywords, int noOfDocuments, String fileName)
	{
		try
		{			
			PrintWriter pubmedWriter = new PrintWriter(new File(path+fileName));//create new file
			
			for(int i=0; i<noOfDocuments;i++)
			{
				pubmedWriter.append(appendABlockToDataset(i, distinctNoOfKeywords));
			}

			pubmedWriter.flush();
			pubmedWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not create Table K");
		}
	}
	
	public void theBrainOfCreateDummyDatasetMadeUpOfNumbers()
	{
		int distinctNoOfKeywords = 100000;
		int noOfDocuments = 500000;
		String fileName = "artificialPubmed.sample";
		createArtificialPubmedDataset(distinctNoOfKeywords, noOfDocuments, fileName);
	}
	
	public static void main(String args[])
	{
		CreateDummyDatasetMadeUpOfNumbers obj = new CreateDummyDatasetMadeUpOfNumbers();
		obj.theBrainOfCreateDummyDatasetMadeUpOfNumbers();
	}

}
