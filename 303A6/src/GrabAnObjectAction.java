

//An action to grab an object : extend the arm, close the gripper, retract the arm

public class GrabAnObjectAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	
	
	//constructor to build action
	public GrabAnObjectAction(Robot theRobot) {
		super(theRobot);
	
	}

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
		boolean one = false, two = false;
		//execute action
		if(theRobot.getArmState() == Robot.ArmState.RETRACTED){
		theRobot.extendArm();	
		one = true;
		}
		
		if(theRobot.getGripperState() == Robot.GripperState.OPEN && one ==true){
		theRobot.closeGripper();
		two = true;
		}
		
		if(theRobot.getArmState() == Robot.ArmState.EXTENDED && one== true && two== true)
		theRobot.retractArm(); 
	}

}
