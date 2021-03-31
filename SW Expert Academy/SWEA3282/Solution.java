package SWEA.SWEA3282;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][] dp = new int[N+1][K+1];
            int[] weights = new int[N+1];
            int[] values = new int[N+1];
            for(int i=1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                weights[i] = Integer.parseInt(st.nextToken());
                values[i] = Integer.parseInt(st.nextToken());

                for(int w=1; w<=K; w++){
                    if(weights[i] > w)
                        dp[i][w] = dp[i-1][w];
                    else
                        dp[i][w] = Math.max(values[i] + dp[i-1][w-weights[i]], dp[i-1][w]);
                }
            }

            sb.append("#" + t + " " + dp[N][K] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
