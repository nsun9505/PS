package Backjoon.BOJ1697;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] distance = new int[200001];
        queue.offer(N);

        int answer = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();

            if(cur == K){
                answer = distance[cur];
                break;
            }

            if(cur - 1 >= 0 && distance[cur-1] == 0){
                distance[cur-1] = distance[cur] + 1;
                queue.offer(cur-1);
            }

            if(cur + 1 <= 200000 && distance[cur+1] == 0){
                distance[cur+1] = distance[cur] + 1;
                queue.offer(cur+1);
            }

            if(cur * 2 <= 200000 && distance[cur*2] == 0){
                distance[cur*2] = distance[cur] + 1;
                queue.offer(cur*2);
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
