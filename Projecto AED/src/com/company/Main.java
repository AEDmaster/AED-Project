package com.company;


public class Main {

    public static void main(String[] args) {
        double[] LL = {10,13,5,17,4,6,3,2};
        double[] RR = {10,13,5,17,4,6,7,8};
        double[] LR = {10,13,5,17,4,6,2,3};
        double[] RL = {10,13,5,17,4,6,8,7};
        double[] test = {17,5,2,11,9,16,7,8,35,29,38};
        double[] test1 = {5,3,4};

        BinaryTree binaryTree = new BinaryTree(RL);
        System.out.println(binaryTree.toString());


    }
}
