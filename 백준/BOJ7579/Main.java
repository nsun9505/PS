package BOJ.BOJ7579;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][10001];
        int[] memory = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            memory[i] = Integer.parseInt(st.nextToken());

        int[] cost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)
            cost[i] = Integer.parseInt(st.nextToken());

        int answer = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            for(int c=0; c<10001; c++){
                if(c < cost[i])
                    dp[i][c] = dp[i-1][c];
                else{
                    dp[i][c] = Math.max(dp[i-1][c], dp[i-1][c-cost[i]] + memory[i]);
                }

                if(dp[i][c] >= M)
                    answer = Math.min(answer, c);
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
