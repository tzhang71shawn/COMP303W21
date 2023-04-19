import java.util.ArrayList;

public class library {
	
	private ArrayList<Movie> Movies;
	private ArrayList<WatchList> WatchLists;
	
	public library() {
		Movies = new ArrayList<Movie>();
		WatchLists = new ArrayList<WatchList>();	
	}
	
	public library(Movie a) {
		
	}
	
	
	public void addMovie (Movie a) {
		this.Movies.add(a);
	}
	public void addWatchList (WatchList a) {
	this.WatchLists.add(a);	
	}
	
	
}
