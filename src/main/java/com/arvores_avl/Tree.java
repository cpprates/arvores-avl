package com.arvores_avl;

public class Tree {
    private Node node;
    private Tree leftSubTree;
    private Tree rightSubTree;    
    private int length = 0;
    private int balanceFactor = 0;
    private boolean isFull;

    public Tree() {
        this.node = null;
        this.leftSubTree = null;
        this.rightSubTree = null;
        this.isFull = false;
    }

    public Tree(Integer key) {
        this.node = new Node(key);
        this.leftSubTree = null;
        this.rightSubTree = null;
        this.isFull = false;
        this.length++;
    }

    public Node getNode() {
        return node;
    }

    public Tree getLeftSubTree() {
        return leftSubTree;
    }

    public void setLeftSubTree(Tree leftSubTree) {
        this.leftSubTree = leftSubTree;
    }
    
    public Tree getRightSubTree() {
        return rightSubTree;
    }
    
    public void setRightSubTree(Tree rightSubTree) {
        this.rightSubTree = rightSubTree;
    }

    public int getBalanceFactor() {
        return balanceFactor;
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
     * @return Boolean
     */
    public Boolean addNode(Tree node) { 
        Boolean added = false;
        if(this.node != null && this.getNode().getKey().intValue() == node.getNode().getKey().intValue())
            return false;
        if(length == 0) {
            this.node = node.getNode();
            added = this.node != null;
        } else {
            added = comparesToAdd(node);
        }
        if(added) {
            length++;
        }
        // updateBalanceFactor();
        updateIfIsFull();
        this.balancing();
        return added;
    }

    // private Boolean updateBalanceFactor() {
    //     Tree leftTree = this.getLeftSubTree();
    //     Tree rightTree = this.getRightSubTree();
    //     if(leftTree == null && rightTree == null)
    //         return false;

    //     if(leftTree != null && rightTree != null) {
    //         int left = leftTree.getBalanceFactor();
    //         int right = rightTree.getBalanceFactor();
    //         this.balanceFactor = left - ((left > 0 && right < 0) ? (right * -1) : right);
    //     }
    //     if(leftTree == null && rightTree.length > 1) {
    //         int right = rightTree.getBalanceFactor();
    //         this.balanceFactor = (right < 0) ? (right * -1) : right;
    //     }
    //     if(rightTree == null && leftTree.length > 1) {
    //         int left = leftTree.getBalanceFactor();
    //         this.balanceFactor = (left < 0) ? (left * -1) : left;
    //     }
    //     return true;
    // }

    private Boolean balancing() {
        if (isFull && this.length > 1) {
            Tree leftTree = this.getLeftSubTree();
            Tree rightTree = this.getRightSubTree();
            if (leftTree == null && rightTree.length > 1) {
                /*
                    Mudar logica de criacao de nova arvore para 
                    atribuicao da arvore antiga e anulacao do node que virou pai
                    assim o node nao perde seus filhos
                */
                if(rightTree.getLeftSubTree() == null) {  
                    rotateLeft();                         // rotacao a esquerda
                } else {
                    doubleRotateLeft();                   // dupla rotacao a esquerda 
                }
            } 
            if (rightTree == null && leftTree.length > 1) {
                /*
                Mudar logica de criacao de nova arvore para 
                atribuicao da arvore antiga e anulacao do node que virou pai
                assim o node nao perde seus filhos
                */
                if(leftTree.getRightSubTree() == null) {  
                    rotateRight();                        // rotacao a direita
                } else {
                    doubleRotateRight();                  // dupla rotacao a direita
                }
            }
            return true;
        }
        return false;
    }

    private Boolean rotateLeft() {
        boolean added = false;
        Tree rightTree = this.getRightSubTree();
        Tree util = new Tree(this.node.getKey());
        this.node = rightTree.getNode();
        this.setRightSubTree(rightTree.getRightSubTree());
        this.length--;
        added = this.addNode(util);
        updateIfIsFull();
        return added;
    }

    private Boolean rotateRight() {
        boolean added = false;
        Tree util = new Tree(this.node.getKey());
        Tree leftTree = this.getLeftSubTree();
        this.node = leftTree.getNode();
        this.setLeftSubTree(leftTree.getLeftSubTree());
        this.length--;
        added = this.addNode(util);
        updateIfIsFull();
        return added;
    }

    private Boolean doubleRotateRight() {
        boolean added = false;
        Tree leftTree = this.getLeftSubTree();
        Tree util = new Tree(leftTree.getNode().getKey());
        this.setLeftSubTree(leftTree.getRightSubTree());
        this.length--;
        this.addNode(util);
        updateIfIsFull();
        this.balancing();
        return added;
    }

    private Boolean doubleRotateLeft() {
        boolean added = false;
        Tree rightTree = this.getRightSubTree();
        Tree util = new Tree(rightTree.getNode().getKey());
        this.setRightSubTree(rightTree.getLeftSubTree());
        this.length--;
        this.addNode(util);
        updateIfIsFull();
        this.balancing();
        return added;
    }

    private Boolean comparesToAdd(Tree node) {
        Boolean added = false;
        Tree newTree = new Tree();
        if(node.getNode().getKey().intValue() < this.node.getKey().intValue()) {
            if(this.getLeftSubTree() != null) {
                added = this.getLeftSubTree().addNode(node);
            } else {
                newTree.addNode(node);
                added = this.addLeft(newTree);
            }
        } else if(node.getNode().getKey().intValue() > this.node.getKey().intValue()) {
            if(this.getRightSubTree() != null)
                added = this.getRightSubTree().addNode(node);
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
        return searchNode(node, node) != null;
    }

    /**
     * A method which receives two nodes (node and root) and compares   
     * @param node
     * @param root
     * @return boolean
     */
    private Node searchNode(Node node, Node root) {
        // if(root.getKey() != null && node.getKey() < root.getKey()) {
        //     return true;
        // } else if(root.getLeftNode() != null) {

        // }
        return null;
    }


    private void updateIfIsFull() {
        int result = 0;
        int n = 1;
        while (this.length >= result) {
            result = (int) (Math.pow(2, n)-1);
            if (this.length == result) {
                this.isFull = true;
                break;
            }
            n++;
            this.isFull = false;
        }
    }

    // private Node rotate(Node father, Node child) {

         
    // }

}