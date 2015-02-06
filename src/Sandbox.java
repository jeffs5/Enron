import java.io.File;
import java.util.ArrayList;


public class Sandbox {

	private static ArrayList<File> fileList;
	private static int totalDataFiles;

	public static void main(String[] args) {

		fileList = new ArrayList<File>();
		totalDataFiles = 0;

		//set to restrict how many files to go through
		//set to -1 for infinite
		int filesToVisit = -1;

		//populate list with starting seeds
		fileList.add(new File("/Users/jeffs5s/Desktop/enron_mail_20110402"));



		while(!fileList.isEmpty() && filesToVisit != 0)
		{
			if(shouldVisit())
				visit();
			//			System.out.println(filesToVisit);
			filesToVisit--;
		}
		
		System.out.println("The total number of data files is " + totalDataFiles);

	}

	private static boolean shouldVisit()
	{
		return true;
	}

	private static void visit()
	{
		File file = fileList.remove(0);
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
