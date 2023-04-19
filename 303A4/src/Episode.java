

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Set;

/**
 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path on
 * the file system.
 */
public class Episode implements Sequenceable<Episode>,Cloneable {
	
	private final File aPath;
	private final TVShow aTVShow;
	private String aTitle;
	private int aEpisodeNumber;
	private Map<String, String> aCast = new HashMap<>();
	private Map<String, String> aTags = new HashMap<>();
	
	/**
	 * Creates an episode from the file path. This method should not be called by a client. Use
	 * TVShow.createAndAddEpisode instead.
	 *
	 * @param pPath
	 *            location of the episode on the file system.
	 * @param pTVShow
	 *            TVShow that this episode is a part of
	 * @param pTitle
	 *            official title of the episode
	 * @param pEpisodeNumber
	 *            the episode number that identifies the episode
	 * @pre pPath!=null && pTVShow != null && pTitle!=null
	 * @throws IllegalArgumentException
	 *             if the path doesn't point to a file (e.g., it denotes a directory)
	 */
	Episode(File pPath, TVShow pTVShow, String pTitle, int pEpisodeNumber) {
		// Package-private constructor AND notice in the Javadoc to prevent clients from using this constructor.
		// Still, a client could create an Episode directly, and it would only affect the episode object, not the TV
		// show.
		// Alternatively, the Episode class could be nested inside TVShow, with a private constructor.
		assert (pPath != null) && (pTVShow != null) && (pTitle != null);
		if (pPath.exists() && !pPath.isFile()) {
			throw new IllegalArgumentException("The path should point to a file.");
		}
		aPath = pPath; // ok because File is immutable.
		aTVShow = pTVShow;
		aTitle = pTitle;
		aEpisodeNumber = pEpisodeNumber;
	}
	
	@Override
	public void watch() {
		System.out.println("Now watching " + aTVShow.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
	}
	
	@Override
	public boolean isValid() {
		return aPath.isFile() && aPath.canRead();
	}
	
	public String getTitle() {
		return aTitle;
	}
	
	public String getStudio() {
		return aTVShow.getStudio();
	}
	
	public Language getLanguage() {
		return aTVShow.getLanguage();
	}
	
	/**
	 * @return the episode number of the episode
	 */
	public int getEpisodeNumber() {
		return aEpisodeNumber;
	}

	public String setCast(String pCharacter, String pActor) {
		if (pActor == null) {
			return aCast.remove(pCharacter);
		}
		else {
			return aCast.put(pCharacter, pActor);
		}
	}

	public String getCast(String pCharacter) {
		return aCast.get(pCharacter);
	}

	public Set<String> getAllCharacters() {
		return Collections.unmodifiableSet(aCast.keySet());
	}

	public String setInfo(String pKey, String pValue) {
		if (pValue == null) {
			return aTags.remove(pKey);
		}
		else {
			return aTags.put(pKey, pValue);
		}
	}
	
	public String getInfo(String pKey) {
		return aTags.get(pKey);
	}
	
	public boolean hasInfo(String pKey) {
		return aTags.containsKey(pKey);
	}
	
	@Override
	public boolean hasPrevious() {
		return aEpisodeNumber > 1;
	}
	
	@Override
	public boolean hasNext() {
		return aEpisodeNumber < aTVShow.getTotalCount();
	}
	
	@Override
	public Episode getPrevious() {
		return aTVShow.getEpisode(aEpisodeNumber - 1);
	}
	
	@Override
	public Episode getNext() {
		return aTVShow.getEpisode(aEpisodeNumber + 1);
	}
	
	public Episode clone() throws CloneNotSupportedException {
		try{
			Episode clone =(Episode)super.clone();
			return clone;
		}
		catch(CloneNotSupportedException e) {
		return null;
		}
	}
}
