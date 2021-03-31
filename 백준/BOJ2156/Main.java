package Backjoon.BOJ2156;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];
        int[][] dp = new int[10001][2];
        for(int i=1; i<=N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int ans = arr[1];
        dp[1][0] = arr[1];
        dp[1][1] = arr[1];

        dp[2][0] = arr[2];
        dp[2][1] = arr[1] + arr[2];
        ans = Math.max(arr[2], ans);
        ans = Math.max(dp[2][1], ans);

        dp[3][0] = arr[3] + arr[1];
        ans = Math.max(dp[3][0], ans);
        dp[3][1] = arr[3] + arr[2];
        ans = Math.max(dp[3][1], ans);

        for(int i=4; i<=N; i++){
            dp[i][0] = Math.max(Math.max(dp[i-2][0], dp[i-2][1]), Math.max(dp[i-3][0], dp[i-3][1])) + arr[i];
            ans = Math.max(ans, dp[i][0]);
            dp[i][1] = Math.max(dp[i-1][0], Math.max(dp[i-2][0], dp[i-2][1])) + arr[i];
            ans = Math.max(ans, dp[i][1]);

        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
