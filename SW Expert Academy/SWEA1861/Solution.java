package SWEA.SWEA1861;

import java.io.*;
import java.util.*;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            ArrayList<Pos> list = new ArrayList<>();
            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    list.add(new Pos(map[i][j], i, j));
                }
            }

            boolean[][] isVisited = new boolean[N][N];
            Collections.sort(list, new Comparator<Pos>() {
                @Override
                public int compare(Pos o1, Pos o2) {
                    return (o1.val - o2.val);
                }
            });

            int max = 0;
            int maxRoom = 0;
            for(Pos pos : list){
                if(isVisited[pos.row][pos.col])
                    continue;

                int dist = BFS(pos.row, pos.col, isVisited);
                if(max < dist){
                    maxRoom = map[pos.row][pos.col];
                    max = dist;
                }
            }

            sb.append("#" + t + " " + maxRoom + " " + max + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int BFS(int row, int col, boolean[][] isVisited) {
        Queue<Pos> queue = new LinkedList<>();
        isVisited[row][col] = true;
        queue.offer(new Pos(1, row, col));
        int maxDis = 1;
        while(!queue.isEmpty()){
            Pos elem = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(isVisited[nx][ny] || Math.abs(map[nx][ny] - map[elem.row][elem.col]) == 1)
                    continue;

                isVisited[nx][ny] = true;
                queue.offer(new Pos(elem.val + 1, nx, ny));
                maxDis = Math.max(maxDis, elem.val + 1);
            }
        }
        return maxDis;
    }

    static class Pos{
        int val;
        int row;
        int col;

        public Pos(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }
}
