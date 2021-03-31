package Backjoon.BOJ2636;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Element> queue = new LinkedList<>();
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
            }
        }

        int time = 0;
        int ans = 0;
        int cnt = 0;
        while(true){
            cnt = BFS();
            if(cnt == 0)
                break;
            ans = cnt;
            time++;
        }
        sb.append(time + "\n" + ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(){
        queue.clear();
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);
        queue.offer(new Element(0, 0)); visited[0][0] = true;
        queue.offer(new Element(N-1, 0)); visited[N-1][0] = true;
        queue.offer(new Element(0, M-1)); visited[0][M-1] = true;
        queue.offer(new Element(N-1, M-1)); visited[N-1][M-1] = true;

        int cnt = 0;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                if(map[nx][ny] == 0)
                    queue.offer(new Element(nx, ny));
                else {
                    cnt++;
                    map[nx][ny] = 0;
                }
            }
        }
        return cnt;
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
