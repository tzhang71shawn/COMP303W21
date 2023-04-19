/* visitor interface that visits elements
 * using visitor design pattern
 */


public interface Visitor {
	
	
	
	
	void visit(basicAction a);
	void visit(ComplexActions ca);
}
