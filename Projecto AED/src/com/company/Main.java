package com.company;

public class Main {

    public static void main(String[] args) {
        int[] oi = {3, 2, 1, 5, 4, 6, 7};
        //int[] oi = {17, 5, 35, 2, 11, 29, 38, 9, 16, 7, 8};
        BinaryTree binaryTree = new BinaryTree(oi);
        BinaryTree.switchNodes(binaryTree, binaryTree.left, binaryTree.right, binaryTree.right.left);
        System.out.println(binaryTree.toString());
        System.out.println("Ola bom dia");
    }
}
