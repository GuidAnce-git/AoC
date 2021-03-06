package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        aoc4_3();
    }




    public static void aoc4_3(){
        int[] nums;
        int[][][] cards;

        Scanner reader = null;
        try {
            reader = new Scanner(new File("C:\\temp\\input.txt"));
        } catch (Exception e) {
            System.out.println("file not found");
        }

        nums = Arrays.stream(reader.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        reader.nextLine(); // eat up blank line

        int row = 0, col = 0, card = 0;
        cards = new int[100][5][5];
        while (reader.hasNext()) {
            cards[card][row][col] = reader.nextInt();
            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
            if (row == 5) {
                row = 0;
                card++;
            }

        }

        reader.close();

        boolean[] hasCardWon = new boolean[100];
        int numOfCardsThatHaveWon = 0;

        int losingCard = -1;
        int numCalled = -1;
        int i = 0;
        boolean done = false;
        while (!done) {
            numCalled = nums[i];
            // this loop will work just like part1, but we will move past any card that has
            // already won.
            for (card = 0; card < cards.length; card++) {
                if (!hasCardWon[card]) {
                    for (row = 0; row < 5; row++) {
                        for (col = 0; col < 5; col++) {
                            if (cards[card][row][col] == numCalled) {
                                cards[card][row][col] = -1;
                                if (cards[card][0][col] + cards[card][1][col] + cards[card][2][col]
                                        + cards[card][3][col] + cards[card][4][col] == -5) {
                                    hasCardWon[card] = true;
                                    numOfCardsThatHaveWon++;
                                } else if (cards[card][row][0] + cards[card][row][1] + cards[card][row][2]
                                        + cards[card][row][3] + cards[card][row][4] == -5) {
                                    hasCardWon[card] = true;
                                    numOfCardsThatHaveWon++;
                                }
                            }
                            // If 100 unique cards have won, we are done and the current card is our
                            // last winner.
                            if (numOfCardsThatHaveWon == 100) {
                                losingCard = card;
                                done = true;
                            }
                        }
                    }
                }
            }

            i++;
        }
        int answer = 0;
        for (row = 0; row < 5; row++) {
            for (col = 0; col < 5; col++) {
                if (cards[losingCard][row][col] > 0) {
                    answer += cards[losingCard][row][col];
                }
            }
        }
        answer *= numCalled;
        System.out.println(answer);
    }


    public static void aoc4_2(){
        List<List<String>> bingoInput = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\temp\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                bingoInput.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> randomNumbers = new ArrayList<>(bingoInput.get(0));
        bingoInput.remove(0);
        bingoInput.removeIf(l -> l.get(0).isEmpty());
        System.out.println(randomNumbers);
        List<List<Integer>> bingoRowsAsList = new ArrayList<>();
        for (List<String> bingoRow : bingoInput) {
            List<Integer> list = new ArrayList<>();
            Scanner scanner = new Scanner(bingoRow.get(0));
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            bingoRowsAsList.add(list);
        }
        System.out.println(bingoRowsAsList);

        List<List<List<Integer>>> mainBingoBoard = new ArrayList<>();
        List<List<Integer>> bingoBoard = new ArrayList<>();
        List<Integer> completedBoards = new ArrayList<>();

        for (List<Integer> values : bingoRowsAsList){
            bingoBoard.add(values);
            if (bingoBoard.size() == 5) {
                mainBingoBoard.add(List.copyOf(bingoBoard));
                bingoBoard.clear();
            }
        }
        System.out.println(mainBingoBoard);
        List<Integer> boardCounterSet = new ArrayList<>();
        String lastRandomNumber = "";

        mainLoop:
        for (String s : randomNumbers){
            for (List<List<Integer>> mainBingoBoardValue : mainBingoBoard){
                for (List<Integer> bingoBoardValue : mainBingoBoardValue){
                    if (bingoBoardValue.contains(Integer.valueOf(s))) {
                        bingoBoardValue.set(bingoBoardValue.indexOf(Integer.valueOf(s)), 999);
                    }
                }
            }
            int boardCounter = 0;
            for (List<List<Integer>> bingoBoardValue : mainBingoBoard){
                boardCounter++;
                for (int i = 0; i < 5; i++) {
                    List<Integer> columns = new ArrayList<>();
                    for (List<Integer> bingoRow : bingoBoardValue){
                        columns.add(bingoRow.get(i));
                    }
                    if (Collections.frequency(columns, 999) == 5) {
                        System.out.println("BINGO!!! With: " + s + " at Board: " + boardCounter);
                        if(!boardCounterSet.contains(boardCounter)){
                            boardCounterSet.add(boardCounter);
                        }
                        if (boardCounterSet.size() == mainBingoBoard.size()){
                            lastRandomNumber = s;
                            break mainLoop;
                        }
                    }
                }

                for (List<Integer> bingoRow : bingoBoardValue){
                    if (Collections.frequency(bingoRow, 999) == 5) {
                        System.out.println("BINGO!!! With: " + s + " at Board: " + boardCounter);
                        if(!boardCounterSet.contains(boardCounter)){
                            boardCounterSet.add(boardCounter);
                            if (boardCounterSet.size() == mainBingoBoard.size()){
                                lastRandomNumber = s;
                                break mainLoop;
                            }
                        }
                    }
                }
            }
        }

        System.out.println(boardCounterSet);
        List<Integer> listOfUnmarkedNumbers = new ArrayList<>();

        for (List<Integer> st : mainBingoBoard.get(boardCounterSet.get(boardCounterSet.size() - 1))){
            System.out.println(st);
            for (Integer valueI : st) {
                if (valueI != 999){
                    listOfUnmarkedNumbers.add(valueI);
                }
            }
        }

        System.out.println(listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());
        System.out.println(lastRandomNumber);
        System.out.println(Integer.parseInt(lastRandomNumber) * listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());

        // 9366 - to high
        // 9000 - to high
    }

    public static void aoc4_1(){
        List<List<String>> bingoInput = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\temp\\input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                bingoInput.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> randomNumbers = new ArrayList<>(bingoInput.get(0));
        bingoInput.remove(0);
        bingoInput.removeIf(l -> l.get(0).isEmpty());
        System.out.println(randomNumbers);
        List<List<Integer>> bingoRowsAsList = new ArrayList<>();
        for (List<String> bingoRow : bingoInput) {
            List<Integer> list = new ArrayList<>();
            Scanner scanner = new Scanner(bingoRow.get(0));
            while (scanner.hasNextInt()) {
                list.add(scanner.nextInt());
            }
            bingoRowsAsList.add(list);
        }
        System.out.println(bingoRowsAsList);

        List<List<List<Integer>>> mainBingoBoard = new ArrayList<>();
        List<List<Integer>> bingoBoard = new ArrayList<>();

        for (List<Integer> values : bingoRowsAsList){
            bingoBoard.add(values);
            if (bingoBoard.size() == 5) {
                mainBingoBoard.add(List.copyOf(bingoBoard));
                bingoBoard.clear();
            }
        }
        System.out.println(mainBingoBoard);

        mainLoop:
        for (String s : randomNumbers){
            for (List<List<Integer>> mainBingoBoardValue : mainBingoBoard){
                for (List<Integer> bingoBoardValue : mainBingoBoardValue){
                    if (bingoBoardValue.contains(Integer.valueOf(s))) {
                        bingoBoardValue.set(bingoBoardValue.indexOf(Integer.valueOf(s)), 999);
                    }
                }
            }

            for (List<List<Integer>> bingoBoardValue : mainBingoBoard){
                for (int i = 0; i < 5; i++) {
                    List<Integer> columns = new ArrayList<>();
                    for (List<Integer> bingoRow : bingoBoardValue){
                        columns.add(bingoRow.get(i));
                    }
                    if (Collections.frequency(columns, 999) == 5) {
                        System.out.println("BINGO!!! With: " + s);
                        List<Integer> listOfUnmarkedNumbers = new ArrayList<>();
                        for (List<Integer> st : bingoBoardValue){
                            for (Integer valueI : st) {
                                if (valueI != 999){
                                    listOfUnmarkedNumbers.add(valueI);
                                }
                            }
                        }
                        System.out.println(listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());
                        System.out.println(Integer.parseInt(s) * listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());
                        break mainLoop;
                    }
                }

                for (List<Integer> bingoRow : bingoBoardValue){
                    if (Collections.frequency(bingoRow, 999) == 5) {
                        System.out.println("BINGO!!! With: " + s);
                        List<Integer> listOfUnmarkedNumbers = new ArrayList<>();
                        for (List<Integer> st : bingoBoardValue){
                            for (Integer valueI : st) {
                                if (valueI != 999){
                                    listOfUnmarkedNumbers.add(valueI);
                                }
                            }
                        }
                        System.out.println(listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());
                        System.out.println(Integer.parseInt(s) * listOfUnmarkedNumbers.stream().mapToInt(Integer::intValue).sum());
                        break mainLoop;
                    }
                    }
                }
            }


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
