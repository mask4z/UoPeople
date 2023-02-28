package ren.cs1102.Entities;

import ren.cs1102.Entities.Test.WillowTree;

import java.util.ArrayList;
import java.util.List;

public class Garden {

    static int treeCount;

    public static void main(String[] args) {

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
