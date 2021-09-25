package com.arvores_avl;

public class Node {
    private Node rootNode;
    private Node leftNode;
    private Node rightNode;
    private Integer key;

    public Node() {
        this.rootNode = null;
        this.leftNode = null;
        this.rightNode = null;
        this.key = null;
    }

    public boolean addLeft(Node left) {
        this.leftNode = left;
        return this.leftNode != null;
    }

    public boolean addRight(Node right) {
        this.rightNode = right;
        return this.rightNode != null;
    }

    public boolean addRoot(Node root) {
        this.rootNode = root;
        return this.rootNode != null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Node getRootNode() {
        return rootNode;
    }
}
