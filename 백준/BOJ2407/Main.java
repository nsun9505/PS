package Backjoon.BOJ2407;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        BigInteger[][] dp =  new BigInteger[N+1][N+1];
        for(int i=0; i<N+1; i++)
            Arrays.fill(dp[i], new BigInteger("0"));
        for(int i=0; i<=N; i++){
            for(int j=0; j<=i; j++){
                if(i == j || j == 0)
                    dp[i][j] = new BigInteger("1");
                else
                    dp[i][j] = dp[i-1][j-1].add(dp[i-1][j]);
            }
        }

        sb.append(dp[N][M]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
