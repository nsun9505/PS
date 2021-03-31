package Backjoon.BOJ11055;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] sum = new int[N];
        int[] length = new int[N];
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        for(int i=0; i<N; i++){
            length[i] = 1;
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = arr[i];
            for(int j=0; j<i; j++){
                if(arr[j] < arr[i] && length[i] < length[j]+1){
                    length[i] = length[j] + 1;
                    sum[i] = Math.max(sum[i], sum[j] + arr[i]);
                }
            }
            answer = Math.max(answer, sum[i]);
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
