package com.app;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TodoApp {

    private static final ArrayList<String> tasks = new ArrayList<>();
    private static final String FILE_NAME = "tasks.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks();

        System.out.println("Welcome to Todo List App!");

        while (true) {
            displayMenu();
            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    displayTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== TO-DO LIST APP =====");
        System.out.println("1. View Tasks");
        System.out.println("2. Add Task");
        System.out.println("3. Remove Task");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getValidChoice() {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
                if (choice >= 1 && choice <= 4) {
                    return choice;
                } else {
                    System.out.print("Please enter a number between 1 and 4: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter a number: ");
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    private static void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addTask() {
        System.out.print("Enter new task: ");
        String task = scanner.nextLine().trim();

        if (task.isEmpty()) {
            System.out.println("Task cannot be empty!");
            return;
        }

        tasks.add(task);
        saveTasks();
        System.out.println("✅ Task added successfully!");
    }

    private static void removeTask() {
        displayTasks();

        if (tasks.isEmpty()) {
            return;
        }

        System.out.print("Enter task number to remove: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index > 0 && index <= tasks.size()) {
                String removedTask = tasks.remove(index - 1);
                saveTasks();
                System.out.println("✅ Removed: " + removedTask);
            } else {
                System.out.println("❌ Invalid task number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Please enter a valid number!");
            scanner.nextLine();
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No previous tasks found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    tasks.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }
}
