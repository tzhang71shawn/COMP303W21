

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import java.util.Collections;
/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<TVShows> AllTVShows = new HashSet<>();
	public Library() {
		this.aMovies = new HashSet<>();
		this.aWatchLists  = new HashSet<>();
		this.AllTVShows = new HashSet<>();
	}
	// Optional
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aMovies.add(pMovie);
	}
	
	// Optional
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 */
	public void addWatchList(WatchList pList) {
		aWatchLists.add(pList);
		for (Movie movie : pList.getMovies()) {
			addMovie(movie);
		}
	}
	//add the TVShows to the library
	public void addTVShows(TVShows a) {
		AllTVShows.add(a);
	}
	//this is a generator by language and studio
	//eg. client can use generatorByLanANDStudio("English", "Disney") or ("Null","Null")
	public  WatchList generatorByLanANDStudio(String lan, String stu){
		
		//filter the movies by language first
		LinkedList<Movie> a = new LinkedList<>();
		for(Movie m: aMovies) {
			if(m.getLanguage().compareToIgnoreCase(lan)==0||lan.compareToIgnoreCase("Null")==0) {
				a.add(m);
			}
		}
		//filter the movies then by the required studio
		LinkedList<Movie> filtered = new LinkedList<>();
		for(Movie q: a) {
			if(q.getStudio().compareToIgnoreCase(stu)==0||stu.compareToIgnoreCase("Null")==0) {
				filtered.add(q);
			}
		}
		//convert linkedlist into watch list
		WatchList generated = new WatchList("Genarated");
		for(Movie p : filtered) {
			generated.addMovie(p);
		}
		return generated;
		
	}
	//play random number of episodes
	//eg. RandomTVShows(10) returns a  list of ten episodes chosen randomly
	public  WatchList RandomTVShows(int a) {
		//so I put al the episodes in a list first so that I
		//can choose randomly from the list
		LinkedList<Episode> all = new LinkedList<>();
		for(TVShows w:AllTVShows) {
			all.addAll(w.binge());
		}
		//make sure we have enough episodes inside
		if(a>all.size()) {
			throw new IllegalArgumentException("The number is more than episodes contained");
		}
		LinkedList<Episode> chosen = new LinkedList<>();
		
		//shuffle all the episodes and then choose the required # of episodes
		 Collections.shuffle(all);
		 for(int i=0;i<a;i++) {
		 chosen.add(all.get(i));
		 }

		//convert linkedlist into watch list
			WatchList generated = new WatchList("Genarated");
			for(Episode p : chosen) {
				generated.addEpisode(p);
			}
			return generated;
		 
	}
	
	
}
