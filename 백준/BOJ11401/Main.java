package Backjoon.BOJ11401;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long P = 1000000007;
    static long[] fac;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        fac = new long[N+1];
        fac[0] = 1L;
        for(int i=1; i<=N; i++)
            fac[i] = fac[i-1] * i % P;

        sb.append(nCr(N, R));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long power(long x, long y){
        long res = 1L;
        x %= P;
        while(y > 0){
            if(y % 2 == 1)
                res = (res * x) %P;
            y = y >> 1;
            x = (x * x) % P;
        }
        return res;
    }

    public static long nCr(int N, int R){
        if(R == 0)
            return 1;

        return ((fac[N] * (power(fac[R], P-2) % P) % P) * (power(fac[N-R], P-2) % P) % P);
    }
}
