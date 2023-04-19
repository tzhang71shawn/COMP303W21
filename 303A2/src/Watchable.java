
public interface Watchable<T> {
	boolean isWatchable() ;
	
	T play();
	//for Movie and Episode, play them
	//for watchlist, play the whole list
	
		
	
}
