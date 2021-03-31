package Backjoon.BOJ1504;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Element>[] graph;
    static int[] distance;
    static int N, E;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        distance = new int[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Element(v, w));
            graph[v].add(new Element(u, w));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        long[] startDistance = dijkstra(1);
        long[] v1Distance = dijkstra(v1);
        long[] v2Distance = dijkstra(v2);

        long answer1 = startDistance[v1] + v1Distance[v2] + v2Distance[N];
        long answer2 = startDistance[v2] + v2Distance[v1] + v1Distance[N];
        long answer = Math.min(answer1, answer2);
        if(answer >= Integer.MAX_VALUE)
            answer = -1;
        sb.append(answer);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static long[] dijkstra(int src){
        long[] distance = new long[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        PriorityQueue<Element> pq = new PriorityQueue<>();
        pq.offer(new Element(src, 0));
        distance[src] = 0;

        while(!pq.isEmpty()){
            Element cur = pq.poll();

            if(distance[cur.vertex] != cur.dist)
                continue;

            for(Element next : graph[cur.vertex]){
                int dist = cur.dist + next.dist;
                if(distance[next.vertex] > dist){
                    pq.offer(new Element(next.vertex, dist));
                    distance[next.vertex] = dist;
                }
            }
        }

        return distance;
    }

    static class Element implements Comparable<Element>{
        int vertex;
        int dist;

        public Element(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Element o) {
            return this.dist - o.dist;
        }
    }
}
