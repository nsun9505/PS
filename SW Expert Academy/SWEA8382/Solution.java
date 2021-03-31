package SWEA.SWEA8382;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static final int SIZE = 200;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken()) + 100;
            int startY = Integer.parseInt(st.nextToken()) + 100;
            int endX = Integer.parseInt(st.nextToken()) + 100;
            int endY = Integer.parseInt(st.nextToken()) + 100;

            boolean[][][] visited = new boolean[4][SIZE+1][SIZE+1];
            Queue<Element> queue = new LinkedList<>();
            for(int dir = 0; dir<4; dir++){
                queue.offer(new Element(startX, startY, dir, 0));
                visited[dir][startY][startX] = true;
            }

            int answer = 0;
            while(!queue.isEmpty()){
                Element cur = queue.poll();

                if(cur.x == endX && cur.y == endY){
                    answer = cur.dist;
                    break;
                }

                int start = 0;
                if(cur.dir == 0 || cur.dir == 2)
                    start = 1;

                for(int dir=start; dir<4; dir+=2){
                    int ny = cur.y + dy[dir];
                    int nx = cur.x + dx[dir];

                    if(nx < 0 || ny < 0 || nx > SIZE || ny > SIZE)
                        continue;

                    if(visited[dir][ny][nx])
                        continue;

                    visited[dir][ny][nx] = true;
                    queue.offer(new Element(nx , ny, dir, cur.dist+1));
                }
            }

            sb.append("#" + t + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int x;
        int y;
        int dir;
        int dist;

        public Element(int x, int y, int dir, int dist) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.dist = dist;
        }
    }
}
