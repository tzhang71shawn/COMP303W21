/* element interface that accepts visitor
 * using visitor design pattern
 */

public interface Element {
	void accept(Visitor v);
}
