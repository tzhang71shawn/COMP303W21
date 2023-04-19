
public class filterOne implements WatchListFilter{
	private final String pStudio;
	public filterOne(String pStudio) {
		this.pStudio = pStudio;
    }
	@Override
	public boolean filter(Movie pMovie) {
		// TODO Auto-generated method stub
		 assert pMovie != null;
		 return pMovie.getStudio().equals(pStudio);
		
	}

	@Override
	public boolean filter(TVShow pTVShow) {
		// TODO Auto-generated method stub
		assert pTVShow != null;
		return pTVShow.getStudio().equals(pStudio);
	}

	@Override
	public boolean filter(Episode pEpisode) {
		// TODO Auto-generated method stub
		assert pEpisode != null;
		 return pEpisode.getStudio().equals(pStudio);
	}

}
