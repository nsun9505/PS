package Backjoon.BOJ2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);
        int left = 1;
        int right = 1000000000;
        int ans = 0;

        while(left<=right){
            int mid = (left + right) / 2;
            int start = 0;
            int cnt = 1;
            for(int i=1; i<arr.length; i++){
                // arr[i]에서 arr[start]를 빼서 mid와 같거나 크다면
                // 해당 위치에 공유기 설치!
                if(arr[i] - arr[start] >= mid){
                    // 시작 위치 변경
                    start = i;
                    cnt++;
                }
            }

            // cnt가 M보다 작다는 것은 범위가 너무 크다는 것이다.
            if(cnt < M){
                right = mid - 1;
            } else {
                left = mid + 1;
                ans = Math.max(ans, mid);
            }
        }
        System.out.println(ans);
    }
}
