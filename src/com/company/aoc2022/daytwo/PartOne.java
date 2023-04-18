package com.company.aoc2022.daytwo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class PartOne {
    private PartOne() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daytwo/resources/input.txt");
        int sum = 0;

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<String> inputList = Arrays.asList(s1.split(" "));
            sum += result(inputList);
            sum += scoreShape(inputList);
        }
        System.out.println("Part one: " + sum);

    }

    private static int scoreShape(List<String> inputList){
        int sum = 0;
        switch (inputList.get(1)){
            case "X" -> sum += 1;
            case "Y" -> sum += 2;
            case "Z" -> sum += 3;
            default -> sum += 0;
        }
        return sum;
    }

    private static int result(List<String> inputList){
        int sum = 0;

        if ((inputList.get(0).equals("A") && inputList.get(1).equals("X")) ||
                (inputList.get(0).equals("B") && inputList.get(1).equals("Y")) ||
                (inputList.get(0).equals("C") && inputList.get(1).equals("Z"))){
            sum += 3;

        } else {
            switch (inputList.get(0)) {
                case "A" -> sum += checkAgainstA(inputList.get(1));
                case "B" -> sum += checkAgainstB(inputList.get(1));
                case "C" -> sum += checkAgainstC(inputList.get(1));
                default -> sum += 0;
            }
        }
        return sum;
    }

    private static int checkAgainstA(String response){
        if (Objects.equals(response, "Y")) {
            return 6;
        }
        return 0;
    }

    private static int checkAgainstB(String response){
        if (Objects.equals(response, "Z")) {
            return 6;
        }
        return 0;
    }

    private static int checkAgainstC(String response){
        if (Objects.equals(response, "X")) {
            return 6;
        }
        return 0;
    }
}
