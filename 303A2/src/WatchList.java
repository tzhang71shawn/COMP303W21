

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Represents a sequence of movies to watch in FIFO order.
 */
public class WatchList implements Bingeable<List> {
	
	private final List<Movie> aList = new LinkedList<>();
	private final List<Episode> eList = new LinkedList<>();
	private String aName;
	
	/**
	 * Creates a new empty watchlist.
	 * 
	 * @param pName
	 *            the name of the list
	 */
	public WatchList(String pName) {
		aName = pName;
	}
	
	public String getName() {
		return aName;
	}
	
	/**
	 * Changes the name of this watchlist.
	 * 
	 * @param pName
	 *            the new name
	 */
	public void setName(String pName) {
		aName = pName;
	}
	
	/**
	 * Adds a movie at the end of this watchlist.
	 * 
	 * @param pMovie
	 *            the movie to add
	 */
	public void addMovie(Movie pMovie) {
		aList.add(pMovie);
	}
	
	/**
	 * Retrieves and removes the next movie to watch from this watchlist. Movies are retrieved in FIFO order.
	 */
	public Movie removeNext() {
		return aList.remove(0);
	}
	
	/**
	 * Retrieves the list of movies (valid and invalid) in this watchlist.
	 * 
	 * @return an unmodifiable list of movies, backed by this watchlist
	 */
	public List<Movie> getMovies() {
		return Collections.unmodifiableList(aList);
	}
	public List<Episode> getEpisodes() {
		return Collections.unmodifiableList(eList);
	}
	
	//add episode into watchlist
	public void addEpisode(Episode e) {
		eList.add(e);
	}
	
	/**
	 * Counts and returns the number of valid movies in this watchlist.
	 * 
	 * @return the number of valid movies
	 */
	public int getNumberMovies() {
		int count = 0;
		for (Movie movie : aList) {
			if (movie.isValid()) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Retrieves the list of all publishing studios, without duplicates, but including studios of invalid movies.
	 * 
	 * @return a set of studios
	 */
	public Set<String> getStudios() {
		Set<String> studios = new HashSet<>();
		for (Movie movie : aList) {
			studios.add(movie.getStudio());
		}
		return studios;
	}
	
	/**
	 * Retrieves the list of all languages, without duplicates, but including languages of invalid movies.
	 * 
	 * @return a set of languages
	 */
	public Set<String> getLanguages() {
		Set<String> languages = new HashSet<>();
		for (Movie movie : aList) {
			languages.add(movie.getLanguage());
		}
		return languages;
	}

	@Override
	//binge watching all the elements inside the list
	public List binge() {
		return Collections.unmodifiableList(aList);
	}
	
}
