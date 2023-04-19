

//An action to compact an object: ensure the gripper holds an object, then compact the object

public class CompactAnObjectAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	
	
	//constructor to build action
	public CompactAnObjectAction(Robot theRobot) {
		super(theRobot);
	
	}

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
		//execute action
		if(this.theRobot.getGripperState()==Robot.GripperState.HOLDING_OBJECT)
		
		theRobot.compact();
	}
	public int getItems() {
		return this.theRobot.getCompactorLevel();
	}




}
