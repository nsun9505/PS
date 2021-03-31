package Backjoon.BOJ2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];


        for (int i = 1; i <= N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(arr[1]);
            return;
        } else if(N == 2){
            System.out.println(arr[1] + arr[2]);
            return;
        }
        dp[1] = arr[1];
        dp[2] = arr[2] + arr[1];
        for(int i=3; i<=N; i++)
            dp[i] = Math.max(dp[i-2],dp[i-3] + arr[i-1]) + arr[i];
        /*
            dp[i-2] : i번째 계단을 오는데 2계단을 한 번에 올라온 경우
            dp[i-3] + arr[i-1] : i번째 계단을 오는데 2번 건너띄고 1번 건너뛴 경우
                                 즉, i-3번에서 2계단을 건너뛴 경우이다.
         */
        System.out.println(dp[N]);
    }
}
