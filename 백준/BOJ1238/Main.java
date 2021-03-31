package Backjoon.BOJ1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
    static ArrayList<Node>[] graph;
    static class Node implements Comparable<Node>{
        int ver;
        int dist;

        Node(int v, int d){
            this.ver = v;
            this.dist = d;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken()) - 1;

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
        }

        int[] dist = dijkstra(X, N);
        int maxDist = 0;
        for (int i = 0; i < N; i++) {
            if(i == X)
                continue;

            int[] tmpDist = dijkstra(i, N);
            maxDist = Math.max(dist[i] + tmpDist[X], maxDist);
        }
        System.out.println(maxDist);
    }

    public static int[] dijkstra(int X, int N){
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dist[cur.ver] < cur.dist)
                continue;

            for (Node next : graph[cur.ver]) {
                int nextDist = cur.dist + next.dist;
                if (nextDist < dist[next.ver]) {
                    dist[next.ver] = nextDist;
                    pq.offer(new Node(next.ver, nextDist));
                }
            }
        }
        return dist;
    }
}
