package Backjoon.BOJ2667;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        ArrayList<Element> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            String input = br.readLine();
            for(int j=0; j<N; j++) {
                map[i][j] = input.charAt(j) - '0';
                if(map[i][j] == 1)
                    list.add(new Element(i, j));
            }
        }

        Queue<Element> queue = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for(Element elem : list){
            if(visited[elem.row][elem.col])
                continue;

            queue.offer(elem);
            visited[elem.row][elem.col] = true;

            int cnt = 0;
            while(!queue.isEmpty()){
                Element cur = queue.poll();
                cnt++;

                for(int dir = 0; dir<4; dir++){
                    int nx = cur.row + dx[dir];
                    int ny = cur.col + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    if(visited[nx][ny] || map[nx][ny] == 0)
                        continue;

                    queue.offer(new Element(nx, ny));
                    visited[nx][ny] = true;
                }
            }

            ans.add(cnt);
        }

        Collections.sort(ans);
        sb.append(ans.size() + "\n");
        for(int num : ans)
            sb.append(num + "\n");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
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
