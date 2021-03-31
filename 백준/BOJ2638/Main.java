package Backjoon.BOJ2638;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] counts;
    static boolean[][] visited;
    static Queue<Element> queue = new LinkedList<>();
    static Element[] starts = new Element[4];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        starts[0] = new Element(0 ,0);
        starts[1] = new Element(0, M-1);
        starts[2] = new Element(N-1, 0);
        starts[3] = new Element(N-1, M-1);
        map = new char[N][M];
        counts = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        int time = 0;
        while(true){
            int count = BFS();
            if(count == (N*M))
                break;

            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(map[i][j] == '0')
                        continue;

                    if(counts[i][j] < 2)
                        continue;

                    map[i][j] = '0';
                }
            }
            time++;
        }
        sb.append(time);


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(){
        for(int i=0; i<N; i++) {
            Arrays.fill(counts[i], 0);
            Arrays.fill(visited[i], false);
        }
        for(int i=0; i<4; i++) {
            queue.offer(starts[i]);
            visited[starts[i].row][starts[i].col] = true;
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            Element cur = queue.poll();
            cnt++;

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(visited[nx][ny])
                    continue;

                if(map[nx][ny] == '0') {
                    queue.offer(new Element(nx, ny));
                    visited[nx][ny] = true;
                }
                else
                    counts[nx][ny] += 1;
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
