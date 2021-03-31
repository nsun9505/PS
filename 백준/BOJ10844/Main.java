package Backjoon.BOJ10844;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];

        for(int i=1; i<10; i++)
            dp[1][i] = 1;

        for(int i=2; i<=N; i++){
            dp[i][0] = dp[i-1][1];
            dp[i][9] = dp[i-1][8];
            for(int j=1; j<9; j++)
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
        }

        long sum = 0;
        for(int i=0; i<10; i++)
            sum += dp[N][i];
        sb.append(sum % 1000000000);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int num;
        int level;

        public Element(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }
}
