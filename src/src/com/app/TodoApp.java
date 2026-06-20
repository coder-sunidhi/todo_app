package com.app;

    import java.io.*;
    import java.util.ArrayList;
	import java.util.Scanner;

	public class TodoApp {

	    static ArrayList<String> tasks = new ArrayList<>();
	    static final String FILE_NAME = "tasks.txt";

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        loadTasks(); // Load saved tasks

	        while (true) {
	            System.out.println("\n===== TO-DO LIST APP =====");
	            System.out.println("1. View Tasks");
	            System.out.println("2. Add Task");
	            System.out.println("3. Remove Task");
	            System.out.println("4. Exit");

	            System.out.print("Enter choice: ");
	            int choice = sc.nextInt();
	            sc.nextLine();

	            switch (choice) {

	                case 1:
	                    displayTasks();
	                    break;

	                case 2:
	                    System.out.print("Enter task: ");
	                    String task = sc.nextLine();
	                    tasks.add(task);
	                    saveTasks();
	                    System.out.println("Task added!");
	                    break;

	                case 3:
	                    displayTasks();

	                    if (tasks.size() == 0) {
	                        break;
	                    }

	                    System.out.print("Enter task number to remove: ");
	                    int index = sc.nextInt();

	                    if (index > 0 && index <= tasks.size()) {
	                        tasks.remove(index - 1);
	                        saveTasks();
	                        System.out.println("Task removed!");
	                    } else {
	                        System.out.println("Invalid task number");
	                    }
	                    break;

	                case 4:
	                    System.out.println("Exiting...");
	                    sc.close();
	                    return;

	                default:
	                    System.out.println("Invalid choice");
	            }
	        }
	    }

	    static void displayTasks() {
	        if (tasks.isEmpty()) {
	            System.out.println("No tasks available.");
	            return;
	        }

	        System.out.println("\nTasks:");
	        for (int i = 0; i < tasks.size(); i++) {
	            System.out.println((i + 1) + ". " + tasks.get(i));
	        }
	    }

	    static void saveTasks() {
	        try {
	            BufferedWriter writer = new BufferedWriter(
	                    new FileWriter(FILE_NAME));

	            for (String task : tasks) {
	                writer.write(task);
	                writer.newLine();
	            }

	            writer.close();

	        } catch (IOException e) {
	            System.out.println("Error saving tasks.");
	        }
	    }

	    static void loadTasks() {
	        try {
	            BufferedReader reader = new BufferedReader(
	                    new FileReader(FILE_NAME));

	            String line;

	            while ((line = reader.readLine()) != null) {
	                tasks.add(line);
	            }

	            reader.close();

	        } catch (IOException e) {
	            System.out.println("No saved tasks found.");
	        }
	    }
	}


