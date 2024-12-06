package day06;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem6 {
     enum Direction{
         UP,
         RIGHT,
         LEFT,
         DOWN;
    }

    private static Direction checkGuardPosition(char c) {
         if (c=='^')
             return Direction.UP;
         else if (c=='>')
             return Direction.RIGHT;
         else if (c=='<')
             return Direction.LEFT;
         else
             return Direction.DOWN;
    }

    private static int guardTotalPositions(char[][] matrixArray, int rowPosition, int columnPosition,Direction direction) {
         int count=1;
        boolean[][] visitedMatrix=new boolean[matrixArray.length][matrixArray[0].length];
        visitedMatrix[rowPosition][columnPosition]=true;
         while (true){
             if (rowPosition==matrixArray.length-1 || rowPosition==0 || columnPosition==matrixArray[0].length-1 || columnPosition==0)
                 break;
             if (direction==Direction.UP){
                 if (matrixArray[rowPosition-1][columnPosition]!='#'){
                     if (!visitedMatrix[rowPosition-1][columnPosition]) {
                         count++;
                         visitedMatrix[rowPosition-1][columnPosition]=true;
                     }
                     rowPosition--;
                 }
                 else
                     direction=Direction.RIGHT;
             }
             else if(direction==Direction.RIGHT){
                 if (matrixArray[rowPosition][columnPosition+1]!='#'){
                     if (!visitedMatrix[rowPosition][columnPosition+1]) {
                         count++;
                         visitedMatrix[rowPosition][columnPosition+1]=true;
                     }
                     columnPosition++;
                 }
                 else
                     direction=Direction.DOWN;
             }
             else if(direction==Direction.DOWN){
                 if (matrixArray[rowPosition+1][columnPosition]!='#'){
                     if (!visitedMatrix[rowPosition+1][columnPosition]) {
                         count++;
                         visitedMatrix[rowPosition+1][columnPosition]=true;
                     }
                     rowPosition++;
                 }
                 else
                     direction=Direction.LEFT;
             }
             else {
                 if (matrixArray[rowPosition][columnPosition-1]!='#'){
                     if (!visitedMatrix[rowPosition][columnPosition-1]) {
                         count++;
                         visitedMatrix[rowPosition][columnPosition-1]=true;
                     }
                     columnPosition--;
                 }
                 else
                     direction=Direction.UP;
             }
         }
         return count;
    }

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb=new StringBuilder();
        Direction direction=Direction.UP;
        int columnPosition=0;
        int rowPosition=0;
        int total;

        int rows=(int)br.lines().map(s->sb.append(s).append("\n")).count();

        char[][] matrixArray=new char[rows][];
        String[] lines=sb.toString().split("\n");


        for (int i=0;i<lines.length;i++){
            matrixArray[i] = new char[lines[i].length()];
            for (int j=0;j<lines[i].length();j++){
                char c=matrixArray[i][j]=lines[i].charAt(j);
                if (c=='^' || c=='>' || c=='<' || c=='v') {
                    rowPosition=i;
                    columnPosition=j;
                    direction = checkGuardPosition(c);
                }

            }
        }
        total= guardTotalPositions(matrixArray,rowPosition,columnPosition,direction);
        System.out.println(total);
    }

}
