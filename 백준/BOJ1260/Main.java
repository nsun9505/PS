package Backjoon.BOJ1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] isVisited;
    static char[][] graph;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        graph = new char[N+1][N+1];
        isVisited = new boolean[N+1];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u][v] = '1';
            graph[v][u] = '1';
        }

        DFS(start);
        System.out.println();
        BFS(start);
    }

    public static void DFS(int vertex){
        isVisited[vertex] = true;
        System.out.print(vertex + " ");
        for(int i=1; i<=N; i++){

            if(i == vertex || graph[vertex][i] != '1')
                continue;
            if(isVisited[i])
                continue;
            DFS(i);
        }
    }

    public static void BFS(int start){
        Arrays.fill(isVisited, false);
        isVisited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();
            System.out.print(cur + " ");

            for(int next=1; next<=N; next++){
                if(next == cur || graph[cur][next] != '1')
                    continue;
                if(isVisited[next])
                    continue;

                isVisited[next] = true;
                queue.offer(next);
            }
        }
    }
}
