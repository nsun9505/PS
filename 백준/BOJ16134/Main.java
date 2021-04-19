package Backjoon.BOJ16134;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long P = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

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

        long nFac = fact(N);
        long rFac = fact(R);
        long n_rFac = fact(N-R);

        return ((nFac * (power(rFac, P-2) % P) % P) * (power(n_rFac, P-2) % P) % P);
    }

    public static long fact(int N){
        long res = 1L;
        for(int i=1; i<=N;i++)
            res = res * i % P;
        return res;
    }
}
