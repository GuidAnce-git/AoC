package com.company.aoc2022.daytwo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.company.aoc2022.commons.ReadInput;

public class PartTwo {
    private PartTwo() {
    }

    public static void run(){
        Scanner input = ReadInput.readInput("src/com/company/aoc2022/daytwo/resources/input_test.txt");
        int sum = 0;

        while (input.hasNextLine()) {
            String s1 = input.nextLine();
            List<String> inputList = Arrays.asList(s1.split(" "));
            
            setCorrectAnswer(findCorrectAnswer(inputList), inputList);
            //find right answer
            // X=loose
            // Y=draw
            // Z=win



            sum += result(s1);
            sum += scoreShape(s1);
        }
        System.out.println("Part two: " + sum);
    }

    private static void setCorrectAnswer(RoundEndingsEnum roundEndingsEnum, List<String> inputList){

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
