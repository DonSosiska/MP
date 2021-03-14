package ua.kpi.comsys.IO8123;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Main {
    public static int randomValue (int maxValue) {
        Random random = new Random();
        int i = random.nextInt(6) + 1;
        switch(i) {
            case 1:
                return (int)Math.round((maxValue) * 0.7);
            case 2:
                return (int)Math.round((maxValue) * 0.9);
            case 3, 4, 5:
                return maxValue;
            default:
                return 0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, String> studentsGroups = new HashMap<>();
        String studentsStr = "Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82; Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83; Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія - ІВ-82; Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83; Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82";
        String[] students = studentsStr.split("; ");
        int groupsCounter = 0;
        String student;
        String group;
        for (int i = 0; i < students.length; i++){
            student = students[i].split(" - ")[0];
            group = students[i].split(" - ")[1];
            if (!studentsGroups.containsKey(group)){
                studentsGroups.put(group, student);
                groupsCounter++;
            } else {
                studentsGroups.put(group, studentsGroups.get(group) + "; " + student);
            }
        }
        String[] groups = studentsGroups.keySet().toArray(new String[0]);
        for (int i = 0; i < groupsCounter; i++){
            students = studentsGroups.get(groups[i]).split("; ");
            Arrays.sort(students);
            String sortedStudents = "";
            for (int j = 0; j < students.length; j++){
                sortedStudents += students[j] + "; ";
            }
            studentsGroups.put(groups[i], sortedStudents);
        }
        System.out.println("Завдання 1");
        System.out.println(studentsGroups);
        System.out.println("");

        int[] points = {12, 12, 12, 12, 12, 12, 12, 16};
        HashMap<String, HashMap<String, int[]>> groupPoints = new HashMap<>();
        for (int i = 0; i < groupsCounter; i++){
            students = studentsGroups.get(groups[i]).split("; ");
            HashMap<String, int[]> studentPoints = new HashMap<>();
            for (int studentCount = 0; studentCount < students.length; studentCount++){
                int currentPoints[] = new int[8];
                for (int j = 0; j < 8; j++){
                    currentPoints[j] = randomValue(points[j]);
                }
                studentPoints.put(students[studentCount], currentPoints);
            }
            groupPoints.put(groups[i], studentPoints);
        }
        for (String key : groupPoints.keySet()) {
            System.out.println(key);
            for (String key1 : groupPoints.get(key).keySet()) {
                System.out.println("    " + key1);
                System.out.print("        ");
                for (int i = 0; i < 8; i++){
                    System.out.print(groupPoints.get(key).get(key1)[i] + " ");
                }
                System.out.println();
            }
        }
        System.out.println("Завдання 2");
        System.out.println("");

        HashMap<String, HashMap<String, Integer>> sumPoints = new HashMap<>();
        for (int i = 0; i < groupsCounter; i++){
            students = studentsGroups.get(groups[i]).split("; ");
            HashMap<String, Integer> studentPoints = new HashMap<>();
            for (int studentCount = 0; studentCount < students.length; studentCount++){
                int sum = 0;
                for (int q = 0; q < 8; q++){
                    sum += groupPoints.get(groups[i]).get(students[studentCount])[q];
                }
                studentPoints.put(students[studentCount], sum);
            }
            sumPoints.put(groups[i], studentPoints);
        }

        for (String key : sumPoints.keySet()) {
            System.out.println(key);
            for (String key1 : sumPoints.get(key).keySet()) {
                System.out.println("    " + key1 + "        " + sumPoints.get(key).get(key1));
            }
        }
        System.out.println("Завдання 3");
        System.out.println("");

        HashMap<String, Float> groupAvg = new HashMap<>();
        for (String key : sumPoints.keySet()) {
            float sum = 0;
            int counter = 0;
            for (String key1 : sumPoints.get(key).keySet()) {
                sum += sumPoints.get(key).get(key1);
                counter++;
            }
            groupAvg.put(key, sum/counter);
        }
        System.out.println(groupAvg);
        System.out.println("Завдання 4");
        System.out.println("");

        HashMap<String, String[]> passedPerGroup = new HashMap<>();
        for (String key : sumPoints.keySet()) {
            String str = "";
            for (String key1 : sumPoints.get(key).keySet()) {
                if (sumPoints.get(key).get(key1) >= 60){
                    str += key1 + "/";
                }
            }
            passedPerGroup.put(key, str.split("/"));
        }
        for (String key : passedPerGroup.keySet()) {
            System.out.println(key);
            for (String key1 : passedPerGroup.get(key)) {
                System.out.println("    " + key1);
            }
        }
        System.out.println("Завдання 5");
        System.out.println("");
    }
}
