package Backjoon.BOJ1463;

import java.io.*;
import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[0] = 0;
        arr[1] = 0;
        for(int i=2; i<=N; i++){
            arr[i] = arr[i-1]+1;
            if(i % 3 == 0)
                arr[i] = Math.min(arr[i], arr[i/3] + 1);
            if(i % 2 == 0)
                arr[i] = Math.min(arr[i], arr[i/2] + 1);
        }
        sb.append(arr[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
