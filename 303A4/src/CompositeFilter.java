import java.util.ArrayList;
public class CompositeFilter {



		private ArrayList<WatchListFilter> filters;
		public CompositeFilter(WatchListFilter a) {
			filters.add(a);
		}
		public ArrayList<WatchListFilter> getFilter() {
			return this.filters;
		}
		public void addFilter(WatchListFilter b) {
			filters.add(b);
		}
	}



