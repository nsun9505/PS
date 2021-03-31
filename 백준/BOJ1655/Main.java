package Backjoon.BOJ1655;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(br.readLine());
            if(maxHeap.size() - minHeap.size() == 1)
                minHeap.offer(num);
            else
                maxHeap.offer(num);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
                int max = maxHeap.poll();
                int min = minHeap.poll();

                maxHeap.offer(min);
                minHeap.offer(max);
            }

            sb.append(maxHeap.peek() + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
