import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Sandbox {

	private static ArrayList<File> fileList;
	public static HashMap<String, Boolean> stopWords;
	private static HashMap<String, ArrayList<Identifier>> index;

	private static void initalize()
	{
		fileList = new ArrayList<File>();
		stopWords = new HashMap<String, Boolean>();
		index = new HashMap<String, ArrayList<Identifier>>();

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
		System.out.println("Done tokenizing");

		sortIndex();

		//		PrintWriter writer = new PrintWriter(new File("index_plain.txt"));
		//		writer.write(sortedIndex.toString());
	}

	private static void sortIndex()
	{
		//TODO replase al with array of keys
		//		String[] toSort = Arrays.copyOf(al.toArray(), al.size(), String[].class);
		//		Arrays.sort(toSort);

		System.out.println("Index Sorted");
	}

	private static boolean shouldVisit(File file)
	{

		return true;
	}

	private static void visit(File file)
	{
		System.out.println(file);
		for(String z: index.keySet())
		{
			System.out.println(z + " " + index.get(z).toString());
		}
		
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
			PrintWriter pw = null;
			try {
				pw = new PrintWriter("toStem.txt");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HashMap<String, ArrayList<Integer>> tokens = Utilities.tokenizeFile(file);
			for(String key: tokens.keySet())
				pw.write(key + "\n");
			pw.close();
			String[] forStemmer = {"toStem.txt"};
			Stemmer.main(forStemmer);
			try {
				Scanner s = new Scanner(new File("stemmedWords.txt"));
				for(String key: tokens.keySet())
				{
					String token = s.next();
					if(index.containsKey(token))
					{
						boolean inList = false;
						ArrayList<Identifier> identifierList = index.get(token);

						for(Identifier i: identifierList)
							if(i.getDocument().equals(getShortFilePath(file.getPath())))
							{
								inList = true;
								for(int position: tokens.get(key))
									i.addPosition(position);
							}
						if(!inList)
						{
							ArrayList<Integer> positions = tokens.get(key);
							Identifier i = new Identifier(getShortFilePath(file.getPath()), positions);
							identifierList.add(i);
						}
					}
					else
					{
						ArrayList<Identifier> identifierList = new ArrayList<Identifier>();
						ArrayList<Integer> positions = tokens.get(key);
						Identifier i = new Identifier(getShortFilePath(file.getPath()), positions);
						identifierList.add(i);
						index.put(token, identifierList);
					}
				}
				s.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println(tokens.toString());
			//			System.out.println("File");
		}
	}

	
	private static String getShortFilePath(String file)
	{
		return file.substring(51, file.length());
	}
}
