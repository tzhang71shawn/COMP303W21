

//An action to empty the compactor: empty the content of the compactor

public class EmptyTheCompactorAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	
	
	//constructor to build action
	public EmptyTheCompactorAction(Robot theRobot) {
		super(theRobot);
	
	}

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
	//execute action
		if( theRobot.getCompactorLevel() > 0)
		theRobot.emptyCompactor();
	}

}
