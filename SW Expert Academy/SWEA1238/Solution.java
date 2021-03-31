package SWEA.SWEA1238;

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = 10;
        boolean[] visited = new boolean[101];
        ArrayList<Integer>[] graph = new ArrayList[101];
        for(int i=1; i<=100; i++)
            graph[i] = new ArrayList<>();
        Queue<Element> queue = new LinkedList<>();
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            Arrays.fill(visited, false);
            queue.clear();
            for(int i=1; i<=100; i++)
                graph[i].clear();

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i+=2){
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                boolean exist = false;
                for(int next : graph[u]){
                    if(next == v){
                        exist = true;
                        break;
                    }
                }

                if(exist)
                    continue;

                graph[u].add(v);
            }

            queue.offer(new Element(start, 0));
            visited[start] = true;
            int maxVertex = start;
            int maxLevel = 0;
            while(!queue.isEmpty()){
                Element cur = queue.poll();

                for(int next : graph[cur.vertex]){
                    if(visited[next])
                        continue;

                    if(maxLevel < cur.level+1){
                        maxLevel = cur.level + 1;
                        maxVertex = next;
                    } else if(maxLevel == cur.level+1){
                        maxVertex = Math.max(maxVertex, next);
                    }

                    queue.offer(new Element(next, cur.level+1));
                    visited[next] = true;
                }
            }

            sb.append("#" + t + " " + maxVertex + "\n");
        }


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int vertex;
        int level;

        public Element(int vertex, int level) {
            this.vertex = vertex;
            this.level = level;
        }
    }
}
