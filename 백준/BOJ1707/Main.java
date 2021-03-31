package Backjoon.BOJ1707;

import java.io.*;
import java.util.*;


/*
*
*   이분 그래프 : 서로 인접한 정점 간에 서로 다른 색을 갖는다. -> 다른 집합이다.
*
* */


public class Main {
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            char[] colors = new char[V+1];
            Arrays.fill(colors, '-');
            graph = new ArrayList[V+1];
            for(int i=1; i<=V; i++)
                graph[i] = new ArrayList<>();

            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                graph[u].add(v);
                graph[v].add(u);
            }

            boolean ret = true;
            for(int i=1; i<=V; i++) {
                if(colors[i] != '-')
                    continue;
                colors[i] = 'R';
                if(!BFS(i, colors)) {
                    ret = false;
                    break;
                }
            }

            if(ret)
                sb.append("YES\n");
            else
                sb.append("NO\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static boolean BFS(int start, char[] colors){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next : graph[cur]){
                if(colors[next] == '-') {
                    colors[next] = colors[cur] == 'R' ? 'B' : 'R';
                    queue.offer(next);
                } else if(colors[next] == colors[cur])
                    return false;
            }
        }
        return true;
    }
}