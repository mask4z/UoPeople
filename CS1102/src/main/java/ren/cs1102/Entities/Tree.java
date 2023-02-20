package ren.cs1102.Entities;

public class Tree {

    private int height;
    private int width;
    private String name;
    static int treeCount;

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public static int getTreeCount() {
        return treeCount;
    }
    public static void setTreeCount(int treeCount) {
        Tree.treeCount = treeCount;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public static void growTree(Tree tree) {
        tree.setHeight(tree.height + 1);
    }
    public String toString() {
        return name;
    }
}
