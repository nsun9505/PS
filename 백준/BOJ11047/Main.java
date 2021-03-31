package Backjoon.BOJ11047;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=N-1; i>=0; i--){
            if(K - arr[i] >= 0) {
                cnt += (K / arr[i]);
                K %= arr[i];
            }
        }
        sb.append(cnt);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}