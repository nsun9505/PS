package Backjoon.BOJ1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] arr = new int[K];

        long left = 0;
        long right = 0;
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            right = Math.max(arr[i], right);
        }

        long ans = 0;
        while(left <= right){
            long mid = (left + right) / 2;
            if(mid == 0)
                mid = 1;

            int cnt = 0;
            for(int i=0; i<K; i++)
                cnt += (arr[i] / mid);

            if(cnt < N)
                right = mid - 1;
            else {
                left = mid + 1;
                ans = Math.max(ans, mid);
            }
        }

        System.out.println(ans);
    }
}