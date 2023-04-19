import java.io.File;
import java.util.*;

/**
 * Represents a single TV show, with at least a title, language, and publishing studio. Each TVShow aggregates episodes.
 */
public class TVShow extends MediaContent implements Watchable, Bingeable<TVShow.Episode> {

	private Optional<Episode> aPrototype = Optional.empty();
	
	private List<Episode> aEpisodes = new ArrayList<>();
	private int aNextToWatch;

	/**
	 * Creates a TVShow with required metadata about the show.
	 *
	 * @param pTitle
	 *            official title of the TVShow
	 * @param pLanguage
	 *            language of the TVShow, in full text (e.g., "English")
	 * @param pStudio
	 *            studio which originally published the movie
	 *
	 * @pre pTitle!=null && pLanguage!=null && pStudio!=null
	 */
	public TVShow(String pTitle, Language pLanguage, String pStudio) {
		super(pTitle, pLanguage, pStudio);
		aNextToWatch = 0;
		aInfo = new HashMap<>();
		aCast = new HashMap<>();
	}
	
	@Override
	public void watch() {
		for (Episode episode : aEpisodes) {
			if (episode.isValid()) {
				episode.watch();
			}
		}
	}

	
	/**
	 * {@inheritDoc}
	 * 
	 * @return true if the TV show contains at least one valid episode
	 */
	@Override
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
	 *            the location of the video file of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @return the newly created episode
	 */
	public Episode createAndAddEpisode(File pPath, String pTitle) {
		Episode episode = new Episode(aEpisodes.size() + 1, pTitle, aLanguage, aStudio, pPath);
		aEpisodes.add(episode);
		return episode;
	}
	
	/**
	 * Selects an Episode by index to use as a prototype for the creation of new episodes. The prototype will not be
	 * updated if the original episode is modified following this change.
	 * 
	 * @param pEpisodeNumber
	 *            1-based index of the episode to use as prototype
	 */
	public void setEpisodePrototype(int pEpisodeNumber) {
		assert pEpisodeNumber <= aEpisodes.size();
		aPrototype = Optional.of(getEpisode(pEpisodeNumber).clone());
	}
	
	/**
	 * Creates a new Episode for this TV show based on the current prototype, and adds it at the end of the episode
	 * list. If no prototype was selected, the first episode is set as the prototype.
	 * 
	 * @param pPath
	 *            the location of the video file of the episode
	 * @param pTitle
	 *            the title of the episode
	 * @return the newly created episode
	 * @pre getTotalCount() > 0
	 */
	public Episode createAndAddEpisodeFromPrototype(File pPath, String pTitle) {
		assert getTotalCount() > 0;
		if (aPrototype.isEmpty()) {
			setEpisodePrototype(1);
		}
		Episode episode = aPrototype.get().clone();
		episode.aEpisodeNumber = aEpisodes.size() + 1;
		episode.aPath = pPath;
		episode.aTitle = pTitle;
		aEpisodes.add(episode);
		return episode;
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
	
	@Override
	public int getTotalCount() {
		return aEpisodes.size();
	}
	
	@Override
	public int getRemainingCount() {
		return aEpisodes.size() - aNextToWatch;
	}
	
	@Override
	public Episode next() {
		assert getRemainingCount() > 0;
		Episode nextEpisode = aEpisodes.get(aNextToWatch);
		aNextToWatch++;
		if (aNextToWatch >= aEpisodes.size()) {
			aNextToWatch = 0;
		}
		return nextEpisode;
	}
	
	@Override
	public void reset() {
		aNextToWatch = 0;
	}
	
	@Override
	public Iterator<Episode> iterator() {
		return Collections.unmodifiableList(aEpisodes).iterator();
	}
	
	/**
	 * Represents a single episode, with at least a title, and an episode number. Each episode is identified by its path
	 * on the file system.
	 */
	public class Episode extends MediaContent implements Sequenceable<Episode>, Cloneable {
		
		private File aPath;
		private int aEpisodeNumber;

		/**
		 * Creates an episode from the file path. This method should not be called by a client. Use
		 * TVShow.createAndAddEpisode instead.
		 *
		 * @param pTitle
		 *            Title of the episode
		 * @param pPath
		 *            file path of the episode
		 * @param pEpisodeNumber
		 *            the episode number that identifies the episode
		 * @param pLanguage
		 * 			  the language of the episode
		 * @param pStudio
		 *            producing studio of the episode
		 */
		private Episode(int pEpisodeNumber, String pTitle, Language pLanguage, String pStudio, File pPath) {
			super(pTitle, pLanguage, pStudio);
			aPath = pPath;
			aEpisodeNumber = pEpisodeNumber;
		}

		// override video basic method because of episode number
		@Override
		public void watch() {
			if (aWatchList.isPresent()) {
				aWatchList.get().setLastWatched(this);

			}
			System.out.println("Now watching " + TVShow.this.getTitle() + " [" + aEpisodeNumber + "]: " + aTitle);
		}
		
		@Override
		public boolean isValid() {
			return aPath.isFile() && aPath.canRead();
		}
		
		public TVShow getTVShow() {
			return TVShow.this;
		}


		/**
		 * @return the episode number of the episode
		 */
		public int getEpisodeNumber() {
			return aEpisodeNumber;
		}
		
		@Override
		public boolean hasPrevious() {
			return aEpisodeNumber > 1;
		}
		
		@Override
		public boolean hasNext() {
			return aEpisodeNumber < getTotalCount();
		}
		
		@Override
		public Episode getPrevious() {
			return getEpisode(aEpisodeNumber - 1);
		}
		
		@Override
		public Episode getNext() {
			return getEpisode(aEpisodeNumber + 1);
		}
		
		@Override
		public Episode clone() {
			try {
				Episode clone = (Episode) super.clone();
				clone.aCast = new HashMap<>(this.aCast);
				clone.aInfo = new HashMap<>(this.aInfo);
				return clone;
			}
			catch (CloneNotSupportedException e) {
				assert false;
				return null;
			}
		}
	}
}