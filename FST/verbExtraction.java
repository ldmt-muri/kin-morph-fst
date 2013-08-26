import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * Example of a program to extract words from the Kinyarwanda dictionary XML file. 
 * dictionary.txt is the dictionary in text file form.
 * This program takes each verb past stem and present stem and prints them in pairs,
 * one pair per line, in a new file (pastVerbList.txt).
 */
public class KinyaWordListMaker2 
{
  public static void main(String[] args) throws FileNotFoundException
	{
		File fl = new File("dictionary.txt");
		Scanner sc = new Scanner(fl);
		PrintWriter pw = new PrintWriter("pastVerbList.txt");
		String dictString = "";
		while(sc.hasNextLine())
		{
			dictString = sc.nextLine();
			int currentStartIndex = 0;
			int currentIndex = 0;
			int currentEndIndex = 0;
			int secondOne = 0;
			int secondTwo = 0;
			
			while(currentEndIndex < dictString.length() && currentStartIndex != -1 )
			{
				//Locating the verbs
				currentStartIndex = dictString.indexOf("wordclass=\"v\"", currentEndIndex);
				
				if(currentStartIndex != -1)
				{
					//Finds the verb stem start index
					currentIndex = dictString.indexOf("text=\"-", currentStartIndex);
					
					//Finds the verb stem end index
					currentEndIndex = dictString.indexOf(" /", currentIndex);
					
					//Finds the start index of the other stem for this lemma
					secondOne = dictString.indexOf("text=\"-", currentEndIndex);
					
					//Finds the end index of the other stem for this lemma
					secondTwo = dictString.indexOf(" /", secondOne);
					
					pw.println(dictString.substring(currentIndex + 7, currentEndIndex - 1) + "  " + dictString.substring(secondOne + 7, secondTwo - 1));
					currentEndIndex = dictString.indexOf("</entry>", currentIndex) + 1;
				}
			
			}

		}
		
		pw.close();
	}
}
