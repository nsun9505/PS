package Backjoon.BOJ1149;

import java.io.*;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][3];
        StringTokenizer st = null;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + red;
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + green;
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + blue;
        }

        sb.append(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}