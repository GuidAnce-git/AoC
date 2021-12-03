package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        aoc3_2();
    }

    public static void aoc3_2(){
        String[] stringArray = readInput();
        List<List<Integer>> finalIntList = new ArrayList<>();


        for (String s : stringArray) {
            String[] parts = s.split("");
            List<Integer> intList = new ArrayList<>();
            for (String s1 : parts){
                intList.add(Integer.valueOf(s1));
            }
            finalIntList.add(intList);
        }

        List<List<Integer>>  finalIntListOriginal = new ArrayList<>(finalIntList);

        System.out.println(finalIntList);

        int counter = 0;
        while(finalIntList.size() > 1){
            int finalCounter = counter;
            List<List<Integer>> finalIntList1 = finalIntList;
            finalIntList.removeIf(li -> li.get(finalCounter) != countingPosition(finalIntList1, finalCounter));
            counter++;
        }

        StringBuilder stringListOxygen = new StringBuilder();
        for (Integer i : finalIntList.get(0)){
            stringListOxygen.append(i);
        }
        System.out.println(Integer.parseInt(String.valueOf(stringListOxygen), 2));

        counter = 0;
        finalIntList = finalIntListOriginal;
        while(finalIntList.size() > 1){
            int finalCounter = counter;
            List<List<Integer>> finalIntList2 = finalIntList;
            finalIntList.removeIf(li -> li.get(finalCounter) == countingPosition(finalIntList2, finalCounter));
            counter++;
        }

        StringBuilder stringListCO2 = new StringBuilder();
        for (Integer i : finalIntList.get(0)){
            stringListCO2.append(i);
        }
        System.out.println(Integer.parseInt(String.valueOf(stringListCO2), 2));

        System.out.println(Integer.parseInt(String.valueOf(stringListOxygen), 2) * Integer.parseInt(String.valueOf(stringListCO2), 2));


    }

    public static int countingPosition(List<List<Integer>> finalIntList, int counter){
        List<Integer> templateList = new ArrayList<>();
        for (List<Integer> li : finalIntList){
            templateList.add(li.get(counter));
        }
        int one = Collections.frequency(templateList, 1);
        int zero = Collections.frequency(templateList, 0);
        int countingPosition = 0;
        if (one >= zero){
            countingPosition = 1;
        }
        return  countingPosition;
    }

    public static void aoc3_1(){
        String[] stringArray = readInput();
        List<List<Integer>> finalIntList = new ArrayList<>();

        for (String s : stringArray) {
            String[] parts = s.split("");
            List<Integer> intList = new ArrayList<>();
            for (String s1 : parts){
                intList.add(Integer.valueOf(s1));
            }
            finalIntList.add(intList);
        }

        System.out.println(finalIntList);

        List<List<Integer>> listOfCollectedInts = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            List<Integer> intList = new ArrayList<>();
            for (List<Integer> li : finalIntList) {
                intList.add(li.get(i));
            }
            listOfCollectedInts.add(intList);
        }

        StringBuilder gammaRate = new StringBuilder();
        StringBuilder epsilonRate = new StringBuilder();

        for (List<Integer> li : listOfCollectedInts){
            int one = Collections.frequency(li, 1);
            int zero = Collections.frequency(li, 0);


            if (one > zero){
                gammaRate.append("1");
                epsilonRate.append("0");
            } else {
                gammaRate.append("0");
                epsilonRate.append("1");
            }

        }
        System.out.println(Integer.parseInt(String.valueOf(gammaRate), 2) * Integer.parseInt(String.valueOf(epsilonRate), 2));
    }

    public static void aoc2_2(){
        String[] stringArray = readInput();

        long depth = 0;
        long aim = 0;
        long horizontalPosition = 0;


        for (String s : stringArray){
            String[] parts = s.split(" ");
            switch (parts[0]) {
                case "forward" -> {
                    horizontalPosition = horizontalPosition + Integer.parseInt(parts[1]);
                    depth = depth + (aim * Integer.parseInt(parts[1]));
                }
                case "down" -> aim = aim + Integer.parseInt(parts[1]);
                case "up" -> aim = aim - Integer.parseInt(parts[1]);
                default -> System.out.println("command unknown");
            }
        }

        System.out.println(horizontalPosition * depth);
    }

    public static void aoc2_1(){
        String[] stringArray = readInput();

        int forward = 0;
        int depth = 0;

        for (String s : stringArray){
            String[] parts = s.split(" ");
            switch (parts[0]) {
                case "forward" -> forward = forward + Integer.parseInt(parts[1]);
                case "down" -> depth = depth + Integer.parseInt(parts[1]);
                case "up" -> depth = depth - Integer.parseInt(parts[1]);
                default -> System.out.println("command unknown");
            }
        }

        System.out.println(forward * depth);
    }

    public static void aoc1_2() throws IOException
    {
        Path filePath = new File("C:\\Users\\q395836\\Downloads\\input.txt").toPath();
        List<String> stringList = Files.readAllLines(filePath);
        String[] stringArray = stringList.toArray(new String[]{});

        List<Integer> intList = new ArrayList<>();

        int value;

        while(stringArray.length > 2) {
            value = Integer.parseInt(stringArray[0]) + Integer.parseInt(stringArray[1]) + Integer.parseInt(stringArray[2]);
            intList.add(value);
            stringArray = Arrays.copyOfRange(stringArray, 1, stringArray.length);
        }

        int counter = 0;
        int previousValue = 0;
        for (int i : intList){
            if (previousValue != 0 && i > previousValue) {
                counter++;
            }
            previousValue = i;
        }
        System.out.println(counter);
        System.out.println(intList.get(intList.size() - 1));


    }

    public static void aoc1_1() throws IOException
    {
        Path filePath = new File("C:\\Users\\q395836\\Downloads\\input.txt").toPath();
        List<String> stringList = Files.readAllLines(filePath);
        String[] stringArray = stringList.toArray(new String[]{});

        int counter = 0;
        String previousValue = null;
        for (String s : stringArray){
            if (previousValue != null && Integer.parseInt(s) > Integer.parseInt(previousValue)) {
                counter++;
            }
            previousValue = s;
        }
        System.out.println(counter);
    }

    public static String[] readInput(){
        Path filePath = new File("C:\\temp\\input.txt").toPath();
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (stringList != null) {
            return stringList.toArray(new String[]{});
        }

        return new String[]{};
    }
}
