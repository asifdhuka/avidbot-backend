//package com.avidbots;
//
//import java.io.BufferedInputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Scanner;
//
//public class AvidbotsRobot {
//    char[][] matrix;
//    public static void main(String[] args) {
//        Scanner stdin = new Scanner(new BufferedInputStream(System.in));
//        AvidbotsRobot avidbotsRobot = new AvidbotsRobot();
//        avidbotsRobot.start(stdin);
//    }
//
//    /*
//     Separating start method so tests can be performed
//     */
//    public void start(Scanner stdin) {
//        List<String> lines = new ArrayList<>();
//        while (stdin.hasNext()) {
//            String line = stdin.nextLine();
//            lines.add(line);
//        }
//        int nr = lines.size();
//        int nc = lines.get(0).length();
//        int whiteSpaces = 0;
//        int walls = 0;
//        matrix = new char[nr][nc];
//        for (int i = 0; i < nr; i++) {
//            char[] chars = lines.get(i).toCharArray();
//            for (int j = 0; j < nc; j++) {
//                if (chars[j] == '#') {
//                    walls++;
//                } else if (chars[j] == ' ') {
//                    whiteSpaces++;
//                }
//                matrix[i][j] = chars[j];
//            }
//        }
//        System.out.println(matrix);
//    }
//}
