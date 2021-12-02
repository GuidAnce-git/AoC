package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException
    {
        //aoc1_1();
        aoc1_2();
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
}
