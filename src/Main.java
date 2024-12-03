import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static void readLinesAndAddElements(List<Integer> firstList,List<Integer> secondList,HashMap<Integer,Integer> map) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                String[] nums = line.split("\\s+");
                Integer firstNum = Integer.parseInt(nums[0]);
                Integer secondNum = Integer.parseInt(nums[1]);
                firstList.add(firstNum);
                secondList.add(secondNum);
                map.put(secondNum,map.getOrDefault(secondNum,0)+1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //complexity of O(n)
    private static void firstTaskPrint(List<Integer> firstList, List<Integer> secondList) {
        int sum=0;
        for (int i = 0; i< firstList.size(); i++){
            sum+=Math.abs(firstList.get(i)- secondList.get(i));
        }
        System.out.println(sum);
    }
    // complexity of O(n)
    private static void secondTaskPrint(List<Integer> firstList, Map<Integer,Integer>map) {
        int sum=0;
        for (Integer num:firstList){
            sum+=num*map.getOrDefault(num,0);
        }
        System.out.println(sum);
    }
    
    public static void main(String[] args) {
        List<Integer> firstList=new ArrayList<>();
        List<Integer> secondList=new ArrayList<>();
        HashMap<Integer,Integer> map=new HashMap<>();
           
           readLinesAndAddElements(firstList,secondList,map);

 
           firstList.sort(Comparator.naturalOrder());
           secondList.sort(Comparator.naturalOrder());

           firstTaskPrint(firstList,secondList);
           secondTaskPrint(firstList,map);
    }

}