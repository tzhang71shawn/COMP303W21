import java.util.ArrayList;
import java.util.LinkedList;
/**
 * Represents class for complex actions 
 * Containing a list of basic actions for clients 
 */

public class ComplexActions implements Action, Element{
	private ArrayList<basicAction> actions = new ArrayList<basicAction>() ;
	//a visitor 
			private Visitor v;
	
	//list of observable to refer to
	private Observable<Action> logSystem;
	/**
	 * Creates a new complex action with no parameter
	 * 
	 * @param none
	 *            
	 */
	public ComplexActions() {
		
	}
	/**
	 * Creates a new complex action  with parameter
	 * 
	 * @param actions
	 *           
	 */
	public ComplexActions(basicAction a) {
		actions.add(a);
	}
	//to add action
	public void addAction(basicAction a) {
		actions.add(a);
	}
	//to remove last action added
		public void removeAction() {
			assert actions.size()!=0;
			actions.remove(actions.size()-1);
		}
		public ArrayList<basicAction> basics(){
			return this.actions;
		}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if( actions.size()!=0) {
		for(basicAction a: actions) {
			if(logSystem!=null)
		a.setObservable(logSystem);	
			a.execute();
			
		}
	}
	}
	//a way for clients to force the battery to be recharged before execution the action
	public void recharge() {
		// TODO Auto-generated method stub
		actions.get(0).recharge();
	}
	
	public void setObservable(Observable<Action> observable) {
		logSystem= observable;
	}
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		this.v=v;
		v.visit(this);
	}

	}
