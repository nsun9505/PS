package Backjoon.BOJ2961;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static long ans = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(0, 1, 0);
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, long mul, long sum){
        if(idx >= arr.length){
            if(mul == 1 || sum == 0)
                return;

            ans = Math.min(ans, Math.abs(mul - sum));
            return;
        }

        solution(idx+1, mul * arr[idx][0], sum + arr[idx][1]);
        solution(idx+1, mul, sum);
    }
}