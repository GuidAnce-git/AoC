package com.company.aoc2022.commons;

import java.io.File;
import java.util.Scanner;

public class ReadInput {
    private ReadInput() {
    }

    public static Scanner readInput(String pathName){
        Scanner reader = null;
        try {
            reader = new Scanner(new File(pathName));
        } catch (Exception e) {
            System.out.println("file not found");
        }

        return reader;
    }
}
