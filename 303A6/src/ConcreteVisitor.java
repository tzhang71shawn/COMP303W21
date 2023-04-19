import java.util.ArrayList;

public class ConcreteVisitor implements Visitor{

	private ArrayList<basicAction> actions = new ArrayList<basicAction>() ;
	
	
	public ConcreteVisitor() {
		
	}
	@Override
	public void visit(basicAction a) {
		// TODO Auto-generated method stub
		actions.add(a);
	}
	//the purpose is for computation for question 8 
	//so I add all basic action together for easily  computing
	@Override
	public void visit(ComplexActions ca) {
		// TODO Auto-generated method stub
		actions.addAll(ca.basics());
	}
	
	public void compute(String intendedComputation) {
		double distance =0;
		int compactedItems= 0;
	
		
		for(basicAction b: actions) {
			if(b.getClass().getName()=="CompactAnObjectAction")
				compactedItems+=((CompactAnObjectAction)b).getItems();
			if(b.getClass().getName()=="MoveForwardAction")
				compactedItems+=((MoveForwardAction)b).getDistance();
		}
	
		
			if(intendedComputation=="distance") {
				System.out.println(distance);
			}
			if(intendedComputation=="CompactedItem") {
				System.out.println(compactedItems);
			}
			
			
		

		
	}

}
