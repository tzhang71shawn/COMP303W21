import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Map;

public class TVShows implements Watchable<Episode>, Bingeable<List>{

	//use hashmap to store episodes 
	private Map<char[], Episode> Episodes = new HashMap<>();
	private String aName;
	
	public TVShows(String pName) {
		aName = pName;
	}
	
	public String getName() {
		return aName;
	}
	public void setName(String pName) {
		aName = pName;
	}
	//
	public void addEpisode(Episode a) {
		Episodes.put(a.getSequentialNumber(),a);
	}
	// to identify an episode by its sequential number
	public Episode getEpisode(char[] number) {
		return Episodes.get(number);
	}

	@Override
	//play one episode for watchable feature
	public Episode play() {
		if(isWatchable()) {
		Map.Entry<char[], Episode> entry = Episodes.entrySet().iterator().next();
		Episode a=entry.getValue();
		return a;
		}
		return null;
	}

	@Override
	public boolean isWatchable() {
		if(this.Episodes.isEmpty()==true)
		return false;
		else return true;
	}

	@Override
	//binge watching all the episodes in the TV Shows
	public LinkedList binge() {
		LinkedList<Episode> a = new LinkedList<>(Episodes.values());
		return a;
		
	}
}
