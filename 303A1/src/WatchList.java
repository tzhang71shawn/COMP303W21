import java.util.ArrayList;


public class WatchList {
	private ArrayList<Movie> watch;
	private String name;
	
	//constructor
	public WatchList() {
		this.watch=new ArrayList<Movie>();
		this.name = "watchlist";

	}
	
	//contructor2; can also be used to add movie into the list
	public WatchList(Movie a) {
		if (this.watch!=null) 
		watch.add(a);	
		else {
			
			
			this.watch=new ArrayList<Movie>();
			watch.add(a);	
		}
	}
	
	
	public void setName(String a) {
		this.name=a;	
	}
	//after it is watched, it can be removed
	public void removeFirst() {
		this.watch.remove(0);
	}
	public ArrayList<Movie> access(){
		//return a deep copy so that clients cannot change original movie object
		ArrayList<Movie> copy = new ArrayList<Movie>();
		for(Movie e: watch) {
			Movie a = new Movie(e.getPath(),e.getTil(),e.getlan(),e.getStu());
			copy.add(0,a);
		}
	return copy;
	
	}
	
	
	//for the client to access infos
	public String[] accessInfo() {
		//the info is a string array containing the number of valid movies and the list 
		//of all publishing studios and languages without duplicates
		String[] info = new String[3];
		int q=0;
		//first info element is the number of valid movies
		for(Movie a: watch) {
			if(a.isValid()) q++;		
		}
		info[0] = String.valueOf(q);
		
		//go through the each movie and put the studio in the arraylist if not already
		ArrayList<String> NumStudios =new ArrayList<String>();
		for(Movie a: watch) {
			if(!NumStudios.contains(a.getStu()));
			
			NumStudios.add(a.getStu());
			
		}
		//convert the arraylist into string 
		StringBuilder sb1 = new StringBuilder();
		for (String s : NumStudios)
		{
		    sb1.append(s);
		    sb1.append("; ");
		}
		//second info element is the list 
		//of all publishing studios
		info[1]=sb1.toString();
		
		//go through the each movie and put the language in the arraylist if not already
		ArrayList<String> Lan =new ArrayList<String>();
		for(Movie a: watch) {
			if(!Lan.contains(a.getlan()));
			Lan.add(a.getlan());
		}
		//convert the arraylist into string 
		StringBuilder sb2 = new StringBuilder();
		for (String s : Lan)
		{
		    sb2.append(s);
		    sb2.append("; ");
		}
		//third info element is the list 
		//of all languages
		info[2]=sb2.toString();
		
		return info;
	}
	
}
