
package com.company;

import java.util.Arrays;

/**
 * Created by lamec on 3/7/2017.
 */
public class BinaryTree extends YearDataStructure{
    public BinaryTree left;
    public BinaryTree right;
    private int value;
    private int height;
    private int balancingFactor;

    //Creates a tree from one value.
    public BinaryTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.setHeight(1);
        this.setBalancingFactor(0);
    }

    //Creates a tree from the first value of the "values" array and inserts the remaining values afterwards.
    public BinaryTree(int[] values){
        this.value = values[0];
        this.left = null;
        this.right = null;
        this.setHeight(1);
        this.setBalancingFactor(0);
        int[] n = Arrays.copyOfRange(values,1,values.length);
        this.insert(n);
    }

    //Insert a single value into the tree
    public void insert(int n) {
        BinaryTree node;
        if (n < value) {//if the given value is lower than the value of the current node
            if(this.left != null){
                node = this.left;
            }
            else{ //if there is no child to the left, create a leaf for "n" and exit
                this.left = new BinaryTree(n);
                this.calcHeight();
                this.calcBalancingFactor();
                return;
            }
        } else {//same logic as above, except to the right
            if(this.right != null){
                node = this.right;
            }
            else{
                this.right = new BinaryTree(n);
                this.calcHeight();
                this.calcBalancingFactor();
                return;
            }
        }
        node.insert(n); //keep searching for a place for "n"

        //after placing "n" recalculate the balancing factor and height
        this.calcHeight();
        this.calcBalancingFactor();
    }

    //Inserts several values into the tree
    public void insert(int[] n) {
        for (int i = 0; i < n.length; i++) { //pretty self explanatory
            this.insert(n[i]);
        }
    }

    //Use to remove a leaf from the tree.
    //IMPORTANT: node is meant to be this.left or this.right, otherwise the method will NOT work.
    public void remove(BinaryTree node) {
        if (node != null) {
            if (node.left == null && node.right == null) { //node has no children
                node = null;
            } else if (node.left != null && node.right == null) {//node has only one child, to the left
                node = node.left; //node is replaced by its child to the left
            } else if (node.left == null && node.right != null) {//node has only one child, to the right
                node = node.right; // node is replaced by its child to the right
            } else {//node has two children, so we need to do a bit more work...

                BinaryTree rightChild = node.right; //"rightChild" holds the child to the right of the node we want to remove
                BinaryTree aux = rightChild.left; //"aux" will be used to find the leaf that will replace node
                BinaryTree previous = rightChild; //this will be used to keep track of "aux"'s parent

                if (aux != null) {//The closest value to "node.value" is going to be in the leftmost child of RightChild (or rightmost child of LeftChild, but we are not using that here)

                    while (aux.left != null) {//So we find the closest value to "node.value" and we put that branch in the variable "aux"
                        previous = aux;
                        aux = aux.left;
                    }

                    //Once "aux" holds the value we want, we put it where "node" used to be, if "aux.right" exists then it will replace "aux"'s position in the tree

                    aux.left = node.left; //"aux" inherits "node.left"
                    node = aux; //"node" becomes "aux", this way "node"'s parent will be linked to "aux" instead
                    previous.left = aux.right; //"aux.right" takes "aux"'s place in the tree, this means we link it to "aux"'s previous parent
                    aux.right = rightChild; //"rightChild", which was originally "node.right", will become "aux.right" effectively replacing node from the tree
                }

                else {//if node's rightChild has no child to the left, then that mean rightChild is the closest value to node we're going to find, so we put it in its place.
                    rightChild.left = node.left;
                    node = rightChild;
                }
            }
        }
    }

    //Use to find the largest value in this node and ancestors
    public void findLargest(){
        if(this.right != null){
            this.right.findLargest();
        }
        else{
            System.out.println("The largest value is: " +  this.value);
        }
    }

    //Use to check if a certain number is in this node and ancestors (can be adapted to search)
    public void findNumber(int n){
        if(n < this.value){
            if(this.left == null)
                System.out.println("Number is not in tree");
            else
                this.left.findNumber(n);
        }
        else if(this.value < n){
            if(this.right == null)
                System.out.println("Number is not in tree");
            else
                this.right.findNumber(n);
        }
        else{
            System.out.println("Number is in tree");
        }
    }

    //Use to calculate the size of this node
    public int calcSize(){
        int totalSize = 1;
        if(this.left != null){
            totalSize += left.calcSize();
        }
        if(this.right != null){
            totalSize += right.calcSize();
        }
        return totalSize;
    }

    //Use to calculate the height of this node
    public int calcHeight(){
        int heightLeft = 1;
        int heightRight = 1;
        if(this.left != null){
            heightLeft += left.calcHeight();
        }
        if (this.right != null) {
            heightRight += right.calcHeight();
        }
        this.setHeight((heightLeft < heightRight) ? heightRight : heightLeft);
        return (heightLeft < heightRight) ? heightRight : heightLeft;
    }

    //Use to calculate the number of full nodes
    public int getFullNodes(){
        int counter = 0;
        if(this.left != null){
            if(this.right != null){
                counter += right.getFullNodes();
                counter += 1;
            }
            counter += left.getFullNodes();
        }
        else if (this.right != null){
            counter += right.getFullNodes();

        }

        /*
        int i = 1;
        ArrayList<BinaryTree> oldBinaryTreeArrayList = new ArrayList<BinaryTree>(i);
        do{
            i *= 2;
            ArrayList<BinaryTree> binaryTreeArrayList = new ArrayList<BinaryTree>(i);
            for(int j = 0; j < oldBinaryTreeArrayList.size(); j++){
                if(oldBinaryTreeArrayList.get(i) != null){
                    if(oldBinaryTreeArrayList.get(i).left != null){
                        binaryTreeArrayList.add(oldBinaryTreeArrayList.get(i).left);
                        if(oldBinaryTreeArrayList.get(i).right != null){
                            counter++;
                        }
                    }
                    if(oldBinaryTreeArrayList.get(i).right != null){
                        binaryTreeArrayList.add(oldBinaryTreeArrayList.get(i).right);
                    }
                }
            }
        }while(oldBinaryTreeArrayList.isEmpty());*/

        return counter;
    }

    //Use to set and calculate the balancing factor
    public int calcBalancingFactor(){
        int heightLeft = 0;
        int heightRight = 0;
        if(this.left != null){
            heightLeft += left.calcHeight();
        }
        if (this.right != null) {
            heightRight += right.calcHeight();
        }
        this.setBalancingFactor(heightLeft - heightRight);
        return heightLeft - heightRight;
    }

    public void rebalanceTree(){
        if(left != null){
            left.rebalanceTree();
        }
        if(right != null){
            right.rebalanceTree();
        }

        BinaryTree node;

        if(Math.abs(balancingFactor) < 2){
            return;
        }
        else if(balancingFactor < 0){
            node = right;
        }
        else{
            node = left;
        }

    }

    public static void switchNodes(BinaryTree node1Parent, BinaryTree node1, BinaryTree node2Parent, BinaryTree node2){
        BinaryTree aux;
        BinaryTree auxLeft;
        BinaryTree auxRight;

        auxLeft = node1.left;
        auxRight = node1.right;

        node1.left = node2.left;
        node1.right = node2.right;

        node2.left = auxLeft;
        node2.right = auxRight;

        if(node1Parent != node2Parent){
            if(node1Parent.left == node1){
                node1Parent.left = node2;
            }
            else if(node1Parent.right == node1){
                node1Parent.right = node2;
            }
            else{
                System.out.println("Invalid node 1 parent");
            }
            if(node2Parent.left == node2){
                node2Parent.left = node1;
            }
            else if(node2Parent.right == node2){
                node2Parent.right = node1;
            }
            else{
                System.out.println("Invalid node 2 parent");
            }
        }
        else{
            aux = node1Parent.left;
            node1Parent.left = node1Parent.right;
            node1Parent.right = aux;
        }
    }

    @Override
    public String toString() {
        if (left != null && right != null) {
            return "BinaryTree: " + value +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = " + left.toString() +
                    "\nright = " + right.toString() +
                    '}';
        } else if (left == null && right != null) {
            return "BinaryTree: " + value +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = null" +
                    "\nright = " + right.toString() +
                    '}';
        } else if (left != null && right == null) {
            return "BinaryTree: " + value +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = " + left.toString() +
                    "\nright = null" +
                    '}';
        } else {
            return "BinaryTree: " + value +
                    "\nheight = " + height +
                    "\nbalancing Factor = " + balancingFactor +
                    "{\nleft = null" +
                    "\nright = null" +

                    '}';
        }
    }

    public int getValue() {
        return value;
    }

    public int getHeight() {
        return height;
    }

    public int getBalancingFactor() {
        return balancingFactor;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBalancingFactor(int balancingFactor) {
        this.balancingFactor = balancingFactor;
    }
}
