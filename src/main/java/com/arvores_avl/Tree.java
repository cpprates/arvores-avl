package com.arvores_avl;

public class Tree {
    private Node node;
    private Tree leftSubTree;
    private Tree rightSubTree;    
    private int length = 0;
    private boolean full;

    public Node getNode() {
        return node;
    }

    public Tree getLeftSubTree() {
        return leftSubTree;
    }

    public Tree getRightSubTree() {
        return rightSubTree;
    }

    private boolean addLeft(Tree left) {
        this.leftSubTree = left;
        return this.leftSubTree != null;
    }

    private boolean addRight(Tree right) {
        this.rightSubTree = right;
        return this.rightSubTree != null;
    }

    /**
     * A method which receives a node and returns if it was add on tree.
     * @param node : {@link Node}
     * @return boolean
     */
    public boolean addNode(Node node) { 
        Boolean added = false;
        if(this.node != null && this.getNode().getKey().intValue() == node.getKey().intValue())
            return false;
        if(length == 0) {
            this.node = node;
            added = this.node != null;
        } else {
            added = compares(node);
        }
        if(added) {
            length++;
        }
        updateIfIsFull();
        return added;
    }

    private boolean compares(Node node) {
        Boolean added = false;
        Tree newTree = new Tree();
        if(node.getKey().intValue() < this.node.getKey().intValue()) {
            if(this.getLeftSubTree() != null) {
                this.getLeftSubTree().addNode(node);
            } else {
                newTree.addNode(node);
                added = this.addLeft(newTree);
            }
        } else if(node.getKey().intValue() > this.node.getKey().intValue()) {
            if(this.getRightSubTree() != null)
                this.getRightSubTree().addNode(node);
            else {
                newTree.addNode(node);
                added = this.addRight(newTree);
            }
        }
        return added;
    }

    

    /**
     * A method which verifies if a param node exists and returns a boolean type if true.
     * @param node : {@link Node}
     * @return boolean
     */
    public boolean contains(Node node) {
        return searchNode(node, node);
    }

    /**
     * A method which receives two nodes (node and root) and compares   
     * @param node
     * @param root
     * @return boolean
     */
    private boolean searchNode(Node node, Node root) {
        // if(root.getKey() != null && node.getKey() < root.getKey()) {
        //     return true;
        // } else if(root.getLeftNode() != null) {

        // }
        return false;
    }


    private void updateIfIsFull() {
        int result = 0;
        int n = 1;
        while (this.length >= result) {
            result = (int) (Math.pow(2, n)-1);
            if (this.length == result) {
                this.full = true;
                break;
            }
            n++;
            this.full = false;
        }
    }

    // private Node rotate(Node father, Node child) {

         
    // }

}