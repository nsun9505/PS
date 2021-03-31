package Programmers.땅따먹기;

public class 땅따먹기 {
    int solution(int[][] land) {
        int answer = 0;

        for(int i=1; i<land.length; i++){
            int maxNum = Math.max(Math.max(land[i-1][1], land[i-1][2]), land[i-1][3]);
            land[i][0] += maxNum;
            maxNum = Math.max(Math.max(land[i-1][0], land[i-1][2]), land[i-1][3]);
            land[i][1] += maxNum;
            maxNum = Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][3]);
            land[i][2] += maxNum;
            maxNum = Math.max(Math.max(land[i-1][0], land[i-1][1]), land[i-1][2]);
            land[i][3] += maxNum;
        }
        for(int i=0; i<land[land.length-1].length; i++)
            answer = Math.max(land[land.length-1][i], answer);
        return answer;
    }
}
