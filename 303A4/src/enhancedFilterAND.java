
public class enhancedFilterAND implements WatchListFilter{
	
	public enhancedFilterAND(CompositeFilter a) {
		
	}
	@Override
	//So basically I need to return every filter's boolean using &&
	public boolean filter(Movie pMovie) {
	
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean filter(TVShow pTVShow) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean filter(Episode pEpisode) {
		// TODO Auto-generated method stub
		return false;
	}

}
