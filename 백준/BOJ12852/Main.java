package Backjoon.BOJ12852;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        arr[0] = 0;
        arr[1] = 0;
        for(int i=2; i<=N; i++){
            arr[i] = arr[i-1] + 1;
            if(i % 2 == 0)
                arr[i] = Math.min(arr[i/2] + 1, arr[i]);
            if(i % 3 == 0)
                arr[i] = Math.min(arr[i/3] + 1, arr[i]);
        }

        int start = N;
        sb.append(arr[N] + "\n");
        sb.append(N + " ");
        while(start > 1){
            int num = arr[start] - 1;
            if(num == arr[start-1]){
                start = start-1;
            } else if(start % 3 == 0  && num == arr[start/3]){
                start = start / 3;
            } else if(start % 2 == 0 && num == arr[start/2]){
                start = start /2;
            }
            sb.append(start + " ");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
