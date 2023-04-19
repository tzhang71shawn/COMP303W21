import java.util.ArrayList;

/**
 * Represents class for a program; 
 * Composed of a sequence of actions, which represent the steps to perform.
 */
public abstract class Program {
	private ArrayList<Action> actions = new ArrayList<Action>() ;
	
	/**
	 * Creates a new complex action with no parameter
	 * 
	 * @param none
	 *            
	 */
	public Program() {
		
	}
	/**
	 * Creates a new complex action  with parameter
	 * 
	 * @param actions
	 *           
	 */
	public Program(Action a) {
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
	//to remove specific action added
	public void removeAction(Action a) {
			assert actions.size()!=0;
					actions.remove(a);
				}
		
	public ArrayList<Action> getActions() {
		return actions;
	}

	public void setActions(ArrayList<Action> actions) {
		this.actions = actions;
	}
	public void execute() {
		boolean succeed = true;
		int executedAction = 0;
		// TODO Auto-generated method stub
		assert actions.size()!=0;
		for(Action a: actions) {
			a.execute();
			executedAction++;
		}
		if(executedAction!=actions.size()-1) 
		succeed = false;
	}
	public void compute(String intendedComputation) {
		
		Visitor v = new ConcreteVisitor();
		for(Action a: actions) {
			if(a instanceof basicAction)
			((basicAction)a).accept(v);
			if(a instanceof ComplexActions)
				((ComplexActions)a).accept(v);
		}
	((ConcreteVisitor)v).compute(intendedComputation);
	
		
	}
}
