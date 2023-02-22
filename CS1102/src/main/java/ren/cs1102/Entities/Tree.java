package ren.cs1102.Entities;

public class Tree {

    private int height;
    private String name;
    static int treeCount;

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
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
    public void growTree() {
        height += 1;
    }
    public String toString() {
        return name;
    }
}
