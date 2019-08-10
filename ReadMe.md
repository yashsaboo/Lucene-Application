# Lucene Application
A codebase for performing operations on Lucene, a full-text search library in Java. The version of Lucene used is [7.4](https://lucene.apache.org/core/7_4_0/index.html)

## File Details
Codebase includes 3 Java Project in ./src folder. This project was built using Eclipse Oxygen with following Lucene 7.4 libraries as a requirement:
1. lucene.analysis
2. lucene.document
3. lucene.index
4. lucene.store
5. lucene.util
6. lucene.queryparser
7. lucene.search

### Project Description
1. .src/com/tutorialspoint/lucene: The code was imported from [TutorialsPoint](https://www.tutorialspoint.com/lucene/) 

2. .src/experimentalFramework: It has all the files which will be used for parsing Pubmed Dataset, generating CSV files out of indexed Pubmed dataset and generating artifical pubmed dataset. It can be used in following ways:
- DocumentProperties.java: Defines a single block of document in a Pubmed Dataset and has following datatypes, along with getters and setters: 
```
int url;
String titleInfo;
String abstractInfo;
ArrayList<Integer> eref1;
ArrayList<Integer> eref2;
ArrayList<String> entityInfo;
ArrayList<String> categories;
```

- PubmedParser.java: Parses the Pubmed formatted data
```
new PubmedParser("src/pubmed.sample").theBrainOfPubmedParser();	
```

- LuceneBuildIndex.java: Builds index on Pubmed dataset using the PubmedParser
```
// Add documents
Directory dir = FSDirectory.open( new File( pathIndex ).toPath() );
IndexWriter ixwriter = new IndexWriter( dir, getIndexWriterConfig() );
PubmedParser objPubmedParser = new PubmedParser("src/artificialPubmed.sample");
new LuceneBuildIndex().addDocuments(objPubmedParser, ixwriter);
ixwriter.close();
dir.close();
```

- LuceneSearch.java: Searches the Lucene indexed system using multiple ways, but queryUsingBooleanQuery is designed specifically for Qkd type of queries.
```
// For Qkd (k1, k2) where k1="transferred" and k2="patient"
Directory dir = FSDirectory.open( new File( pathIndex ).toPath() );
IndexReader indexReader = DirectoryReader.open( dir );
new LuceneSearch().queryUsingBooleanQuery(indexReader,new ArrayList<>(Arrays.asList("body", "body")), new ArrayList<>(Arrays.asList("transferred", "patient")), 10);
indexReader.close();
dir.close();
```

- LuceneDatabaseReader.java: Implements multiple helper functions to query Lucene indexed search system

- CreateDummyDatasetMadeUpOfNumbers.java: Creates Pubmed dataset using only numbers as an input. 
```
int distinctNoOfKeywords = 100000;
int noOfDocuments = 500000;
String fileName = "artificialPubmed.sample";
new CreateDummyDatasetMadeUpOfNumbers().createArtificialPubmedDataset(distinctNoOfKeywords, noOfDocuments, fileName);
```

- createCSVforExperimentalFramework.java: Creates CSV out of the indexed Lucene Dataset for Everything-Search Experimental Framework.
```
// For K.csv
Directory dir = FSDirectory.open( new File( pathIndex ).toPath() );
IndexReader indexReader = DirectoryReader.open( dir );
new createCSVforExperimentalFramework().createCSVforK(indexReader);
indexReader.close();
dir.close();
```

- CreateDummyDatasetMadeUpOfAlphabets.java: Creates Pubmed dataset using only alphabets as an input. Since length is used as an input with no of alphabets as an iterator for each position's permutation and combination, the amount of keywords are expotentially increased, where the exponent has a base of 26.

3. .src/PeilinLucene: The code was imported from another Forward Data Lab member's, [Peilin Rao](https://github.com/peilinrao/EverythingSearch-Experiment/tree/master/out/production/EverythingSearch-Experiment), Lucene codebase.

The following dataFiles are also included:
1. Index Folder: Stores the indexed pubmed.sample
2. pubmed.sample: Sample Pubmed Data (update the link)
3. artificialPubmed.sample: Generated Sample Data using .src/CreateDummyDatasetMadeUpOfAlphabets or .src/CreateDummyDatasetMadeUpOfAlphabets
2. CSV files: Stores the content of each relational table which are generated from the indexed pubmed.parse.
	- D.csv
	- K.csv
	- KD.csv
					
## Requirements to run the code
JRE Environment

### Contributor: [Yash Saboo](https://github.com/yashsaboo) as a Member of [FORWARD Data Lab](http://www.forwarddatalab.org/)