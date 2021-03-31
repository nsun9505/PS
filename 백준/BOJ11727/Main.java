package Backjoon.BOJ11727;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[1001];
        arr[1] = 1;
        arr[2] = 3;

        for(int i=3; i<=N; i++)
            arr[i] =  ((arr[i-2] * 2) + arr[i-1]) % 10007;
        sb.append(arr[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
