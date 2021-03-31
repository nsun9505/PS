package Backjoon.BOJ1010;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[31][31];
        for(int i=1; i<=30; i++){
            for(int j=0; j<=i; j++){
                if(i == j || j == 0)
                    dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            sb.append(dp[M][N] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
