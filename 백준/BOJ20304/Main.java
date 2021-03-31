package Backjoon.BOJ20304;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int[] arr = new int[1000001];
        Arrays.fill(arr, -1);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());
            arr[num] = 0;
            queue.offer(num);
        }

        int ans = 0;
        while(!queue.isEmpty()){
            int num = queue.poll();

            for(int i=0; i<20; i++){
                int next = num ^ (1 << i);
                if(next > N || arr[next] != -1)
                    continue;

                arr[next] = arr[num] + 1;
                queue.offer(next);
                ans = Math.max(arr[next], ans);
            }
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
