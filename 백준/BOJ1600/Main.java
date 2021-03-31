package Backjoon.BOJ1600;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] horseDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseDy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int H, W;
    static int[][][] dist;
    static char[][] map;
    static Queue<Element> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        int K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dist = new int[K+1][H][W];
        map = new char[H][W];

        // 맵 할당
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++)
                map[i][j] = st.nextToken().charAt(0);
        }


        // 3차원 배열 거리(및 방문) 체크
        /*
         * dist[K][H][W]
         *  1. 말처럼 K번 뛸 수 있는 경우에 다음 위치를 아직 방문하지 않은 경우
         *  2. 말처럼 K번 뛸 수 있는 경우에 다음 위치를 이미 방문했지만, 지금 움직이는 것이 더 최적인 경우
         *  -1로 초기화해서 아직 방문하지 않음을 표현
         */
        for(int i=0; i<=K; i++){
            for(int j=0; j<H; j++)
                Arrays.fill(dist[i][j], -1);
        }

        queue.offer(new Element(0, 0, K));
        dist[K][0][0] = 0;

        int answer = -1;
        while(!queue.isEmpty()) {
            Element cur = queue.poll();

            // 답을 찾은 경우 break
            if(cur.row == H-1 && cur.col == W-1) {
                answer = dist[cur.k][cur.row][cur.col];
                break;
            }
            
            // 그냥 인접한 곳으로 가는 경우
            search(dx, dy, 0, cur);

            // 말처럼 움직일 수 있는 횟수가 1번 이상 남았으면 현재 위치에서 말처럼 움직여 보기
            if(cur.k > 0)
                search(horseDx, horseDy, -1, cur);
        }
        if(answer == Integer.MAX_VALUE)
            answer = -1;
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void search(int[] dx, int[] dy, int dec, Element cur){
        for(int dir = 0; dir<dx.length; dir++){
            int nx = cur.row + dx[dir];
            int ny = cur.col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= H || ny >= W)
                continue;

            if(map[nx][ny] == '1')
                continue;

            // 처음 방문하는 경우
            if(dist[cur.k+dec][nx][ny] == -1){
                dist[cur.k+dec][nx][ny] = dist[cur.k][cur.row][cur.col] + 1;
                queue.offer(new Element(nx, ny, cur.k+dec));
            }
            // cur.k번 말처럼 움직일 수 있을 때 (이전에 누군가 방문 했음)다음 위치를 이미 방문했지만, 현재에서 다음 위치로 가는 이동 횟수가 최소인 경우
            else if(dist[cur.k+dec][nx][ny] > dist[cur.k][cur.row][cur.col] + 1){
                dist[cur.k+dec][nx][ny] = dist[cur.k][cur.row][cur.col] + 1;
                queue.offer(new Element(nx, ny, cur.k+dec));
            }
        }
    }

    static class Element{
        int row;
        int col;
        int k;

        public Element(int row, int col, int k) {
            this.row = row;
            this.col = col;
            this.k = k;
        }
    }
}
