import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Examples {
	MaxHeapValidator validator = new MaxHeapValidator();
	EmptyBT leaf = new EmptyBT();

	@Test
	public void testAdd() {
		assertTrue(validator.validAdd(leaf, 1, new NodeBT(1, leaf, leaf)));
	}

	@Test
	public void testAddEmpty() {
		assertFalse(validator.validAdd(leaf, 1, leaf));
	}

	@Test
	public void testAddFull() {
		assertFalse(validator.validAdd(new NodeBT(2, leaf, leaf), 1, new NodeBT(1, leaf, leaf)));
	}

	@Test
	public void testRemove() {
		assertTrue(validator.validRemove(new NodeBT(2, leaf, leaf), 2, leaf));
	}

	@Test
	public void testRemoveEmpty() {
		assertFalse(validator.validRemove(leaf, 1, leaf));
	}

	@Test
	public void testRemoveFull() {
		assertFalse(validator.validRemove(new NodeBT(2, leaf, leaf), 1, new NodeBT(1, leaf, leaf)));
	}
}
