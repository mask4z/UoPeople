package Unit3.LearningJournal;

import java.util.Random;

/**
 * This program tests the following theory:
 * "if the [binary sort] tree is created by inserting items in a random order,
 * there is a high probability that the tree is approximately balanced."
 */

public class LJExercise {

    public static void main(String[] args) {
        RandomBinarySortTree testTree = new RandomBinarySortTree(1023);
        System.out.println("The average depth of all the leaves are "
                + testTree.sumOfDepthOfAllLeaves(testTree.root, 0) / testTree.countLeaves(testTree.root) + ".");
        System.out.println("This tree's max depth of all the leaves are "
                + testTree.maxDepth(testTree.root, 0) + ".");
    }

    public static class RandomBinarySortTree {

        public RandomBinarySortTree(Integer size) {

            Random random = new Random();

            for (int i = 0; i < size; i++) {
                this.treeInsert(random.nextInt());
            }
        }

        /**
         * An object of type TreeNode represents one node in a binary tree of real numbers.
         */
        public static class TreeNode {
            Integer item;      // The data in this node.
            TreeNode left;    // Pointer to left subtree.
            TreeNode right;   // Pointer to right subtree.

            TreeNode(Integer item) {
                // Constructor.  Make a node containing the specified number.
                // Note that left and right pointers are initially null.
                this.item = item;
            }
        }  // end nested class TreeNode


        private TreeNode root;  // Pointer to the root node in a binary tree.

        /**
         * Add the item to the binary sort tree to which the global variable
         * "root" refers.  (Note that root can't be passed as a parameter to
         * this routine because the value of root might change, and a change
         * in the value of a formal parameter does not change the actual parameter.)
         */
        private void treeInsert(Integer newItem) {
            if (root == null) {
                // The tree is empty.  Set root to point to a new node containing
                // the new item.  This becomes the only node in the tree.
                root = new TreeNode(newItem);
                return;
            }
            TreeNode runner;  // Runs down the tree to find a place for newItem.
            runner = root;   // Start at the root.
            while (true) {
                if (newItem.compareTo(runner.item) < 0) {
                    // Since the new item is less than the item in runner,
                    // it belongs in the left subtree of runner.  If there
                    // is an open space at runner.left, add a new node there.
                    // Otherwise, advance runner down one level to the left.
                    if (runner.left == null) {
                        runner.left = new TreeNode(newItem);
                        return;  // New item has been added to the tree.
                    } else
                        runner = runner.left;
                } else {
                    // Since the new item is greater than or equal to the item in
                    // runner it belongs in the right subtree of runner.  If there
                    // is an open space at runner.right, add a new node there.
                    // Otherwise, advance runner down one level to the right.
                    if (runner.right == null) {
                        runner.right = new TreeNode(newItem);
                        return;  // New item has been added to the tree.
                    } else
                        runner = runner.right;
                }
            } // end while
        }  // end treeInsert()

        /**
         * This method iterates through the binary sort tree and count all the leaves.
         * Recursion is used to find a node that doesn't have child nodes and then return 1.
         *
         * @param node
         * @return The total of leaves in the tree.
         */
        private int countLeaves(TreeNode node) {

            if (node == null) {
                return 0;
            }
            if (isLeaf(node)) {
                return 1;
            }

            return countLeaves(node.left) + countLeaves(node.right);
        } // end of countLeaves()

        /**
         * This method is used to check whether the node passed as a parameter
         * is a leaf.
         *
         * @param node
         * @return True if the node is indeed a leaf.
         */
        private boolean isLeaf(TreeNode node) {
            if (node == null) {
                // An exception is thrown when a null is passed through to this method.
                // this prevents a NullPointerException.
                throw new IllegalArgumentException("The provided node is null.");
            }
            if (node.left == null && node.right == null) {
                return true;
            }
            return false;
        } // end of isLeaf()

        /**
         * This method calculates the total of all the leaves' depths.
         * Normally, this method is called with the 'root' passed as the node,
         * and '0' as the depth.
         *
         * @param node
         * @param depth
         * @return an int value of the sum of the depths of all the leaves in a tree.
         */
        private int sumOfDepthOfAllLeaves(TreeNode node, int depth) {

            if (node == null) {
                return 0;
            }
            if (isLeaf(node)) {
                return depth;
            } else {
                return sumOfDepthOfAllLeaves(node.left, depth + 1)
                        + sumOfDepthOfAllLeaves(node.right, depth + 1);
            }
        } // end of sumOfDepthOfAllLeaves()

        /**
         * This method is used to get the deepest node.
         * Again, depth is passed through as a parameter in order to properly
         * enable recursion.
         * This method is also normally called with 'root' as the node,
         * and '0' as the depth for the parameters.
         *
         * @param node
         * @param depth
         * @return an int value of the deepest node in the tree.
         */
        private int maxDepth(TreeNode node, int depth) {
            if (node == null) {
                return 0;
            }
            if (!isLeaf(node)) {
                int maxLeft = maxDepth(node.left, depth + 1);
                int maxRight = maxDepth(node.right, depth + 1);

                if (maxLeft > maxRight) {
                    return maxLeft;
                } else {
                    return maxRight;
                }
            }
            return depth;
        } // end of maxDepth()
    } // end of class RandomBinarySortTree
}// end of class LJExercise
