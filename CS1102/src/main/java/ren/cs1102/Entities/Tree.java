package ren.cs1102.Entities;

public class Tree{
    private int height;     // height in cm

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void growTree() {
        setHeight(getHeight() + 1);
    }

    public void setTreeCount(int count) {
        Garden.setTreeCount(count);
    }

    public int getTreeCount() {
        return Garden.getTreeCount();
    }
}
