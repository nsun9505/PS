package Programmers.합승택시요금;

public class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] map = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                map[i][j] = Integer.MAX_VALUE;

        for(int i=0; i<n; i++)
            map[i][i] = 0;

        for(int i=0; i<fares.length; i++){
            int u = fares[i][0] - 1;
            int v = fares[i][1] - 1;
            map[u][v] = fares[i][2];
            map[v][u] = fares[i][2];
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                if(i == k)
                    continue;
                for(int j=0; j<n; j++){
                    if(i == j || j == k)
                        continue;
                    if(map[i][k] == Integer.MAX_VALUE || map[k][j] == Integer.MAX_VALUE)
                        continue;

                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        int min = map[s-1][a-1] + map[s-1][b-1];
        for(int i=0; i<n; i++){
            if(i != (s-1)){
                min = Math.min(min, map[i][a-1] + map[i][b-1] + map[s-1][i]);
            }
        }

        answer = min;

        return answer;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(6,4,6,2, new int[][]{{4,1,10}, {3, 5, 24}, {5,6,2}, {3,1,41}, {5,1,24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}});
    }
}
