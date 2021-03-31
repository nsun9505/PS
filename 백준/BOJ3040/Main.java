package Backjoon.BOJ3040;

import java.io.*;

public class Main {
    static final int N = 9;
    static int[] arr = new int[N];
    static boolean[] isUsed = new boolean[N];
    static boolean isFind = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        solution(0, 0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, int cnt, int sum){
        if(sum > 100 || isFind)
            return;

        if(cnt == 7){
            if(sum == 100) {
                isFind = true;
                for(int i=0; i<N; i++)
                    if(isUsed[i]) System.out.println(arr[i]);
            }
            return;
        }

        if(idx >= N)
            return;

        isUsed[idx] = true;
        solution(idx+1, cnt+1, sum + arr[idx]);
        isUsed[idx] = false;
        solution(idx+1, cnt, sum);
    }
}
