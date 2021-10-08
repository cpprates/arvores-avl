package com.arvores_avl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tree tree = new Tree();
        tree.insert(new Tree(10));
        tree.insert(new Tree(20));
        tree.insert(new Tree(30));
        tree.insert(new Tree(40));
        tree.insert(new Tree(50));
        tree.insert(new Tree(60));
        tree.insert(new Tree(70));

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