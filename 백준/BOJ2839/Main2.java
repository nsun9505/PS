package Backjoon.BOJ2839;

import java.io.*;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

//        int[] arr = new int[N+1];
//        Arrays.fill(arr, 5001);
//
//        arr[0] = 0;
//        for(int i=0; i<=N-3; i++) {
//            if(arr[i] == 5001)
//                continue;
//            if (i + 3 <= N)
//                arr[i + 3] = Math.min(arr[i] + 1, arr[i + 3]);
//            if (i + 5 <= N)
//                arr[i + 5] = Math.min(arr[i] + 1, arr[i + 5]);
//        }
//
//        if(arr[N] == 5001)
//            sb.append(-1);
//        else
//            sb.append(arr[N]);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
