package Backjoon.BOJ1976;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static boolean[][] connected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] travelPlan = new int[M];
        graph = new ArrayList[N];
        visited = new boolean[N];
        connected = new boolean[N][N];

        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
            connected[i][i] = true;
        }

        StringTokenizer st = null;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int next = Integer.parseInt(st.nextToken());
                if(next == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        for(int i=0; i<N; i++)
            find(i);

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++)
            travelPlan[i] = Integer.parseInt(st.nextToken())-1;

        String answer = "YES";
        for(int i=0; i<M-1; i++){
            int cur = travelPlan[i];
            int next = travelPlan[i+1];
            if(!connected[cur][next]){
                answer = "NO";
                break;
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void find(int start){
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(visited, false);
        visited[start] = true;
        queue.offer(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next : graph[cur]){
                if(visited[next])
                    continue;
                visited[next] = true;
                connected[start][next] = true;
                queue.offer(next);
            }
        }
    }
}
