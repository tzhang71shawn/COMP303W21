
public interface Precedable<T> {
//This interface is used to determine if there is a sequel or next episode
	boolean hasNext();
	 T next();
	
}
