package com.arvores_avl;

import org.junit.*;

public class TreeTest {
    private Tree tree = new Tree();

    @Before
    public void buildTreeTest() {
        tree.insert(new Tree(10));
        tree.insert(new Tree(20));
        tree.insert(new Tree(30));
        tree.insert(new Tree(40));
        tree.insert(new Tree(50));
        tree.insert(new Tree(60));
    }

    @Test
    public void testIfIsAddOnTree() {
        
    }
}
