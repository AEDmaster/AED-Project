package com.company;

import sun.reflect.generics.tree.Tree;

/**
 * Created by lamec on 3/17/2017.
 */
public class TreeNode {
    public BinaryTree left;
    public BinaryTree right;
    public InformationType informationType;
    public TreeNode(double percentage){
        this.informationType = new ElectricityInformation(percentage);
    }

}
