package ren.cs1102.Entities;

import java.util.ArrayList;
import java.util.List;

public class Garden {

    public static void main(String[] args) {
        List<Tree> trees = new ArrayList<>();

        Tree willowBonsai =  plantTree("Willow Bonsai");
        Tree chineseElmBonsai = plantTree("Chinese Elm Bonsai");

        trees.add(willowBonsai);
        trees.add(chineseElmBonsai);

        System.out.println("There are currently " + Tree.getTreeCount() + " tree/s in your garden.");
        System.out.println("And your garden consists of the following trees: ");

        trees.forEach(tree -> System.out.println(tree.toString()));

        matureTrees(trees);

        trees.forEach(tree -> System.out.println("Your " + tree + " is " + tree.getHeight() + "cm tall."));

    }
    public static Tree plantTree(String name) {
        Tree newTree = new Tree();
        newTree.setName(name);
        Tree.setTreeCount(Tree.getTreeCount()+ 1);

        return newTree;
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
