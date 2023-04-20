package com.company.aoc2022.daythree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class DayThreePartTwo {
    private DayThreePartTwo() {
    }

    public static void run(){

        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daythree/resources/input.txt");
        int sum = 0;
        int cycle = 0;
        List<String> rucksackList = new ArrayList<>();

        while (input.hasNextLine()) {
            cycle++;
            String s1 = input.nextLine();

            rucksackList.add(s1);
            if (cycle >= 3) {
                sum += calcValue(findSingleCharInAllRucksacks(rucksackList));
                cycle = 0;
                rucksackList.clear();
            }
        }

        System.out.println("Day 3 - Part 2: " + sum);

    }

    private static int calcValue(final char doubledChar) {

        if (Character.isUpperCase(doubledChar)){
            return doubledChar - 38;
        } else {
            return doubledChar - 96;
        }
    }

    private static char findSingleCharInAllRucksacks(final List<String> rucksackList) {
        char result = 0;
        char[] charArrayOne = rucksackList.get(0).toCharArray();
        char[] charArrayTwo = rucksackList.get(1).toCharArray();
        char[] charArrayThree = rucksackList.get(2).toCharArray();

        List<Character> foundDoublesList = new ArrayList<>();

        for (char firstArrayChar : charArrayOne) {
            for (char secondArrayChar : charArrayTwo) {
                if (firstArrayChar == secondArrayChar) {
                    foundDoublesList.add(firstArrayChar);
                }
            }
        }

        for (char foundChars : foundDoublesList) {
            for (char ThirdArrayChar : charArrayThree) {
                if (foundChars == ThirdArrayChar) {
                    result = foundChars;
                }
            }
        }
        return result;
    }

}
