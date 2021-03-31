package Backjoon.BOJ1026;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            B[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(A);
        Arrays.sort(B);

        int S = 0;
        for(int i=0, j=N-1; i<N; i++,j--)
            S += A[i] * B[j];

        sb.append(S);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
