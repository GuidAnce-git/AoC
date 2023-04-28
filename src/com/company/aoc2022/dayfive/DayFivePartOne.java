package com.company.aoc2022.dayfive;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.company.aoc2022.commons.ReadInput;

public class DayFivePartOne {
    private DayFivePartOne() {
    }

    public static void run() {
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/dayfive/resources/input.txt");
        Map<Integer, List<Character>> mapOfStacks = createMapOfStacks();
        StringBuilder result = new StringBuilder();

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            if (s1.startsWith("move")) {
                EnumMap<InstructionsEnum, Integer> instructionsMap = readInstruction(s1);
                doTheMagicStuff(mapOfStacks, instructionsMap);

            }
        }

        for (List<Character> charList : mapOfStacks.values()) {
            result.append(charList.get(charList.size() - 1));
        }
        
        System.out.println("Day 5 - Part 1: " + result);
    }

    private static void doTheMagicStuff(final Map<Integer, List<Character>> mapOfStacks,
            final EnumMap<InstructionsEnum, Integer> instructionsMap) {

        List<Character> characterToAddList = new ArrayList<>();
        for (int i = instructionsMap.get(InstructionsEnum.MOVE); i > 0; i-- ) {
            characterToAddList.add(mapOfStacks.get(instructionsMap.get(InstructionsEnum.FROM)).get(
                    mapOfStacks.get(instructionsMap.get(InstructionsEnum.FROM)).size() - i));
        }

        for (int i = instructionsMap.get(InstructionsEnum.MOVE); i > 0; i-- ) {
            List<Character> newFromList =
                    new ArrayList<>(List.copyOf(mapOfStacks.get(instructionsMap.get(InstructionsEnum.FROM))));
            newFromList.remove(mapOfStacks.get(instructionsMap.get(InstructionsEnum.FROM)).size() - 1);
            mapOfStacks.put(instructionsMap.get(InstructionsEnum.FROM), newFromList);
        }

        List<Character> newToList =
                new ArrayList<>(List.copyOf(mapOfStacks.get(instructionsMap.get(InstructionsEnum.TO))));
        newToList.addAll(characterToAddList);
        mapOfStacks.put(instructionsMap.get(InstructionsEnum.TO), newToList);


    }

    private static EnumMap<InstructionsEnum, Integer> readInstruction(final String s1) {
        EnumMap<InstructionsEnum, Integer> instructionsMap = new EnumMap<>(InstructionsEnum.class);
        Pattern movePattern = Pattern.compile("(?<=move )\\d+");
        Pattern fromPattern = Pattern.compile("(?<=from )\\d+");
        Pattern toPattern = Pattern.compile("(?<=to )\\d+");

        Matcher matcher = movePattern.matcher(s1);
        while(matcher.find()) {
            instructionsMap.put(InstructionsEnum.MOVE, Integer.valueOf(matcher.group()));
        }

        matcher = fromPattern.matcher(s1);
        while(matcher.find()) {
            instructionsMap.put(InstructionsEnum.FROM, Integer.valueOf(matcher.group()));
        }

        matcher = toPattern.matcher(s1);
        while(matcher.find()) {
            instructionsMap.put(InstructionsEnum.TO, Integer.valueOf(matcher.group()));
        }


        return instructionsMap;
    }

    private static Map<Integer, List<Character>> createMapOfStacks() {
        Map<Integer,List<Character>> mapOfStacks = new LinkedHashMap<>();

        /*
        mapOfStacks.put(1, List.of('Z', 'N'));
        mapOfStacks.put(2, List.of('M', 'C', 'D'));
        mapOfStacks.put(3, List.of('P'));
        */

        mapOfStacks.put(1, List.of('S', 'C', 'V', 'N'));
        mapOfStacks.put(2, List.of('Z', 'M', 'J', 'H', 'N', 'S'));
        mapOfStacks.put(3, List.of('M', 'C', 'T', 'G', 'J', 'N', 'D'));
        mapOfStacks.put(4, List.of('T', 'D', 'F', 'J', 'W', 'R', 'M'));
        mapOfStacks.put(5, List.of('P', 'F', 'H'));
        mapOfStacks.put(6, List.of('C', 'T', 'Z', 'H', 'J'));
        mapOfStacks.put(7, List.of('D', 'P', 'R', 'Q', 'F', 'S', 'L', 'Z'));
        mapOfStacks.put(8, List.of('C', 'S', 'L', 'H', 'D', 'F', 'P', 'W'));
        mapOfStacks.put(9, List.of('D', 'S', 'M', 'P', 'F', 'N', 'G', 'Z'));


        return mapOfStacks;
    }

}
