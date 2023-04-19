
public class recharge implements Action {
	private Action a;
	
	public recharge(Action a) {
		this.a=a;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		if(a instanceof basicAction) {
		((basicAction) a).recharge();
	}
		if(a instanceof ComplexActions) {
			((basicAction) a).recharge();
		}
	
}
}
