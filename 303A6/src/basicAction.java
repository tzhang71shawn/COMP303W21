import java.util.LinkedList;
import java.util.Optional;



/**
 * Represents an abstract class for six basic actions 
 * This is to ensure ComplexActions is not a basic action and cannot be added into another
 * ComplexActions.
 */

public abstract class basicAction implements Action, Observer<Action>, Element{
	//attributes containing robot
		private Robot theRobot;
	//an optional observable to refer to
		private Observable<Action> logSystem;
		//a visitor 
		private Visitor v;
		
	public basicAction(Robot theRobot) {
		this.theRobot = theRobot;	
	}
	@Override
	public final void execute() {
		//check battery level first
		if(theRobot.getBatteryCharge()<=5)
			theRobot.rechargeBattery();
		 
		 specificExecute();
		
		//update battery level
		theRobot.updateBatteryLevel();
		
		/*after performing, log to system, this makes loose coupling between logging
		system and robot
		*/
		if(logSystem!= null)
		logging(logSystem, theRobot.getBatteryCharge());
		}
	
	
	public abstract void specificExecute();
	//a way for clients to force the battery to be recharged before execution the action
	public void recharge() {
		// TODO Auto-generated method stub
		theRobot.rechargeBattery();
	}
	
	public void setObservable(Observable<Action> observable) {
		logSystem= observable;
	}
	
	@Override
	public void logging(Observable<Action> observable, int batterylevel) {
		// TODO Auto-generated method stub
		observable.addObserver(this,batterylevel);
		
	}
	@Override
	public void accept(Visitor v) {
		// TODO Auto-generated method stub
		this.v=v;
		v.visit(this);
	}
}
