package Backjoon.BOJ14889;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        Set<Integer> set = new TreeSet<>();
        int sum = 0;
        for(int i=0; i<food_times.length; i++){
            set.add(food_times[i]);
            sum += food_times[i];
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(set);



        return answer;
    }
}
