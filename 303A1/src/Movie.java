import java.io.File;
import java.util.HashMap;



public class Movie {
	//set the format as enums
	enum Format{
		MP4, MOV, WMV, AVI, FLV, MKV
	}
	
	private final String path;
	//fields
private String title;
private String language;
private String publishingStudio;
private HashMap<String, String> customeAttributes;


	public Movie(String path, String title, String language,  String publishingStudio) {
		
		
		//throws Exception 
		
		this.path=path;
		String form = Format(path);
		//format that doesn't match cannot create the movie
		if(!Movie.contains(form)) throw new IllegalArgumentException("format doesn't match");
		if(Movie.contains(form)) 	
		this.title=title;
		this.language=language;
		this.publishingStudio=publishingStudio;
		customeAttributes = new HashMap <String, String>();				
	}
	public String getTil() {
		return this.title;
	}
	public String getPath() {
		return this.path;
	}

	public String getlan() {
		return this.language;
	}
	public String getStu() {
		return this.publishingStudio;
	}

	public format getFormat() {
		//from string to format
		String form = Format(this.path);
		format j;
						
		j = format.valueOf(form);
				
		return j;
	}
	//retrieve the last three chars in path as format
	public static String Format(String path) {
		char[] c = new char[3];
		c[0] = path.charAt(path.length()-1);
		c[1] = path.charAt(path.length()-2);
		c[2] = path.charAt(path.length()-3);
		String s = String.valueOf(c);
		return s;
		
	}
	
	//check enum contains a certain format or not
	private static boolean contains(String test) {
	    for (format c : format.values()) {
	        if (c.name().equals(test)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	
	public boolean isValid() {
		//check if the file is vaild
		 File f = new File(this.path);
		boolean valid = f.exists();
		return valid;	
	}
	
	public void changeCustomInfo(String K,String V) {
		//set method for clients
		this.customeAttributes.put(K,V);
	}
	//be able to remove elements for custom info
	public void removeCustomInfo(String K) {
		this.customeAttributes.remove(K);
	}
	
	
}
