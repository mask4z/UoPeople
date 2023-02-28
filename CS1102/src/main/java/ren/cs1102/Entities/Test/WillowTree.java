package ren.cs1102.Entities.Test;

import ren.cs1102.Entities.Tree;

public class WillowTree extends Tree {

    private String name;

    public WillowTree() {
    }

    public WillowTree(String nickName) {
        this.name = nickName;
        super.setTreeCount(super.getTreeCount() + 1);
    }

    public WillowTree(int height) {
        this.setHeight(height);
        super.setTreeCount(super.getTreeCount() + 1);
    }
}
