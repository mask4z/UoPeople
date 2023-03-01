package ren.cs1102.Entities;

import java.util.List;

public class Garden {

    static int treeCount;

    public static void main(String[] args) {
        WillowTree willowQ = new WillowTree("willowQ", 2);
        System.out.println("Hi, I'm a new tree and my name is " + willowQ.getName());
        System.out.println("And in my garden I have " + treeCount);
        willowQ.growTree();

    }

    public static int getTreeCount() {
        return treeCount;
    }

    public static void setTreeCount(int treeCount) {
        Garden.treeCount = treeCount;
    }
}
