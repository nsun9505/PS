package Backjoon.BOJ2805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 2000000000;
        int ans = 0;
        while(left <= right){
            int mid = (left + right) / 2;

            long sum = 0;
            for(int i=0; i<N; i++){
                if(mid < arr[i])
                    sum += arr[i] - mid;
            }

            if(sum >= M){
                left = mid + 1;
                if(ans < mid)
                    ans = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
}