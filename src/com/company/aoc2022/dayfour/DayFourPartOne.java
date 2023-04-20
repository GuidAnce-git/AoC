package com.company.aoc2022.dayfour;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class DayFourPartOne {
    private DayFourPartOne() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/dayfour/resources/input.txt");
        int sum = 0;

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<List<Integer>> list = splitInput(s1);
            if (checkInclusion(list)) {
               sum++;
            }
        }
        System.out.println("Day 4 - Part 1: " + sum);

    }

    private static boolean checkInclusion(final List<List<Integer>> list) {
        return ((list.get(1).get(0) >= list.get(0).get(0)) &&
                (list.get(1).get(1) <= list.get(0).get(1))) ||
                ((list.get(0).get(0) >= list.get(1).get(0)) &&
                        (list.get(0).get(1) <= list.get(1).get(1)));
    }

    private static List<List<Integer>> splitInput(final String s1) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> firstSection = new ArrayList<>();
        List<Integer> secondSection = new ArrayList<>();
        String [] sections = s1.split(",");
        String [] sectionOne = sections[0].split("-");
        firstSection.add(Integer.parseInt(sectionOne[0]));
        firstSection.add(Integer.parseInt(sectionOne[1]));
        list.add(firstSection);
        String [] sectionTwo = sections[1].split("-");
        secondSection.add(Integer.parseInt(sectionTwo[0]));
        secondSection.add(Integer.parseInt(sectionTwo[1]));
        list.add(secondSection);

        return list;
    }

}
