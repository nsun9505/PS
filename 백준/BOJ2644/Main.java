package Backjoon.BOJ2644;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] graph = new ArrayList[N+1];
        boolean[] visited = new boolean[N+1];
        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(v1, 0));
        visited[v1] = true;

        int answer = -1;
        while(!queue.isEmpty()){
            Element cur = queue.poll();

            if(cur.vertex == v2){
                answer = cur.dist;
                break;
            }

            for(int next : graph[cur.vertex]){
                if(visited[next])
                    continue;

                visited[next] = true;
                queue.offer(new Element(next, cur.dist+1));
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int vertex;
        int dist;

        public Element(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
