

//An action to release an object: ensure the arm is retracted, then open the gripper

public class ReleaseAnObjectAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	
	
	//constructor to build action
	public ReleaseAnObjectAction(Robot theRobot) {
		super(theRobot);
	
	}
	

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
		//execute action
		if(theRobot.getArmState() == Robot.ArmState.RETRACTED)
		
		theRobot.openGripper();
	}

}
