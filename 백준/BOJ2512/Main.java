package Backjoon.BOJ2512;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxValue = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxValue = Math.max(arr[i], maxValue);
        }
        int maxBudget = Integer.parseInt(br.readLine());
        int left = 1;
        int right = maxValue;
         while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;
            for (int i = 0; i < N; i++)
                if (arr[i] <= mid)
                    sum += arr[i];
                else
                    sum += mid;

            if (sum <= maxBudget)
                left = mid + 1;
            else
                right = mid - 1;
        }
        System.out.println(right); // 이게 핵심인 것 같음!
    }
}