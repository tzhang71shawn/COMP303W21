

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a single movie, with at least a title, language, and publishing studio. Each movie is identified by its
 * path on the file system.
 */
public class Movie implements Sequenceable<Movie> {
	
	private final File aPath;
	
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	
	private Movie prequel;
	private Movie sequel;
	
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Creates a movie from the file path. Callers must also provide required metadata about the movie.
	 *
	 * @param pPath
	 *            location of the movie on the file system.
	 * @param pTitle
	 *            official title of the movie in its original language
	 * @param pLanguage
	 *            language of the movie
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pPath!=null && pTitle!=null && pLanguage!=null && pStudio!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	
	//setting constructor to private for flyweight design
	private Movie(File pPath, String pTitle, Language pLanguage, String pStudio) {
		assert pPath != null && pTitle != null && pLanguage != null && pStudio != null;
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
	}
	
	//I decided to use flyweight design pattern to ensure object uniqness 
	//for Movie Object
	
	//a factory to store movies, using hashmap
	
	private static HashMap<String,Movie> movieStore = new HashMap <String,Movie>();
	
	public static Movie get(File pPath, String pTitle, Language pLanguage, String pStudio) {
		if(!movieStore.containsKey(pTitle)) {
			movieStore.put(pTitle,new Movie(pPath, pTitle, pLanguage, pStudio));
		}		
		return movieStore.get(pTitle);
	}
	public static void get(String pTitle) {
		if(!movieStore.containsKey(pTitle)) {
			System.out.println("There is no such movie.");
		}		
		System.out.println(movieStore.get(pTitle).getTitle()+" "+movieStore.get(pTitle).getLanguage());
	}
	
	
	
	public void watch() {
		// Just a stub.
		// We don't expect you to implement a full media player!
		System.out.println("Now playing " + aTitle);
	}
	
	
	public boolean isValid() {
		return aPath.isFile() && aPath.canRead();
	}
	
	
	public String getTitle() {
		return aTitle;
	}
	
	
	public Language getLanguage() {
		return aLanguage;
	}
	
	
	public String getStudio() {
		return aStudio;
	}
	
	
	public String setInfo(String pKey, String pValue) {
		assert pKey != null && !pKey.isBlank();
		if (pValue == null) {
			return aTags.remove(pKey);
		}
		else {
			return aTags.put(pKey, pValue);
		}
	}
	
	
	public boolean hasInfo(String pKey) {
		assert pKey != null && !pKey.isBlank();
		return aTags.containsKey(pKey);
	}
	
	
	public String getInfo(String pKey) {
		assert hasInfo(pKey);
		return aTags.get(pKey);
	}
	
	
	public boolean hasPrevious() {
		return prequel != null;
	}
	
	
	public boolean hasNext() {
		return sequel != null;
	}
	
	
	public Movie getPrevious() {
		return prequel;
	}
	
	
	public Movie getNext() {
		return sequel;
	}
	
	/**
	 * Sets the previous Movie in the series, and updates the prequel and sequel information of all related movies
	 * involved.
	 *
	 * @param pMovie
	 *            the Movie object to set as previous
	 * @pre pMovie != null
	 */
	public void setPrevious(Movie pMovie) {
		assert pMovie != null;
		if (prequel != null) {
			prequel.sequel = null;
		}
		if (pMovie.sequel != null) {
			pMovie.sequel.prequel = null;
		}
		prequel = pMovie;
		pMovie.sequel = this;
	}
}
