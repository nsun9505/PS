package Programmers.스택큐.기능개발;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        Queue<Integer> queue = new LinkedList<>();
        int[] days = new int[100];
        for(int i=0; i<progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = remain / speeds[i];
            if(remain % speeds[i] != 0)
                day++;
            queue.offer(day);
        }

        int curDay = queue.peek();
        int size = 1;
        while(!queue.isEmpty()){
            int completeDay = queue.peek();
            if(completeDay <= curDay){
                queue.poll();
                days[curDay]++;
            } else {
                size++;
                curDay = completeDay;
            }
        }

        answer = new int[size];
        int idx = 0;
        for(int i=1; i<100; i++){
            if(days[i] > 0)
                answer[idx++] = days[i];
        }

        return answer;
    }
}
