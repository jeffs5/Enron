import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class Sandbox {

	private static ArrayList<File> fileList;
	private static int totalDataFiles;
	private static HashMap<String, Integer> sentMessages;

	private static void initalize()
	{
		fileList = new ArrayList<File>();
		totalDataFiles = 0;
		sentMessages = new HashMap<String, Integer>();
		
	}
	
	public static void main(String[] args) {
		
		initalize();
		
		//set to restrict how many files to go through
		//set to -1 for infinite
		int filesToVisit = -1;

		//populate list with starting seeds
		fileList.add(new File("/Users/jeffs5s/Desktop/enron_mail_20110402"));



		while(!fileList.isEmpty() && filesToVisit != 0)
		{
			File file = fileList.remove(0);
			
			if(shouldVisit(file))
				visit(file);
			//			System.out.println(filesToVisit);
			filesToVisit--;
		}
		
		System.out.println("The total number of data files is " + totalDataFiles);

	}
	


	private static boolean shouldVisit(File file)
	{
		
		return true;
	}

	private static void visit(File file)
	{
		//		System.out.println(file);
		totalDataFiles++;

		if (file.isDirectory())
		{
			//if visited directory has files to go to, add those files to the list to later visit
			for(File f: file.listFiles())
				fileList.add(f);
			System.out.println("Directory");
		}

		else if(file.isFile())
		{
			System.out.println("File");
		}
	}

}
