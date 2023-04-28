package com.company.aoc2022.daysix;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.company.aoc2022.commons.ReadInput;

public class DaySixPartOne {
    private DaySixPartOne() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daysix/resources/input.txt");

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<Character> characterList = new ArrayList<>();

            for (int i = 0; i < s1.length() ; i++) {
                if (characterList.size() == 4){
                    characterList.remove(0);
                }
                characterList.add(s1.charAt(i));
                if (isSequenceMarker(characterList)){
                    System.out.println("Day 6 - Part 1: " + (i + 1));
                    break;
                }
            }


        }
    }

    private static boolean isSequenceMarker(final List<Character> characterList){

        Set<Character> characterSet = new HashSet<>(characterList);

        return characterList.size() >= 4 && characterSet.size() == 4;
    }

}
