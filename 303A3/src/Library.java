

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Represents a movie library, with individual movie titles and watch lists.
 */
public class Library {
	
	private Set<Movie> aMovies = new HashSet<>();
	private Set<WatchList> aWatchLists = new HashSet<>();
	private Set<Episode> aEpisodes = new HashSet<>();
	private Set<TVShow> aTVShows = new HashSet<>();
	//necessary attribute
	private String aName ;
	//optional attribute
	private Optional<String> EmailID;
	
	//TO DO:
	//constructor
	//singleton design to ensure there is only one library class
	private static Library Instance = new Library("Library");
	
	private Library(String name) {
		this.aMovies = new HashSet<>();
		this.aWatchLists  = new HashSet<>();
		this.aTVShows = new HashSet<>();
		this.aEpisodes = new HashSet<>();
		this.aName = name;
		this.EmailID = Optional.empty();
	}
	
	//method to provide only one instance
	public static Library getInstance() 
    { 
        if (Instance == null) 
        	Instance = new Library("Library"); 
        return Instance; 
    } 

	//getter for aName
	public String getName() {
		return this.aName;
	}
	//setter for aName
	public void setName(String name) {
		assert name!= null && !name.isBlank();
		this.aName=name;
	}
	//getter for EmailID
	public Optional<String> getEmailID(){
		
		return this.EmailID;
	}
	//setter for EmailID
	public void setEmailID(String pEmailID) {
		EmailID = Optional.of(pEmailID); 
	}
	
	
	
	
	
	
	
	/**
	 * Adds a movie to the library. Duplicate movies aren't added twice.
	 * 
	 * @param pMovie
	 *            the movie to add
	 * @pre pMovie!=null
	 */
	public void addMovie(Movie pMovie) {
		assert pMovie != null;
		aMovies.add(pMovie);
	}
	
	
	/**
	 * Adds a watchlist to the library. All movies from the list are also added as individual movies to the library.
	 * 
	 * @param pList
	 *            the watchlist to add
	 * @pre pList!=null;
	 */
	public void addWatchList(WatchList pList) {
		assert pList != null;
		aWatchLists.add(pList);
		for (Watchable movie : pList) {
			addMovie((Movie) movie);
		}
	}
	
	/**
	 * Adds a TVShow to the library. All Episodes from the list are also added as individual episodes to the library.
	 *
	 * @param pTVShow
	 *            the TVShow to add
	 * @pre pTVShow!=null;
	 */
	public void addTVShow(TVShow pTVShow) {
		assert pTVShow != null;
		aTVShows.add(pTVShow);
		for (Episode episode : pTVShow) {
			aEpisodes.add(episode);
		}
	}
	
	/**
	 * Method to generate a new watchlist based on some filtering mechanism
	 * 
	 * @param pName
	 *            the name of the watchlist to create
	 * @param pGenerationParameters
	 *            the generation parameters
	 * @pre pName!=null && pFilter!=null;
	 */
	public WatchList generateWatchList(String pName, WatchListGenerationInfo pGenerationParameters) {
		assert (pName != null) && (pGenerationParameters != null);
		List<Watchable> items = new ArrayList<>();
		for (TVShow show : aTVShows) {
			if (pGenerationParameters.filter(show)) {
				for (Episode episode : show) {
					if (pGenerationParameters.filter(episode)) {
						items.add(episode);
					}
				}
			}
		}
		for (Movie movie : aMovies) {
			if (pGenerationParameters.filter(movie)) {
				items.add(movie);
			}
		}
		Collections.sort(items, pGenerationParameters);
		WatchList watchlist = new WatchList(pName);
		for (Watchable item : items) {
			watchlist.addWatchable(item);
		}
		return watchlist;
	}
}
