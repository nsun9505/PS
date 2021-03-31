package Backjoon.BOJ2748;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N+1];
        arr[0] = 0;
        arr[1] = 1;

        for(int i=2; i<=N; i++)
            arr[i] = arr[i-1] + arr[i-2];
        sb.append(arr[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
