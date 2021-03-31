package Backjoon.BOJ11403;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static boolean[] isVisited;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        isVisited = new boolean[N];
        graph = new ArrayList[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            graph[i] = new ArrayList<>();
            for(int j=0; j<N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 1)
                    graph[i].add(j);
            }
        }

        for(int i=0; i<N; i++)
            BFS(i);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(int start){
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(isVisited, false);
        queue.offer(start);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(int next : graph[cur]){
                if(isVisited[next])
                    continue;
                arr[start][next] = 1;
                isVisited[next] = true;
                queue.offer(next);
            }
        }
    }
}
