package SWEA.SWEA2001;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[][] map = new int[N][N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            int ans = 0;
            for(int i=0; i<=N-M; i++){
                for(int j=0; j<=N-M; j++){
                    int sum = 0;
                    for(int row = i; row < i + M; row++){
                        for(int col = j; col < j + M; col++){
                            sum += map[row][col];
                        }
                    }
                    ans = Math.max(ans, sum);
                }
            }

            sb.append("#" + t + " " + ans + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
