package Backjoon.BOJ14502;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Element> virusList = new ArrayList<>();
    static ArrayList<Element> emptyList = new ArrayList<>();
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    virusList.add(new Element(i , j));
                else if(map[i][j] == 0)
                    emptyList.add(new Element(i, j));
            }
        }
        combination(0, 0);
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void combination(int cnt, int index){
        if(cnt == 3){
            BFS();
            return;
        }

        if(index >= emptyList.size())
            return;

        Element elem = emptyList.get(index);
        map[elem.row][elem.col] = 1;
        combination(cnt+1, index+1);
        map[elem.row][elem.col] = 0;
        combination(cnt, index+1);
    }

    public static void BFS(){
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);
        for(Element virus : virusList){
            queue.offer(virus);
            visited[virus.row][virus.col] = true;
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            Element cur = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(visited[nx][ny])
                    continue;

                if(map[nx][ny] == 0) {
                    cnt++;
                    visited[nx][ny] = true;
                    queue.offer(new Element(nx, ny));
                }
            }
        }
        int ret = emptyList.size() - cnt - 3;
        answer = Math.max(ret, answer);
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
