package SWEA.SWEA1249;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    static int[][] map;
    static int[][] dist;
    static int N;
    static final int INF = 100000;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dist = new int[N][N];

            for(int i=0; i<N; i++) {
                char[] line = br.readLine().toCharArray();
                Arrays.fill(dist[i], INF);
                for(int j=0; j<N; j++)
                    map[i][j] = (int)(line[j] - '0');
            }

            PriorityQueue<Element> pq = new PriorityQueue<>();
            pq.offer(new Element(0, 0, 0));
            dist[0][0] = 0;

            while(!pq.isEmpty()){
                Element cur = pq.poll();

                if(cur.weight != dist[cur.row][cur.col])
                    continue;

                for(int dir=0; dir<4; dir++){
                    int nx = cur.row + dx[dir];
                    int ny = cur.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    int d = cur.weight + map[nx][ny];
                    if(d < dist[nx][ny]){
                        dist[nx][ny] = d;
                        pq.offer(new Element(nx, ny, d));
                    }
                }
            }

            sb.append("#" + t + " " + dist[N-1][N-1] + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element implements Comparable<Element>{
        int row;
        int col;
        int weight;

        public Element(int row, int col, int weight) {
            this.row = row;
            this.col = col;
            this.weight = weight;
        }

        @Override
        public int compareTo(Element o) {
            return this.weight - o.weight;
        }
    }
}
