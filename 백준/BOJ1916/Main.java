package Backjoon.BOJ1916;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static class Node implements Comparable<Node>{
        int vertex;
        int dist;

        Node(int v, int d){
            this.vertex = v;
            this.dist = d;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        ArrayList<Node>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        int[] dist = new int[N];
        Arrays.fill(dist, INF);

        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken())-1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (dist[cur.vertex] < cur.dist)
                continue;

            for (Node next : graph[cur.vertex]) {
                int nextDist = cur.dist + next.dist;
                if(dist[next.vertex] > nextDist){
                    dist[next.vertex] = nextDist;
                    pq.offer(new Node(next.vertex, nextDist));
                }
            }
        }

        bw.write(String.valueOf(dist[end]));
        bw.flush();
        bw.close();
        br.close();
    }
}