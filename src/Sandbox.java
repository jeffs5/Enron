import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Sandbox {

	private static ArrayList<File> fileList;
	private static int totalDataFiles;
	private static HashMap<String, Integer> sentMessages;
	private static ArrayList<Frequency> filesPerPerson;

	private static void initalize()
	{
		fileList = new ArrayList<File>();
		totalDataFiles = 0;
		sentMessages = new HashMap<String, Integer>();
		filesPerPerson = new ArrayList<Frequency>();
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
			int totalIndividualFiles = 0;
			for(File f: file.listFiles())
			{
//				System.out.println(f.getName());
				if(f.isDirectory())
				{
					for(File f2: f.listFiles())
					{
						totalIndividualFiles += f2.length();
					}
				}
				else if(f.isFile())
					totalIndividualFiles += 1;

			}
			filesPerPerson.add(new Frequency(file.getName(), totalIndividualFiles));
			//			System.out.println("Directory");
		}

		else if(file.isFile())
		{
			//			System.out.println("File");
		}
	}

}
