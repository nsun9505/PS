package Backjoon.BOJ2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int left = 0;
        int right = 0;
        int sum = 0;
        int cnt = 0;
        while(true){
            if(M <= sum){
                if(sum == M)
                    cnt += 1;
                sum -= arr[left];
                left++;
            } else {
                if(right < N)
                    sum += arr[right];
                else
                    break;
                right++;
            }
        }
        System.out.println(cnt);
    }
}
