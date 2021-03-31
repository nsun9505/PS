package Backjoon.BOJ10815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int target = Integer.parseInt(st.nextToken());
            int left = 0;
            int right = N-1;
            int flag = 0;
            while(left <= right){
                int mid = (left + right) / 2;

                if(arr[mid] == target){
                    flag = 1;
                    break;
                }

                if(arr[mid] < target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }

            System.out.print(flag + " ");
        }
    }

}
