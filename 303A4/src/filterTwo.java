
public class filterTwo implements WatchListFilter{
	public filterTwo() {
		
	}
	@Override
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
		boolean re = pEpisode.hasPrevious();
		return !re;
	}

}
