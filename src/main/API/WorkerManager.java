package main.API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class WorkerManager {

    private static List<List<String>> worker = new ArrayList<>();

    public static void saveWorker(String id, String name, String age, String email, String course) {
        worker.add(List.of(id, name, age, email, course));
        System.out.println("\033[0;36mAdded worker to memory\033[0m");
    }

    public static List<String> searchWorker(String target) {
        return worker.stream()
                .filter(workerData -> workerData.contains(target))
                .findFirst()
                .orElse(new ArrayList<>());
    }

    public static void deleteWorker(List<String> selWorker) {
        Iterator<List<String>> iterator = worker.iterator();
        while (iterator.hasNext()) {
            List<String> workerData = iterator.next();
            if (workerData.equals(selWorker)) {
                iterator.remove();
                return;
            }
        }
    }

    public static void workReport() {
        int count = 1;
        for (List<String> innerList : worker) {
            System.out.printf("\033[0;36m\n----------------------------------------\nWORKER %d:\n----------------------------------------\n" + "WORKER ID: %s\n" + "WORKER NAME: %s\n" + "WORKER AGE: %s\n" + "WORKER EMAIL: %s\n" + "WORKER QUALIFICATION: %s\n\033[0m", count, innerList.toArray());
            count++;
        }
    }

    public static void exitApplication() {
        System.exit(420);
    }

    public static boolean isValidAge(String strAge) {
        try {
            int age = Integer.parseInt(strAge);
            return age >= 16;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches();
    }

    public static boolean isValidId(String iD) {
        try {
            int age = Integer.parseInt(iD);
            return age > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validate(String id, String age, String email) {
        if (!isValidId(id)) System.err.println("Invalid ID. Please check credentials and retry.");
        else if (!isValidAge(age)) System.err.println("Invalid Age. Please check credentials and retry.");
        else if (!isValidEmail(email)) System.err.println("Invalid Email. Please check credentials and retry.");
        else return true;
        return false;
    }
}
