package day04;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem4 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb=new StringBuilder();

        int rows=(int)br.lines().map(s->sb.append(s).append("\n")).count();

        char[][] matrixArray=new char[rows][];
        String[] lines=sb.toString().split("\n");

        for (int i=0;i<lines.length;i++){
            for (int j=0;j<lines[i].length();j++){
                matrixArray[i]=lines[i].toCharArray();
            }
        }

        int count = 0;

        int[][] directions={
                {0,1,0,2,0,3}, // Horizontal search (Left to Right)
                {0,-1,0,-2,0,-3}, // Horizontal search (Right to Left)
                {1,0,2,0,3,0}, // Vertical search (Top to Bottom)
                {-1,0,-2,0,-3,0}, // Vertical search (Bottom to Top)
                {1,1,2,2,3,3}, // Diagonal search (Top-left to Bottom-right)
                {1,-1,2,-2,3,-3}, // Diagonal search (Top-right to Bottom-left)
                {-1,1,-2,2,-3,3}, // Diagonal search (Bottom-left to Top-right)
                {-1,-1,-2,-2,-3,-3} // Diagonal search (Bottom-right to Top-left)
        };

        count = xmasSearch(matrixArray, directions);
        System.out.println(count);

        partTwo(matrixArray);
    }

    private static int xmasSearch(char[][] matrixArray, int[][] directions) {

        int count=0;

        // Horizontal search (Left to Right)
        count += xmasCount(matrixArray, directions[0]);
        // Horizontal search (Right to Left)
        count += xmasCount(matrixArray, directions[1]);
        // Vertical search (Top to Bottom)
        count += xmasCount(matrixArray, directions[2]);
        // Vertical search (Bottom to Top)
        count += xmasCount(matrixArray, directions[3]);
        // Diagonal search (Top-right to Bottom-right)
        count += xmasCount(matrixArray, directions[4]);
        // Diagonal search (Top-right to Bottom-left)
        count += xmasCount(matrixArray, directions[5]);
        // Diagonal search (Bottom-left to Top-right)
        count += xmasCount(matrixArray, directions[6]);
        // Diagonal search (Bottom-right to Top-left)
        count += xmasCount(matrixArray, directions[7]);

        return count;
    }

    private static int xmasCount(char[][] matrixArray,int[] directions) {
        int count=0;
        for (int i = 0; i<matrixArray.length; i++) {
            for (int j = 0; j < matrixArray[i].length; j++) {
                if (isValidPosition(i, j, directions, matrixArray.length, matrixArray[i].length) && matrixArray[i][j] == 'X'
                        && matrixArray[i+directions[0]] [j+directions[1]] == 'M'
                        && matrixArray[i+directions[2]] [j+directions[3]] == 'A'
                        && matrixArray[i+directions[4]] [j+directions[5]] == 'S') {
                    count++;
                }
            }
        }
        return count;
    }
    private static boolean isValidPosition(int i, int j, int[] direction, int rows, int cols) {
        return i + direction[4] < rows && i + direction[4] >= 0 && j + direction[5] < cols && j + direction[5] >= 0;
    }

    private static void partTwo(char[][] matrix) {
        int counter = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows - 2; i++) {
            for (int j = 0; j < cols - 2; j++) {
                if (isPatternMatch(matrix, i, j)) {
                    counter++;
                }
            }
        }
        System.out.println(counter);
    }

    private static boolean isPatternMatch(char[][] matrix, int i, int j) {
        // Collect the characters in the 3x3 grid
        char c1 = matrix[i][j];
        char c2 = matrix[i][j + 2];
        char c3 = matrix[i + 1][j+1];
        char c4 = matrix[i + 2][j];
        char c5 = matrix[i + 2][j + 2];


        if (c1 == 'M' && c2 == 'S' && c3 == 'A' && c4 == 'M' && c5 == 'S') {
            return true;
        }

        if (c1 == 'M' && c2 == 'M' && c3 == 'A' && c4 == 'S' && c5 == 'S') {
            return true;
        }

        if (c1 == 'S' && c2 == 'M' && c3 == 'A' && c4 == 'S' && c5 == 'M') {
            return true;
        }

        if (c1 == 'S' && c2 == 'S' && c3 == 'A' && c4 == 'M' && c5 == 'M') {
            return true;
        }

        return false;
    }
}
