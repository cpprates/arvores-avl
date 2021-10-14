package com.arvores_avl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.insert(new Tree(93));
        tree.insert(new Tree(9));
        tree.insert(new Tree(92));
        tree.insert(new Tree(83));
        tree.insert(new Tree(74));
        tree.insert(new Tree(2));
        tree.insert(new Tree(22));
        tree.insert(new Tree(56));
        tree.insert(new Tree(80));
        tree.insert(new Tree(14));
        tree.insert(new Tree(48));
        tree.insert(new Tree(81));
        tree.insert(new Tree(73));
        tree.insert(new Tree(7));
        tree.insert(new Tree(1));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the AVL Tree Application\n Choose your option to start:");

        int option;
        do {

            printOptions();
            option = scanner.nextInt();

            printReceiveParam();
            int key = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println(
                            tree.insert(new Tree(key)) ? "Print tree after modifying" : "Error. Choose another key" );
                    // print da árvore
                    break;
                case 2:
                    System.out.println(tree.contains(new Node(key)) ? "Key Found" : "Key Not found");
                    // apresentar a lista de nós consultados
                    break;
                case 3:
                    // comentado pq ainda n tem o método
                    // System.out.println(tree.remove(new Node(key)) ? "Removed successfully" :
                    // "Error");
                    // print da árvore
                    break;
            }
        } while (option != 4);
        System.out.println("Exiting Application");
        scanner.close();
    }

    public static void printOptions() {
        System.out.println("1. Insert | 2. Search | 3. Remove | 4. Exit");
    }

    public static void printReceiveParam() {
        System.out.println("Type the Integer key value:");
    }

}