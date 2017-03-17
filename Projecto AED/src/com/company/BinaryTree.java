
package com.company;

import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lamec on 3/7/2017.
 */
public class BinaryTree extends DataStructure {
    private int height;
    private int balancingFactor;
    private TreeNode node;

    public BinaryTree(TreeNode node){
        this.node = node;
        setLeft(null);
        setRight(null);
        calcBalancingFactor();
        calcHeight();
    }
    //Creates a tree from one value.
    public BinaryTree(double percentage) {
        this.node = new TreeNode(percentage);
        setLeft(null);
        setRight(null);
        this.setHeight(1);
        this.setBalancingFactor(0);
    }

    //Creates a tree from the first value of the "values" array and inserts the remaining values afterwards.
    public BinaryTree(double[] values){
        this.node = new TreeNode(values[0]);
        setLeft(null);
        setRight(null);
        this.setHeight(1);
        this.setBalancingFactor(0);
        double[] n = Arrays.copyOfRange(values,1,values.length);
        this.insert(n);
    }

    //Insert a single value into the tree
    public void insert(double n, boolean isRoot) {
        BinaryTree binaryTree;
        if (n < getValue()) {//if the given value is lower than the value of the current node
            if(getLeft() != null){
                binaryTree = getLeft();
            }
            else{ //if there is no child to the left, create a leaf for "n" and exit
                setLeft(new BinaryTree(n));
                return;
            }
        } else {//same logic as above, except to the right
            if(getRight() != null){
                binaryTree = getRight();
            }
            else{
                setRight(new BinaryTree(n));
                return;
            }
        }
        binaryTree.insert(n, false); //keep searching for a place for "n"

        rebalanceTree();
    }

    //Inserts several values into the tree
    public void insert(double[] n) {
        for (int i = 0; i < n.length; i++) { //pretty self explanatory
            this.insert(n[i],true);

        }
    }

    //Use to remove a leaf from the tree.
    //IMPORTANT: node is meant to be this.left or this.right, otherwise the method will NOT work.
    public void remove(double percentage) {
        if(getValue() == percentage){
            BinaryTree binaryTree = this;
            if (binaryTree != null) {
                if (binaryTree.getLeft() == null && binaryTree.getRight() == null) { //binaryTree has no children
                    binaryTree = null;
                } else if (binaryTree.getLeft() != null && binaryTree.getRight() == null) {//binaryTree has only one child, to the left
                    binaryTree.node = binaryTree.getLeft().node; //binaryTree.node is replaced by its child to the left
                } else if (binaryTree.getLeft() == null && binaryTree.getRight() != null) {//binaryTree.node has only one child, to the right
                    binaryTree.node = binaryTree.getRight().node; //binaryTree.node is replaced by its child to the right
                } else {//binaryTree has two children, so we need to do a bit more work...

                    BinaryTree rightChild = binaryTree.getRight(); //"rightChild" holds the child to the right of the node we want to remove
                    BinaryTree closestValue = rightChild.getLeft(); //"closest" will be used to find the node that will replace binaryTree.node

                    if (closestValue != null) {//The closest value to "binaryTree.value" is going to be in the leftmost child of RightChild (or rightmost child of LeftChild, but we are not using that here)

                        while (closestValue.getLeft() != null) {//So we find the closest value to "binaryTree.value" and we put that branch in the variable "aux"
                            closestValue = closestValue.getLeft();
                        }

                        //Once "closestValue" holds the value we want, we put "closestValue.node" where "binaryTree.node" used to be
                        TreeNode aux = closestValue.node;
                        closestValue.node = closestValue.getRight().node;
                        aux.left = binaryTree.getLeft();
                        aux.right = binaryTree.getRight();
                        binaryTree.node = aux;
                    }

                    else {
                        rightChild.setLeft(binaryTree.getLeft());
                        binaryTree.node = rightChild.node;
                    }
                }
            }
        }
        else if(getValue() < percentage && getLeft() != null)
            getLeft().remove(percentage);
        else if(getValue() > percentage && getRight() != null)
            getRight().remove(percentage);
        else
            return;
    }


    //Use to find the largest value in this node and ancestors
    public void findLargest(){
        if(getRight() != null){
            getRight().findLargest();
        }
        else{
            System.out.println("The largest value is: " +  getValue());
        }
    }

    //Use to check if a certain number is in this node and ancestors (can be adapted to search)
    public void findNumber(float n){
        if(n < getValue()){
            if(getLeft() == null)
                System.out.println("Number is not in tree");
            else
                getLeft().findNumber(n);
        }
        else if(getValue() < n){
            if(getRight() == null)
                System.out.println("Number is not in tree");
            else
                getRight().findNumber(n);
        }
        else{
            System.out.println("Number is in tree");
        }
    }

