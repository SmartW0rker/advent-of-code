package day02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Problem2 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int counter = 0;

        List<List<Integer>> intLists = br.lines()
                .map(line -> line.split("\\s+"))
                .map(tokens -> Arrays.stream(tokens)
                        .map(Integer::parseInt)
                        .toList())
                .toList();
        for (List<Integer> listNums: intLists) {
            if (checkIfSafe(listNums)) {
                counter++;
            }
        }

        System.out.println(counter);
    }

    private static boolean checkIfSafe(List<Integer> intNums) {
        if (isReportSafe(intNums)) {
            return true;
        }

        for (int i = 0; i < intNums.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(intNums);
            modifiedReport.remove(i);
            if (isReportSafe(modifiedReport)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isReportSafe(List<Integer> intNums) {
        boolean isIncreasing = intNums.get(0) < intNums.get(1);
        boolean isDecreasing = intNums.get(0) > intNums.get(1);

        for (int i = 1; i < intNums.size(); i++) {
            int diff = intNums.get(i) - intNums.get(i - 1);

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                return false;
            }

            if ((isIncreasing && diff <= 0) || (isDecreasing && diff >= 0)) {
                return false;
            }
        }

        return true;
    }
}
