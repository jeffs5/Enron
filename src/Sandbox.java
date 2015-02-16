import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Sandbox {

	private static ArrayList<File> fileList;
	public static HashMap<String, Boolean> stopWords;

	private static void initalize()
	{
		fileList = new ArrayList<File>();
		stopWords = new HashMap<String, Boolean>();

		Scanner s = null;
		try {
			s = new Scanner(new File("StopWords.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(s.hasNext())
		{
			stopWords.put(s.nextLine(), false);
		}
		s.close();
	}

	public static void main(String[] args) {

		initalize();

		//set to restrict how many files to go through
		//set to -1 for infinite
		int filesToVisit = 20000;

		//populate list with starting seeds
		for(File f : new File("/Users/ronnie/Downloads/enron_mail_20110402/maildir/").listFiles())
			fileList.add(f);



		while(!fileList.isEmpty() && filesToVisit != 0)
		{
			File file = fileList.remove(0);
			
			if(filesToVisit%5000 == 0){
				System.out.println(filesToVisit);
			}
			
			visit(file);
			
			filesToVisit--;
		}
		System.out.println("Done tokenizing");
	}

	private static void visit(File file)
	{
		if (file.isDirectory())
		{
			for(File f: file.listFiles())
			{
				fileList.add(f);
			}
		}
	}
}
