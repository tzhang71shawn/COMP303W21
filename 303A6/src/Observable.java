
public interface Observable<Action>  {
	
	abstract void addObserver(Observer<Action> observer, int b); 
		
	void removeObserver(Observer<Action> observer); 
		
		
	
	
}
