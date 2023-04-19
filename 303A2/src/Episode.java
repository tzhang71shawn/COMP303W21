import java.io.File;
import java.util.HashMap;
import java.util.Map;

//Each episode corresponds to a specific file path and can capture custom information,
//similarly to movies. 
//It should be possible to identify an episode by its sequential number. 
	public class Episode implements Watchable<Episode>, Precedable<Episode>{
		/*Episode has a specific file path.
		 * Has custom information
		*/ 
		
		private final File aPath;

		private char[] sequentialNumber;
	
		private Map<String, String> aTags = new HashMap<>();
		private Episode inSequence;
		//constructor
		public Episode(File pPath, char[] sequentialNumber) {
			if (pPath.exists() && !pPath.isFile()) {
				throw new IllegalArgumentException("The path should point to a file.");
			}
			aPath = pPath; // ok because File is immutable.
			this.sequentialNumber=sequentialNumber;
		}
		/**
		 * Sets the value of a custom tag.
		 */
		public void setTag(String pKey, String pValue) {
			if (pValue == null) {
				aTags.remove(pKey);
			}
			else {
				aTags.put(pKey, pValue);
			}
		}

		/**
		 * Retrieves the value of a custom tag.
		 */
		public String getTag(String pKey) {
			return aTags.get(pKey);
		}
		public char[] getSequentialNumber() {
			return this.sequentialNumber;
		}
		public void setSequence(Episode a) {
			this.inSequence=a;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(this.inSequence==null)
			return false;
			else return true;
		}
		@Override
		public Episode next() {
			if(hasNext()==true) return this.inSequence;
			else return null;
		}
		@Override
		public boolean isWatchable() {
			// TODO Auto-generated method stub
			return true;
		}
		@Override
		public Episode play() {
			return this;			
		}
		
		

}
