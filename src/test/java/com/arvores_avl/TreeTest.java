package com.arvores_avl;

import org.junit.*;

public class TreeTest {
    private Tree tree = new Tree();

    @Before
    public void buildTreeTest() {
        tree.addNode(new Tree(10));
        tree.addNode(new Tree(20));
        tree.addNode(new Tree(30));
        tree.addNode(new Tree(40));
        tree.addNode(new Tree(50));
        tree.addNode(new Tree(60));
    }

    @Test
    public void testIfIsAddOnTree() {
        
    }
}
