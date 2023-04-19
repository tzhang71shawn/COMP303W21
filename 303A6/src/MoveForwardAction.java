

//An action to move forward

public class MoveForwardAction extends basicAction{
	//attributes containing robot
	private Robot theRobot;
	private double pDistance;
	
	//constructor to build action
	public MoveForwardAction(Robot theRobot,  double pDistance) {
		super(theRobot);
		this.pDistance = pDistance;
	}

	@Override
	public void specificExecute() {
		// TODO Auto-generated method stub
		//execute action
		if(theRobot.getArmState() == Robot.ArmState.RETRACTED)
		theRobot.moveRobot(pDistance);
	}
	public double getDistance() {
		return this.pDistance;
	}
}
