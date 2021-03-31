package Backjoon.BOJ4963;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static boolean[][] isVisited = new boolean[50][50];
    static Queue<Element> queue = new LinkedList<>();
    static int[][] map = new int[50][50];
    static int H, W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        ArrayList<Element> landList = new ArrayList<>();

        while(true){
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0)
                break;

            landList.clear();
            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    isVisited[i][j] = false;
                    if(map[i][j] == 1)
                        landList.add(new Element(i, j));
                }
            }

            int ans = 0;
            for(Element elem : landList){
                if(isVisited[elem.row][elem.col])
                    continue;
                ans++;
                BFS(elem.row, elem.col);
            }

            sb.append(ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(int row, int col){
        isVisited[row][col] = true;
        queue.clear();
        queue.offer(new Element(row, col));

        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir = 0; dir < 8; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= H || ny >= W)
                    continue;

                if(isVisited[nx][ny] || map[nx][ny] == 0)
                    continue;

                isVisited[nx][ny] = true;
                queue.offer(new Element(nx, ny));
            }
        }
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
