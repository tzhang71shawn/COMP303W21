

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Represents a single TV show, with at least a title, language, and publishing studio. Each TVShow aggregates episodes.
 */
public class TVShow implements Watchable, Bingeable<Episode> {
	
	private String aTitle;
	private Language aLanguage;
	private String aStudio;
	private Map<String, String> aInfo;
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;
	
	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the movie, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	//setting constructor to private for flyweight design
	private TVShow(String pTitle, Language pLanguage, String pStudio) {
		assert pTitle != null && pLanguage != null && pStudio != null;
		aTitle = pTitle;
		aLanguage = pLanguage;
		aStudio = pStudio;
		aNextToWatch = 0;
		aInfo = new HashMap<>();
	}
	//I decided to use flyweight design pattern to ensure object uniqness 
		//for Movie Object
		
		//a factory to store movies, using hashmap
		
		private static HashMap<String,TVShow> TVShowStore = new HashMap <String,TVShow>();
		
		public static TVShow get(String pTitle, Language pLanguage, String pStudio) {
			if(!TVShowStore.containsKey(pTitle)) {
				TVShowStore.put(pTitle,new TVShow(pTitle, pLanguage, pStudio));
			}		
			return TVShowStore.get(pTitle);
		}
	
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
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
	
	
	public String getInfo(String pKey) {
		return aInfo.get(pKey);
	}
	
	
	public boolean hasInfo(String pKey) {
		return aInfo.containsKey(pKey);
	}
	
	
	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aInfo.remove(pKey);
		}
		else {
			return aInfo.put(pKey, pValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	
	public boolean isValid() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a new Episode for this TV show, and adds it the end of the episode list.
	 * 
	 * @param pPath
	 *            the path of the file that contains the video of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @pre pPath != null && pTitle != null;
	 */
	public void createAndAddEpisode(File pPath, String pTitle) {
		assert pPath != null && pTitle != null;
		int nb = aEpisodes.size();
		Episode episode = new Episode(pPath, this, pTitle, nb);
		aEpisodes.add(episode);
	}
	
	/**
	 * Returns the Episode of a given number. Episode numbers are 1-based: the first episode is 1, not 0.
	 *
	 * @param pNumber
	 *            the number whose Episode is to be returned
	 * @return the Episode of the given number
	 * @pre there is an episode for the given number
	 */
	public Episode getEpisode(int pNumber) {
		assert aEpisodes.size() >= pNumber;
		return aEpisodes.get(pNumber - 1);
	}
	
	
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	
	public int getRemainingCount() {
		return aEpisodes.size() - aNextToWatch;
	}
	
	
	public Episode next() {
		assert getRemainingCount() > 0;
		Episode nextEpisode = aEpisodes.get(aNextToWatch);
		aNextToWatch++;
		if (aNextToWatch >= aEpisodes.size()) {
			aNextToWatch = 0;
		}
		return nextEpisode;
	}
	
	
	public void reset() {
		aNextToWatch = 0;
	}
	
	
	public Iterator<Episode> iterator() {
		return aEpisodes.iterator();
	}
}