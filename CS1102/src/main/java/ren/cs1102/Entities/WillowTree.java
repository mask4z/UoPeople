package ren.cs1102.Entities;

import ren.cs1102.Entities.Tree;

public class WillowTree extends Tree {

    private String name;

    public WillowTree() {
        super.setTreeCount(super.getTreeCount() + 1);
    }

    public WillowTree(String nickName) {
        this.name = nickName;
        super.setTreeCount(super.getTreeCount() + 1);
    }

    public WillowTree(int height) {
        this.setHeight(height);
        super.setTreeCount(super.getTreeCount() + 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
