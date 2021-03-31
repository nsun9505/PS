package Backjoon.BOJ4344;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int[] arr = new int[N];
            int sum = 0;
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum +=arr[i];
            }

            double avg = (double)sum / N;
            int cnt = 0;
            for(int i=0; i<N; i++){
                if(avg < arr[i])
                    cnt++;
            }

            sb.append(String.format("%.3f", (double)cnt / N * 100) + "%\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
