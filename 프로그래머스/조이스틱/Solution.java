package Programmers.조이스틱;

import java.util.Arrays;

class Solution {
    static int results[];
    public int solution(String name) {
        int answer = 0;
        results = new int[name.length()];
        Arrays.fill(results, Integer.MAX_VALUE);

        String initString = "";
        for(int i=0; i<name.length(); i++)
            initString += "A";

        DFS(0, name, initString, 0);

        return answer;
    }

    public static void DFS(int cur, String name, String init, int moveCnt){
        if(cur >= name.length())
            return;
        if(results[cur] < moveCnt)
            return;

        
    }

    public static int searchCharWithMoveDown(char search, char start){
        int cnt = 0;
        while(search != start){
            cnt++;
            start -= 1;
            if(start < 'A')
                start = 'Z';
        }

        return cnt;
    }

    public static int searchCharWithMoveUp(char search, char start){
        int cnt = 0;
        while(search != start && start <= 'Z'){
            cnt++;
            start += 1;
        }
        if(start == search)
            return cnt;
        return 100;
    }
}