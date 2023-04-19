import java.util.LinkedList;

public class LoggingSystem implements Observable<Action>{
	
	
	LinkedList<Pair> logs = new LinkedList<Pair>();
	
	
	static class Pair
	{
	    Observer<Action> first;
	    int second;
	    Pair(Observer<Action> observer, int b)
	    {
	        first = observer;
	        second = b;
	    }
	}
	//use input to build up pair to store logs
	@Override
	public void addObserver(Observer<Action> observer,int batterylevel) {
		// TODO Auto-generated method stub
		Pair p = new Pair(observer,batterylevel);
		logs.add(p);
	}
	//gotta need more context to decide how and when to remove logs
	
	@Override
	public void removeObserver(Observer<Action> observer) {
		// TODO Auto-generated method stub
		
	}
	public void printlogs() {
		if(logs.size()!=0) {
		for(Pair p: logs) {
			System.out.println(p.first.getClass().getName()+ " is performed, battery level is "+ p.second);
		}
	}
	}
	
	
}
