import java.util.ArrayList;

//used to identify how a token relates to a documents
public class Identifier {
	private String token;
	private int frequency = 0;
	private String document;
	ArrayList<Integer> positions;
	
	public Identifier(String doc, ArrayList<Integer> positions){
		this.document = doc;
		this.positions = positions;
		this.frequency = positions.size();
	}
	
	public void addPosition(int position){
		positions.add(position);
		frequency++;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public ArrayList<Integer> getPositions(){
		return positions;
	}
	
	public String getDocument(){
		return document;
	}
	
	public String getToken(){
		return token;
	}
	
	//returns string in format request in assignment
	//[\t<doc>:<frequency>:<position>[,<position>]*]+
	public String toString(){
		String output ="\t" + document + ":" + frequency + ":";
		for(int i = 0; i < positions.size(); i++){
			
			output += ",";
			output += positions.get(i); 

		}
		return output.replaceFirst(",", "");
	}
}
