package com.company.aoc2022.daythree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class DayThreePartOne {
    private DayThreePartOne() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daythree/resources/input_test.txt");
        int sum = 0;
        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<char[]> list = splitInput(s1);
            char doubledChar = findDoubledChar(list);
            sum += calcValue(doubledChar);
        }

        System.out.println("Day 3 - Part 1: " + sum);
    }

    private static int calcValue(final char doubledChar) {

        if (Character.isUpperCase(doubledChar)){
            return doubledChar - 38;
        } else {
            return doubledChar - 96;
        }
    }

    private static char findDoubledChar(List<char[]> charList){
        char result = 0;

        for (char singleCharFirstList : charList.get(0)) {
            for (char singleCharSecondList : charList.get(1)) {
                if (singleCharFirstList == singleCharSecondList) {
                    return singleCharFirstList;
                }
            }
        }

        return result;
    }

    private static List<char[]> splitInput(String input){

        List<char[]> charList = new ArrayList<>();
        String firstPart = input.substring(0, ((input.length() / 2)));
        charList.add(firstPart.toCharArray());
        String secondPart = input.substring(input.length() /2);
        charList.add(secondPart.toCharArray());

        return charList;
    }

}
