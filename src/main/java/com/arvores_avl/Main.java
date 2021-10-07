package com.arvores_avl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.addNode(new Tree(30));
        tree.addNode(new Tree(10));
        tree.addNode(new Tree(20));
        tree.addNode(new Tree(40));
        tree.addNode(new Tree(50));
        tree.addNode(new Tree(60));
        tree.addNode(new Tree(70));

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
                            tree.addNode(new Tree(key)) ? "Error. Choose another key" : "Print tree after modifying");
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
    }

    public static void printOptions() {
        System.out.println("1. Insert | 2. Search | 3. Remove | 4. Exit");
    }

    public static void printReceiveParam() {
        System.out.println("Type the Integer key value:");
    }
}
