package ren.cs1102.Entities;

import ren.cs1102.Entities.Tree;

public class WillowTree extends Tree {

    private String name;

    public WillowTree() {
        super.setTreeCount(super.getTreeCount() + 1);
        this.setHeight(0);
    }

    public WillowTree(String nickName, int height) {
        this.name = nickName;
        this.setHeight(height);
        super.setTreeCount(super.getTreeCount() + 1);
    }

    public void growTree() {
        super.growTree();
        System.out.println( this.getName() + "has grown " + this.getHeight() + "cm");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

