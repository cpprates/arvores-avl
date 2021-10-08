package com.arvores_avl;

public class Tree {
    private Node node;
    private Tree leftSubTree;
    private Tree rightSubTree;
    private int balanceFactor = 0;  
    private int length = 0;
    private int levels = 0;
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
     * @return Boolean
     */
    public Boolean insert(Tree node) { 
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
        updateIfIsFull();
        this.balancing();
        updateBalanceFactor();
        return added;
    }

    private Boolean updateBalanceFactor() {
        int oldValue = this.balanceFactor;
        if (this.length > 1) {
            if (this.getLeftSubTree() != null && this.getRightSubTree() != null) {
                this.balanceFactor = updateBalanceFactorWhenLeftAndRightIsNotNull();
            }
            else if (this.getLeftSubTree() != null)
                this.balanceFactor = this.getLeftSubTree().length;
            else if (this.getRightSubTree() != null)
                this.balanceFactor = -this.getRightSubTree().length;
        } else {
            this.balanceFactor = 0;
        }
        return oldValue != this.balanceFactor;
    }


    // atualizar o fator de balaceamento pelo num de niveis.
    private int updateBalanceFactorWhenLeftAndRightIsNotNull() {
        int leftBF = this.getLeftSubTree().balanceFactor;
        int rightBF = this.getRightSubTree().balanceFactor;

        if (leftBF == 0 && this.getRightSubTree().isFull && this.getRightSubTree().length > 1)
            rightBF += (rightBF < 0) ? -1 : 1;
        if (rightBF == 0 && this.getLeftSubTree().isFull && this.getLeftSubTree().length > 1)
            leftBF += (leftBF < 0) ? -1 : 1;
        if (rightBF < 0)
            rightBF *= -1;
        if (leftBF < 0)
            leftBF *= -1;
        if (leftBF == 0 && this.balanceFactor == -1) {
            if (this.getRightSubTree() != null && rightBF == 1 && this.getRightSubTree().balanceFactor == -1)
                return this.getRightSubTree().balanceFactor - rightBF;
            if (this.getLeftSubTree().length > 1)
                return 0;
            return -(rightBF + this.balanceFactor * -1);
        } else if (rightBF == 0 && this.balanceFactor == 1) {
            if (this.getRightSubTree().length > 1)
                return leftBF + this.balanceFactor;
        }
        return leftBF - rightBF;
    }

    private Boolean balancing() {
        // this.isFull && 
        if (this.length > 1) {
            updateBalanceFactor();
            updateLevels();
            Tree leftTree = this.getLeftSubTree();
            Tree rightTree = this.getRightSubTree();
            boolean isOneOfBothNull = leftTree == null || rightTree == null;
            if (isOneOfBothNull) {
                return balancingLessThenThreeChildTrees();
            }
            
            /*
                criar lógica para verificar atualizar o FB.
                Se a árvore não está cheia, mas o lado esquerdo tem FB=0 e o direito está cheio,
                então FB= (0 - 1) -1, não precisa balancear.
                Se não está cheia, o lado esquerdo tem FB=0, o direito não está full e tem mais de 2 filhos.
                Calcula left.length - right.length 
            */
            if (!this.isFull && (this.balanceFactor < 2 && this.balanceFactor > -2)) {
                return false;
            } 
            boolean isOneOfBothFull = leftTree.balanceFactor == 0 || rightTree.balanceFactor == 0;
            boolean osDoisNaoNull = leftTree != null && rightTree != null;
            if (isOneOfBothFull && osDoisNaoNull) {
                Tree util;
                if (leftTree.balanceFactor != 0) {
                    util = new Tree(this.node.getKey());
                    util.insert(this.getRightSubTree());
                    
                } else {
                    // ta estourando erro aqui
                    util = new Tree(this.node.getKey());
                    util.insert(this.getLeftSubTree());
                    this.length -= util.length;
                    this.node = rightTree.getNode();
                    this.addLeft(rightTree.getLeftSubTree());
                    this.addRight(rightTree.getRightSubTree());
                    this.getLeftSubTree().leftSubTree = util;
                    this.getLeftSubTree().length += util.length;
                    this.length += util.length;
                    this.getLeftSubTree().balancing();
                    this.getLeftSubTree().updateBalanceFactor();
                    this.getRightSubTree().balancing();
                    updateBalanceFactor();
                    return true;
                }
            }
            // else if (leftTree.length - rightTree.length >= 2) {
            //     Tree util = new Tree(this.node.getKey());
            //     util.setRightSubTree(this.getRightSubTree());
            //     this.length -= util.length;
            //     this.node = leftTree.getNode();
            //     this.setLeftSubTree(leftTree.getLeftSubTree());
            //     this.setRightSubTree(leftTree.getRightSubTree());
            //     this.addNode(util);
            //     updateIfIsFull();
            //     return this.balancing();
            // }
            // else if (leftTree.length - rightTree.length <= -2) {
            //     Tree util = new Tree(this.node.getKey());
            //     util.setLeftSubTree(this.getLeftSubTree());
            //     this.length -= util.length;
            //     this.node = rightTree.getNode();
            //     this.setRightSubTree(rightTree.getRightSubTree());
            //     this.setLeftSubTree(rightTree.getLeftSubTree());
            //     this.addLeft(util);
            //     updateIfIsFull();
            //     return this.balancing();
            // }
            // Se a árvore estiver cheia e o FB não for 0
            // if (this.isFull && this.balanceFactor != 0) {
            //     return this.balancing();
            // }
        }
        return false;
    }

