package com.company.aoc2022.daytwo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class DayTwoPartTwo {
    private DayTwoPartTwo() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daytwo/resources/input.txt");
        int sum = 0;

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<String> inputList = Arrays.asList(s1.split(" "));

            if (findCorrectAnswer(inputList) != null) {
                sum += result(findCorrectAnswer(inputList));
            }
            setCorrectAnswer(findCorrectAnswer(inputList), inputList);
            sum += scoreShape(inputList);
        }
        System.out.println("Day 2 - Part 2: " + sum);
    }

    private static int scoreShape(List<String> inputList){
        int sum = 0;
        switch (inputList.get(1)){
            case "A" -> sum += 1;
            case "B" -> sum += 2;
            case "C" -> sum += 3;
            default -> sum += 0;
        }
        return sum;
    }

    private static int result(RoundEndingsEnum roundEndingsEnum){
        int sum = 0;
        switch (roundEndingsEnum) {
            case WIN -> sum += 6;
            case DRAW -> sum += 3;
            case LOSE -> sum += 0;
        }
        return sum;
    }

    private static void setCorrectAnswer(RoundEndingsEnum roundEndingsEnum, List<String> inputList){
        // value:win,loose
        HashMap<String, List<String>> rulesMap = new HashMap<>();
        rulesMap.put("A", Arrays.asList("C", "B"));
        rulesMap.put("B", Arrays.asList("A", "C"));
        rulesMap.put("C", Arrays.asList("B", "A"));

        switch (roundEndingsEnum) {
            case WIN -> inputList.set(1, rulesMap.get(inputList.get(0)).get(1));
            case DRAW -> inputList.set(1, inputList.get(0));
            case LOSE -> inputList.set(1, rulesMap.get(inputList.get(0)).get(0));
        }
    }

    private static RoundEndingsEnum findCorrectAnswer(List<String> choise){
        switch (choise.get(1)) {
            case "X" -> {return RoundEndingsEnum.LOSE;}
            case "Y" -> {return RoundEndingsEnum.DRAW;}
            case "Z" -> {return RoundEndingsEnum.WIN;}
            default -> {
                return null;
            }
        }
    }
}
