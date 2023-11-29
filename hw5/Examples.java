import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Examples {
	EmptyBT leaf = new EmptyBT();

	@Test
	public void testAdd1() {
		MaxHeapValidator validator = new MaxHeapValidator();
		assertTrue(validator.validAdd(leaf, 1, new NodeBT(1, leaf, leaf)));
	}

	@Test
	public void testAddButEmpty() {
		MaxHeapValidator validator = new MaxHeapValidator();
		assertFalse(validator.validAdd(leaf, 1, leaf));
	}

	public void testRemoveEmpty() {
		MaxHeapValidator validator = new MaxHeapValidator();
		assertTrue(validator.validRemove(new NodeBT(2, leaf, leaf), 2, leaf));
	}
}
