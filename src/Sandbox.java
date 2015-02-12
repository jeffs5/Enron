import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Sandbox {

	private static ArrayList<File> fileList;
	private static int totalDataFiles;
	private static HashMap<String, Integer> sentMessages;
	private static ArrayList<Frequency> filesPerPerson;
	public static HashMap<String, Boolean> stopWords;

	private static void initalize()
	{
		fileList = new ArrayList<File>();
		totalDataFiles = 0;
		sentMessages = new HashMap<String, Integer>();
		filesPerPerson = new ArrayList<Frequency>();
		
		stopWords = new HashMap<String, Boolean>();
		
		Scanner s = null;
		try {
			s = new Scanner(new File("StopWords.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
		int filesToVisit = -1;

		//populate list with starting seeds
		for(File f : new File("/Users/jeffs5s/Desktop/enron_mail_20110402/maildir/").listFiles())
			fileList.add(f);



		while(!fileList.isEmpty() && filesToVisit != 0)
		{
			File file = fileList.remove(0);

			if(shouldVisit(file))
				visit(file);
			//			System.out.println(filesToVisit);
			filesToVisit--;
		}

		System.out.println(filesPerPerson.toString());
		PriorityQueue<Frequency> topTen = new PriorityQueue<Frequency>(200, WordFrequencyCounter.freqComparator);
		for(Frequency f: filesPerPerson)
		{
			topTen.add(f);
		}
		for(int i = 0; i< 10; i++)
			System.out.println(topTen.poll());
		System.out.println("The total number of data files is " + totalDataFiles);

	}



	private static boolean shouldVisit(File file)
	{

		return true;
	}

	private static void visit(File file)
	{
		//		System.out.println(file);

		if (file.isDirectory())
		{
			for(File f: file.listFiles())
			{
				fileList.add(f);
			}
			//			System.out.println("Directory");
		}

		else if(file.isFile() && !file.getName().contains(".DS_Store"))
		{
//			System.out.println(Utilities.tokenizeFile(file));
			//			System.out.println("File");
		}
	}

}