    private boolean balancingLessThenThreeChildTrees() {
        Tree leftTree = this.getLeftSubTree();
        Tree rightTree = this.getRightSubTree();
        if (leftTree == null && rightTree.length > 1) {
            if (rightTree.getLeftSubTree() == null) {  
                return rotateLeft();                         // rotacao a esquerda
            } else {
                return doubleRotateLeft();                   // dupla rotacao a esquerda 
            }
        }
        if (rightTree == null && leftTree.length > 1) {
            if (leftTree.getRightSubTree() == null) {  
                return rotateRight();                        // rotacao a direita
            } else {
                return doubleRotateRight();                  // dupla rotacao a direita
            }
        }
        return false;
    }

    private Boolean rotateLeft() {
        boolean added = false;
        Tree rightTree = this.getRightSubTree();
        Tree util = new Tree(this.node.getKey());
        util.updateLevels();
        this.node = rightTree.getNode();
        this.addRight(rightTree.getRightSubTree());
        added = this.addLeft(util);
        updateIfIsFull();
        return added;
    }

    private Boolean rotateRight() {
        boolean added = false;
        Tree util = new Tree(this.node.getKey());
        Tree leftTree = this.getLeftSubTree();
        this.node = leftTree.getNode();
        this.addLeft(leftTree.getLeftSubTree());
        added = this.addRight(util);
        updateIfIsFull();
        return added;
    }

    private Boolean doubleRotateRight() {
        boolean added = false;
        Tree leftTree = this.getLeftSubTree();
        Tree util = new Tree(leftTree.getNode().getKey());
        this.addLeft(leftTree.getRightSubTree());
        this.length--;
        this.insert(util);
        updateIfIsFull();
        this.balancing();
        updateLevels();
        updateBalanceFactor();
        return added;
    }

    private Boolean doubleRotateLeft() {
        boolean added = false;
        Tree rightTree = this.getRightSubTree();
        Tree util = new Tree(rightTree.getNode().getKey());
        this.addRight(rightTree.getLeftSubTree());
        this.length--;
        this.insert(util);
        updateIfIsFull();
        this.balancing();
        updateLevels();
        updateBalanceFactor();
        return added;
    }

    private Boolean comparesToAdd(Tree node) {
        Boolean added = false;
        Tree newTree = new Tree();
        if(node.getNode().getKey().intValue() < this.node.getKey().intValue()) {
            if(this.getLeftSubTree() != null) {
                added = this.getLeftSubTree().insert(node);
            } else {
                newTree.insert(node);
                added = this.addLeft(newTree);
            }
        } else if(node.getNode().getKey().intValue() > this.node.getKey().intValue()) {
            if(this.getRightSubTree() != null)
                added = this.getRightSubTree().insert(node);
            else {
                newTree.insert(node);
                added = this.addRight(newTree);
            }
        }
        updateLevels();
        return added;
    }

    /**
     * A method which verifies if a param node exists and returns a boolean type if true.
     * The search happends in pre-order.
     * @param node : {@link Node}
     * @return boolean
     */
    public boolean contains(Node node) { // percorrendo em pré-ordem
        boolean found = false;
        if (this.node == null)
            return false;
        else if (this.node.getKey().intValue() == node.getKey().intValue())
            return true;
        else if (this.length == 1)
            return false;
        else if (this.getRightSubTree() == null && this.getLeftSubTree() != null)
            found = this.getLeftSubTree().contains(node);
        else if (this.getLeftSubTree() == null && this.getRightSubTree() != null)
            found = this.getRightSubTree().contains(node);
        else if (this.getLeftSubTree() != null && getRightSubTree() != null) {
            found = this.getRightSubTree().contains(node);
            if (found) 
                found = this.getLeftSubTree().contains(node);
        }
        return found;
    }

    // /**
    //  * A method which receives a node and compares with the current node root of tree.
    //  * If the node be less then root, then search on leftTree of root
    //  * @param search
    //  * @param root
    //  * @return boolean
    //  */
    // private Node searchNode(Node search) {
    //     boolean found = false;
    //     if(this.node.getKey() != null && search.getKey() < this.node.getKey()) {
    //         if(this.getLeftSubTree() != null) {
    //              this.getLeftSubTree().searchNode(search);
    //         } else if () {

    //         }
            
    //     }
    //     return null;
    // }

    private void updateLevels() {
        this.levels = (int) Math.round(Math.log(this.length)) + 1;
    }

    private void updateIfIsFull() {
        int result = 0;
        updateLength();
        updateLevels();
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

    private void updateLength() {
        Tree left = this.getLeftSubTree();
        Tree right = this.getRightSubTree();
        if (left != null && right != null) {
            this.length = 1 + left.length + right.length;
        } else if (left != null) {
            this.length = 1 + left.length;
        } else if (right != null) {
            this.length = 1 + right.length;
        } else {
            this.length = 1;
        }
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}