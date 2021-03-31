package Backjoon.BOJ4485;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 2000;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int[][] map = new int[125][125];
        int[][] dist = new int[125][125];
        for(int t=1;; t++){
            int N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;
            int answer = 0;
            StringTokenizer st = null;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            dist[0][0] = map[0][0];
            PriorityQueue<Element> pq = new PriorityQueue<>();
            pq.offer(new Element(0, 0, dist[0][0]));

            while(!pq.isEmpty()){
                Element cur = pq.poll();

                if(cur.dist > dist[cur.row][cur.col])
                    continue;

                for(int dir=0; dir<4; dir++){
                    int nx = cur.row + dx[dir];
                    int ny = cur.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    int d = cur.dist + map[nx][ny];
                    if(dist[nx][ny] > d){
                        dist[nx][ny] = d;
                        pq.offer(new Element(nx, ny, d));
                    }
                }
            }

            sb.append("Problem "+ t + ": " + dist[N-1][N-1] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element implements Comparable<Element>{
        int row;
        int col;
        int dist;

        public Element(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }

        @Override
        public int compareTo(Element o) {
            return this.dist - o.dist;
        }
    }
}
