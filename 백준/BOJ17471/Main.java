package Backjoon.BOJ17471;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer>[] graph;
    static Queue<Integer> queue = new LinkedList<>();
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        graph = new ArrayList[N];
        visited = new boolean[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for(int j=0; j<size; j++){
                int next = Integer.parseInt(st.nextToken()) - 1;
                graph[i].add(next);
            }
        }

        int answer = Integer.MAX_VALUE;
        for(int i=1; i<(1 << N); i++){
            int sumOfGroup1 = BFS(i);
            int sumOfGroup2 = BFS(i ^ ((1<<N)-1));

            if(sumOfGroup1 == -1 || sumOfGroup2 == -1)
                continue;

            int diff = Math.abs(sumOfGroup1 - sumOfGroup2);
            answer = Math.min(answer, diff);
        }

        if(answer == Integer.MAX_VALUE) sb.append(-1);
        else sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int group){
        queue.clear();
        Arrays.fill(visited, false);

        for(int i=0; i<N; i++){
            int idx = (1 << i);
            if((group & idx) > 0){
                queue.offer(i);
                visited[i] = true;
                break;
            }
        }

        int ret = 0;
        int cnt = 0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            cnt++;
            ret += arr[cur];

            for(int next : graph[cur]){
                if(visited[next])
                    continue;

                if((group & (1 << next)) > 0){
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }

        if(cnt == Integer.bitCount(group))
            return ret;
        return -1;
    }
}
