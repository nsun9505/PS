package Backjoon.BOJ11054;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] dp1;
    static int[] dp2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        dp1 = new int[N];
        dp2 = new int[N];

        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        for(int i=0; i<N; i++){
            dp1[i] = 1;
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j] && dp1[i] < dp1[j] + 1){
                    dp1[i] = dp1[j] + 1;
                }
            }
        }

        for(int i=N-1; i>=0; i--){
            dp2[i] = 1;
            for(int j=N-1; j>i; j--){
                if(arr[i] > arr[j] && dp2[i] < dp2[j] + 1){
                    dp2[i] = dp2[j] + 1;
                }
            }

           if(answer < dp1[i] + dp2[i] - 1 ){
               answer = dp1[i] + dp2[i] - 1;
           }
        }

        sb.append(answer);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
