package SWEA.SWEA5607;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static long P = 1234567891;
    static long[] fac = new long[1000001];
    static int N, R;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        fac[0] = 1;
        for(int i=1; i<=1000000; i++)
            fac[i] = fac[i-1] * i % P;

        for(int t=1; t<=T; t++){
            StringTokenizer st =  new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            sb.append("#" + t + " " + nCr(N, R) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }


    // 거듭제곱으로 N승 구하기
    // N이 홀수 = 2T + 1
    // N이 짝수 = 2T
    public static long power(long x, long y){
        long res = 1L;
        x %= P;

        while(y > 0){
            if(y % 2 == 1)
                res = (res * x) % P;
            y = y >> 1;
            x = (x * x) % P;
        }

        return res;
    }

    public static long nCr(int n, int r){
        if(r == 0)
            return 1L;

        return ((fac[N] * (power(fac[R], P-2) % P) % P) * (power(fac[N-R], P-2) % P) % P);
    }
}
