package main.API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class MainPage extends Thread {

    public MainPage() {
    }

    public static void mainPage() {
        welcome();
        new MainPage().start();
    }

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            printOption();
            try {
                String[] strInput = reader.readLine().split(" ");
                if (strInput.length <= 0) continue;
                switch (strInput[0].toLowerCase(Locale.ROOT)) {
                    case "1" -> {
                        String[] str = new String[6];
                        String[] prompts = {"id", "name", "age", "email", "course"};
                        for (int i = 0; i < prompts.length; i++) {
                            System.out.print("Enter the worker " + prompts[i] + ": ");
                            str[i] = reader.readLine();
                        }
                        if (!WorkerManager.validate(str[0], str[2], str[3])) break;
                        WorkerManager.saveWorker(str[0], str[1], str[2], str[3], str[4]);
                    }
                    case "2" -> {
                        System.out.print("\nEnter the worker ID to search: ");
                        String index = reader.readLine();
                        String[] response = WorkerManager.searchWorker(index).toArray(new String[0]);
                        if (response.length > 0)
                            System.out.printf("\u001B[0;36m----------------------------------\nworker ID: %s\nworker NAME: %s\nworker AGE: %s\nworker EMAIL: %s\nworker COURSE: %s\n----------------------------------\u001B[0m%n", response);
                        else
                            System.err.printf("worker with worker Id: %s was not found!", index);
                    }
                    case "3" -> {
                        System.out.print("\nEnter the worker ID to delete: ");
                        String index = reader.readLine();
                        ArrayList<String> response = (ArrayList<String>) WorkerManager.searchWorker(index);
                        if (response.size() > 0) {
                            System.out.println(WorkerManager.searchWorker(index).toArray(new String[0])[2]);
                            System.out.printf("Are you sure you want to delete worker with ID: %s ? Press Y to continue or any other input to discard changes", index);
                            if (reader.readLine().equalsIgnoreCase("y")) {
                                WorkerManager.deleteWorker(response);
                                System.out.println("worker was deleted");
                            } else
                                System.err.println("procedure canceled");
                        } else
                            System.err.printf("worker with worker Id: %s was not found!", index);
                    }
                    case "4" -> WorkerManager.workReport();
                    case "exit", "5" -> WorkerManager.exitApplication();
                    default -> System.err.println("Unknown Input");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void welcome() {
        System.out.println("\u001B[32mworker MANAGEMENT APPLICATION\n************************************************\033[0m");
    }

    public static void printOption() {
        System.out.println("""
                \nPlease select one of the following menu items:	\u001B[34m
                (1) Capture a new worker
                (2) Search for a worker
                (3) Delete a worker
                (4) Print worker report
                (5) Exit Application""");
    }
}
