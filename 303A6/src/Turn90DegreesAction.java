//An action to Turn 90 degrees to left or right

public class Turn90DegreesAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	private double pDegrees;
	
	//constructor to build action
	public Turn90DegreesAction(Robot theRobot,  double pDegrees) {
		super(theRobot);
		this.pDegrees = pDegrees;
	}

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
		//execute action
		if(theRobot.getArmState() == Robot.ArmState.RETRACTED) {
		if(pDegrees>0) System.out.println("Direction: Left");
		if(pDegrees<0) System.out.println("Direction: Right");
		if(pDegrees==0) System.out.println("Direction: Same direction");
		theRobot.turnRobot(pDegrees);
	}
	}

}
