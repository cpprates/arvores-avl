package com.arvores_avl;

import org.junit.*;

public class TreeTest {
    private Tree tree = new Tree();

    @Before
    public void buildTreeTest() {
        tree.addNode(new Node(10));
        tree.addNode(new Node(20));
        tree.addNode(new Node(30));
        tree.addNode(new Node(40));
        tree.addNode(new Node(50));
        tree.addNode(new Node(60));
    }

    @Test
    public void testIfIsAddOnTree() {
        
    }
}
