package ren.cs1102.Entities;

public class Tree{
    static int height;

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Tree.height = height;
    }

    public void growTree() {
    }

    public void setTreeCount(int count) {
        Garden.setTreeCount(count);
    }

    public int getTreeCount() {
        return Garden.getTreeCount();
    }
}
