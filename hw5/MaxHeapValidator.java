public class MaxHeapValidator implements BTValidator {
	/**
	 * Checks if the tree satisfies the max heap property.
	 *
	 * @param tree the binary tree to validate as a max heap
	 * @return true if the tree is a valid max heap, false otherwise
	 */
	private boolean isMaxHeap(IBinTree tree) {
		if (tree.isEmpty()) {
			return true;
		}

		int root = tree.getRoot();
		IBinTree left = tree.getLeft();
		IBinTree right = tree.getRight();

		if ((!left.isEmpty() && left.getRoot() > root) || (!right.isEmpty() && right.getRoot() > root)) {
			return false;
		}

		return isMaxHeap(left) && isMaxHeap(right);
	}

	/**
	 * Checks if the tree contains a specific integer.
	 *
	 * @param tree the binary tree to search
	 * @param i    the integer to find in the tree
	 * @return true if the tree contains the integer, false otherwise
	 */
	private boolean contains(IBinTree tree, int i) {
		if (tree.isEmpty()) {
			return false;
		}

		return tree.getRoot() == i || contains(tree.getLeft(), i) || contains(tree.getRight(), i);
	}

	/**
	 * Validates adding an element to the tree while maintaining max heap property.
	 *
	 * @param oldTree the original tree assumed to satisfy max heap invariants
	 * @param i       the element to add
	 * @param newTree the new tree to validate
	 * @return true if the new tree respects max heap invariants after adding the
	 *         element, false otherwise
	 */
	public boolean validAdd(IBinTree oldTree, int i, IBinTree newTree) {
		return isMaxHeap(oldTree) && isMaxHeap(newTree) && !contains(oldTree, i) && contains(newTree, i);
	}

	/**
	 * Validates removing an element from the tree while maintaining max heap
	 * property.
	 *
	 * @param oldTree the original tree assumed to satisfy max heap invariants
	 * @param i       the element to remove
	 * @param newTree the new tree to validate
	 * @return true if the new tree respects max heap invariants after removing the
	 *         element, false otherwise
	 */
	public boolean validRemove(IBinTree oldTree, int i, IBinTree newTree) {
		return isMaxHeap(oldTree) && isMaxHeap(newTree) && contains(oldTree, i) && !contains(newTree, i);
	}
}
