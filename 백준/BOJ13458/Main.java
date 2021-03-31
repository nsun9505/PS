package Backjoon.BOJ13458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        long[] isUsed = new long[1000001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = 0;

        for(int i=0; i<N; i++){
            if(isUsed[arr[i]] > 0) {
                ans += isUsed[arr[i]];
                continue;
            }

            int num = arr[i];
            int cnt = 0;

            num -= B;
            cnt++;

            if(num <= 0){
                isUsed[arr[i]] = cnt;
                ans += cnt;
                continue;
            }

            cnt += (num / C);
            num %= C;
            if(num > 0)
                cnt++;

            ans += cnt;
            isUsed[arr[i]] = cnt;
        }
        System.out.println(ans);
    }
}
