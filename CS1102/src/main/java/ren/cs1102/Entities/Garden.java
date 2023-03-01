package ren.cs1102.Entities;

import java.util.List;

public class Garden {

    static int treeCount;

    public static void main(String[] args) {
        WillowTree willowQ = new WillowTree("willowQ");
        System.out.println("Hi, I'm a new tree and my name is " + willowQ.getName());
        System.out.println("And in my garden I have " + treeCount);

    }

    public static int getTreeCount() {
        return treeCount;
    }

    public static void setTreeCount(int treeCount) {
        Garden.treeCount = treeCount;
    }

    public static void plantWillowTree(int height) {
        WillowTree firstWillow = new WillowTree(2);
    }

    public static void matureTrees(List<Tree> trees) {
            try {
                for (Tree tree : trees) {
                    tree.growTree();
                }
        } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}
