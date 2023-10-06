package HW1_4;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.*;

public class Equation {
    public static String getSolution(String str) {
        // Введите свое решение ниже
        String expr;
        try (FileReader fr = new FileReader(str); BufferedReader br = new BufferedReader(fr)) {
            expr = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Given the equation: " + expr);
        ArrayList<String> exprParsed = parseExpression(expr);
        int first_term_known = getKnownNum(exprParsed.get(0));
        int second_term_known = getKnownNum(exprParsed.get(1));
        int unknown_el_sum = Integer.parseInt(exprParsed.get(2)) - first_term_known - second_term_known;
        int[] unknown_el_arr = getUnknownNumbers(getUnknownPosition(exprParsed.get(0)),
                getUnknownPosition(exprParsed.get(1)),
                exprParsed.get(0).length(),
                exprParsed.get(1).length(),
                unknown_el_sum);
        if (unknown_el_arr[0] == 0 && unknown_el_arr[1] == 0){
            return "No solution";
        }
        int first_term = first_term_known + unknown_el_arr[0];
        int second_term = second_term_known + unknown_el_arr[1];
        return "Result: " + first_term + " + " + second_term + " = " + exprParsed.get(2);
    }

    public static ArrayList<String> parseExpression(String expr){
        String regex = "(\\d|\\?)+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expr);
        ArrayList<String> res = new ArrayList<>();
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            res.add(expr.substring(start, end));
        }
        return res;
    }

    public static int getKnownNum(String element){
        int len = element.length();
        int res = 0;
        for (int i = 0; i < len; i++){
            char currentChar = element.charAt(i);
            if (currentChar != '?') {
                res += (int) (Character.getNumericValue(element.charAt(i)) * Math.pow(10, len - i - 1));
            }
        }
        return res;
    }

    public static int getUnknownPosition(String element){
        return element.indexOf('?');
    }

    public static int[] getUnknownNumbers(int unknownPositionFirst, int unknownPositionSecond, int lenFirst, int lenSecond, int unknownSum){
        int exponentFirst = (int) Math.pow(10, lenFirst - unknownPositionFirst - 1);
        int exponentSecond = (int) Math.pow(10, lenSecond - unknownPositionSecond - 1);
        for (int i = exponentFirst; i < exponentFirst * 10; i += exponentFirst){
            for (int j = exponentSecond; j < exponentSecond * 10; j += exponentSecond){
                if (i + j == unknownSum){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
}
