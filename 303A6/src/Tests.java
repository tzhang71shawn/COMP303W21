import static org.junit.Assert.*;

	public class Tests {
		
		public static void main(String[] args) {
			WallE robotw = new WallE();
			CompactAnObjectAction a1 = new CompactAnObjectAction(robotw);
			MoveForwardAction a2 =  new MoveForwardAction(robotw, 10.0);
			Turn90DegreesAction a3 = new Turn90DegreesAction(robotw,90);
			GrabAnObjectAction a4= new GrabAnObjectAction(robotw);
			EmptyTheCompactorAction a5 = new EmptyTheCompactorAction(robotw);
			ReleaseAnObjectAction a6 = new ReleaseAnObjectAction(robotw);						
			
		ComplexActions ca = new ComplexActions();
			test1(a5);
		}
		
		public static void test1(Action a1) {
			System.out.println("Test 1 /n");
			a1.execute();
			System.out.println("");
		}
		
		
		

}
