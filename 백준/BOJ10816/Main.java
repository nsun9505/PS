package Backjoon.BOJ10816;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<M; i++){
            int target = Integer.parseInt(st.nextToken());
            if (map.containsKey(target)) {
                sb.append(map.get(target) + " ");
                continue;
            }
            int lower = lowerBound(target);
            int upper = upperbound(target);
            int cnt = 0;
            if(arr[lower] == target) {
                cnt = upper - lower;
                sb.append(cnt + " ");
            }
            else
                sb.append(cnt + " ");
            map.put(target, cnt);
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int lowerBound(int target){
        int left = 0;
        int right = N-1;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] < target)
                left =  mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public static int upperbound(int target){
        int left = 0;
        int right = N-1;
        while(left < right){
            int mid = (left + right) / 2;
            if(arr[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return right;
    }
}