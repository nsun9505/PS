package Backjoon.BOJ2309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N = 9;
    static boolean isOk = false;
    static boolean isUsed[] = new boolean[9];
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[N];
        for(int i=0; i<N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        solution(new int [7], 0, 0);
    }

    public static void solution(int[] ans, int idx, int sum){
        if(isOk)
            return;

        if(idx >= ans.length){
            if(sum == 100){
                isOk = true;
                for(int n : ans)
                    System.out.println(n);
            }
            return;
        }

        for(int i=0; i<N; i++){
            if(!isUsed[i]){
                isUsed[i] = true;
                ans[idx] = arr[i];
                solution(ans, idx+1, sum + arr[i]);
                isUsed[i] = false;
            }
        }
    }
}
