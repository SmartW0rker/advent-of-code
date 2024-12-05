package day05;

import java.util.*;

public class Problem5 {
    public static void main(String[] args) {
        HashMap<Integer, List<Integer>> beforeMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        readPattern(scanner, beforeMap);

        readSequenceAndCalculate(scanner, beforeMap);
    }

    private static void readSequenceAndCalculate(Scanner scanner, HashMap<Integer, List<Integer>> beforeMap) {
        int counter = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Integer[] nums = Arrays.stream(line.split(","))
                    .map(Integer::parseInt).toArray(Integer[]::new);
            boolean isValid=true;
            for (int i = 0; i < nums.length; i++) {
                int tempI=i;
                List<Integer> numsBefore = beforeMap.getOrDefault(nums[i], new ArrayList<>());
                for (int j = tempI + 1; j < nums.length; j++) {
                    if (numsBefore.contains(nums[j])) {
                        int temp=nums[tempI];
                        nums[tempI]=nums[j];
                        nums[j]=temp;
                        tempI=j;
                        isValid=false;
                    }
                }
            }

            if (!isValid) {
                System.out.println(Arrays.toString(nums));
                counter += nums[nums.length / 2];
            }
        }
        System.out.println(counter);
    }

    private static void readPattern(Scanner scanner, HashMap<Integer, List<Integer>> beforeMap) {
        while (true) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String[] nums = line.split("\\|");
            Integer firstNum = Integer.parseInt(nums[0].trim());
            Integer secondNum = Integer.parseInt(nums[1].trim());

            beforeMap.computeIfAbsent(secondNum, k -> new ArrayList<>()).add(firstNum);
        }
    }
}