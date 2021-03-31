package Backjoon.BOJ1932;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N+1][N+1];
        int[][] dp = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=i; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[1][1] = arr[1][1];
        int ans = 0;
        for(int i=2; i<=N; i++){
            for(int j=1; j<=i; j++){
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + arr[i][j];
                ans = Math.max(ans, dp[i][j]);
            }
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
