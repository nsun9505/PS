package Backjoon.BOJ1261;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        Deque<Element> deque = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            String input = br.readLine();
            map[i] = input.toCharArray();
        }

        deque.offer(new Element(0, 0, 0));
        visited[0][0] = true;

        int answer = 0;
        while(!deque.isEmpty()){
            Element cur = deque.pollFirst();

            if(cur.row == N-1 && cur.col == M-1){
                answer = cur.dist;
                break;
            }

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(visited[nx][ny])
                    continue;

                visited[nx][ny] = true;
                if(map[nx][ny] == '0')
                    deque.offerFirst(new Element(nx, ny, cur.dist));
                else
                    deque.offerLast(new Element(nx, ny, cur.dist+1));
            }
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int row;
        int col;
        int dist;

        public Element(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
