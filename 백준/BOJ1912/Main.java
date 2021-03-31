package Backjoon.BOJ1912;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int prev = Integer.parseInt(st.nextToken());
        int ans = prev;
        for(int i=1; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            prev = Math.max(prev + num, num);
            ans = Math.max(prev, ans);
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
