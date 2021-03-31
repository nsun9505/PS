package Backjoon.BOJ2428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        arr[N] = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        long ans = 0;
        for(int i=0; i<N-1; i++)
            ans += upperbound(i+1, N, arr[i]) - i;
        System.out.println(ans);
    }

    public static long upperbound(int left, int right, int num){
        while(left < right){
            int mid = (left + right) / 2;
            if(num >= (0.9 * arr[mid]))
                left = mid + 1;
            else
                right = mid;
        }
        return right - 1;
    }
}