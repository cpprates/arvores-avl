package com.arvores_avl;

public class Tree {
    Node root;
    private int length = 0;
    
    public boolean addNode(Node node) {
        if(length == 0)
            this.root = node;
            return this.root != null;
    }

    /**
     * A method which verifies if a param node exists and returns a boolean type if true.
     * @param node : {@link Node}
     * @return boolean
     */
    public boolean contains(Node node) {
        return searchNode(node, root);
    }

    /**
     * A method which receives two nodes (node and root) and compares   
     * @param node
     * @param root
     * @return boolean
     */
    private boolean searchNode(Node node, Node root) {
        if(root.getKey() != null && node.getKey() < root.getKey()) {
            return true;
        } else if(root.getLeftNode() != null) {

        }
        return false;
    }
}


