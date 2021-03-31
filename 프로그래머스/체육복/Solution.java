package Programmers.체육복;

public class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i] == reserve[j]){
                    lost[i] = -1;
                    reserve[j] = -1;
                    answer++;
                    break;
                }
            }
        }

        answer = n - (lost.length - answer);
        for(int i=0; i<lost.length; i++){
            if(lost[i] == -1)
                continue;


            for(int j=0; j<reserve.length; j++){
                if(reserve[j] == -1)
                    continue;

                if(lost[i] + 1 == reserve[j] || lost[i] - 1 == reserve[j]){
                    reserve[j] = -1;
                    answer++;
                }
            }
        }

        return answer;
    }
}