    //Use to calculate the size of this node
    public int calcSize(){
        int totalSize = 1;
        if(getLeft() != null){
            totalSize += getLeft().calcSize();
        }
        if(getRight() != null){
            totalSize += getRight().calcSize();
        }
        return totalSize;
    }

    //Use to calculate the height of this node
    public int calcHeight(){
        int heightLeft = 1;
        int heightRight = 1;
        if(getLeft() != null){
            heightLeft += getLeft().calcHeight();
        }
        if (getRight() != null) {
            heightRight += getRight().calcHeight();
        }
        this.setHeight((heightLeft < heightRight) ? heightRight : heightLeft);
        return (heightLeft < heightRight) ? heightRight : heightLeft;
    }

    //Use to set and calculate the balancing factor
    public int calcBalancingFactor(){
        int heightLeft = 0;
        int heightRight = 0;
        if(getLeft() != null){
            getLeft().calcBalancingFactor();
            heightLeft += getLeft().calcHeight();
        }
        if (getRight() != null) {
            getRight().calcBalancingFactor();
            heightRight += getRight().calcHeight();
        }
        this.setBalancingFactor(heightLeft - heightRight);
        return heightLeft - heightRight;
    }

    public void rebalanceTree(){
        calcBalancingFactor();
        calcHeight();
        while(getBalancingFactor() < -1 || 1 < getBalancingFactor()){
            if(1 < getBalancingFactor()){
                if(getLeft().getBalancingFactor() < 0){
                      getLeft().rotateLeft();
                      calcBalancingFactor();
                      calcHeight();
                }
                rotateRight();
                calcBalancingFactor();
                calcHeight();
            }
            if(getBalancingFactor() < -1){
                if(getRight().getBalancingFactor() > 0){
                    getRight().rotateRight();
                    calcBalancingFactor();
                    calcHeight();
                }
                rotateLeft();
                calcBalancingFactor();
                calcHeight();
            }
        }
    }


    private void rotateLeft(){
        TreeNode A = this.node;
        TreeNode B = A.right.node;
        TreeNode T = (B.left != null) ? B.left.node: null;
        this.node = B;
        if(B.left != null){
        B.left.node = A;
        A.right.node = T;}
        else
            B.left = new BinaryTree(A);
    }

    private void rotateRight(){
        TreeNode A = this.node;
        TreeNode B = A.left.node;
        TreeNode T = (B.right != null) ? B.right.node: null;

        this.node = B;
        if(B.right != null){
            B.right.node = A;
            A.left.node = T;
        }

        else
            B.right = new BinaryTree(A);
    }

    /*
    public static BinaryTree rotationLL(BinaryTree nodeA, BinaryTree nodeB, BinaryTree nodeC){
        nodeA.left = nodeB.right;


    public static BinaryTree rotationLR(BinaryTree nodeA, BinaryTree nodeB, BinaryTree nodeC){
        nodeC.left = nodeB;
        nodeC.right = nodeA;
        nodeA.left = null;
        nodeA.right = null;
        nodeB.right = null;
        return nodeC;
    }

    public static BinaryTree rotationRR(BinaryTree nodeA, BinaryTree nodeB, BinaryTree nodeC){
        nodeA.right = nodeB.left;
        nodeB.left = nodeA;
        return nodeB;
    }

    public static BinaryTree rotationRL(BinaryTree nodeA, BinaryTree nodeB, BinaryTree nodeC){
        nodeA.right = null;
        nodeB.left = null;
        nodeC.left = nodeA;
        nodeC.right = nodeB;
        return nodeC;
    }*/



    public int getHeight() {
        return height;
    }

    public int getBalancingFactor() {
        return balancingFactor;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBalancingFactor(int balancingFactor) {
        this.balancingFactor = balancingFactor;
    }

    public double getValue(){
        return (double)node.informationType.getValue();
    }

    public BinaryTree getLeft(){
        return this.node.left;
    }

    public BinaryTree getRight(){
        return this.node.right;
    }

    public void setLeft(BinaryTree binaryTree){
        this.node.left = binaryTree;
    }

    public void setRight(BinaryTree binaryTree){
        this.node.right = binaryTree;
    }

    @Override
    public String toString() {
        if (getLeft() != null && getRight() != null) {
            return "BinaryTree: " + getValue() +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = " + getLeft().toString() +
                    "\nright = " + getRight().toString() +
                    '}';
        } else if (getLeft() == null && getRight() != null) {
            return "BinaryTree: " + getValue() +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = null" +
                    "\nright = " + getRight().toString() +
                    '}';
        } else if (getLeft() != null && getRight() == null) {
            return "BinaryTree: " + getValue() +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = " + getLeft().toString() +
                    "\nright = null" +
                    '}';
        } else {
            return "BinaryTree: " + getValue() +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = null" +
                    "\nright = null" +
                    '}';
        }
    }
}
