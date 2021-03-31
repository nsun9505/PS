package Backjoon.BOJ16236;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        StringTokenizer st = null;

        int sharkRow = 0;
        int sharkCol = 0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    sharkRow = i;
                    sharkCol = j;
                }
            }
        }

        Shark shark = new Shark(sharkRow, sharkCol, 2, 0);
        int answer = 0;
        while(true) {
            int sec = BFS(shark);
            if(sec == 0)
                break;
            answer += sec;
        }
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(Shark shark){
        queue.clear();
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);

        queue.offer(new Element(shark.row, shark.col, 0));
        visited[shark.row][shark.col] = true;
        int minDist = Integer.MAX_VALUE;
        int minDistRow = N;
        int minDistCol = N;

        while(!queue.isEmpty()){
            Element cur = queue.poll();

            if(cur.dist > minDist)
                break;

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(visited[nx][ny] || map[nx][ny] > shark.size)
                    continue;

                if(map[nx][ny] == 0 || map[nx][ny] == shark.size){
                    queue.offer(new Element(nx, ny, cur.dist+1));
                    visited[nx][ny] = true;
                }
                else if(map[nx][ny] < shark.size){
                    if(cur.dist + 1 < minDist){
                        minDist = cur.dist + 1;
                        minDistRow = nx;
                        minDistCol = ny;
                    } else if(cur.dist + 1 == minDist){
                        if(nx < minDistRow){
                            minDistRow = nx;
                            minDistCol = ny;
                        } else if(nx == minDistRow){
                            if(ny < minDistCol){
                                minDistCol = ny;
                            }
                        }
                    }
                }
            }
        }


        if(minDist == Integer.MAX_VALUE)
            return 0;

        shark.cntOfEat++;
        if(shark.cntOfEat == shark.size){
            shark.size++;
            shark.cntOfEat = 0;
        }

        map[shark.row][shark.col] = 0;
        map[minDistRow][minDistCol] = 0;
        shark.row = minDistRow;
        shark.col = minDistCol;

        return minDist;
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

    static class Shark{
        int row;
        int col;
        int size;
        int cntOfEat;

        public Shark(int row, int col, int size, int cntOfEat) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.cntOfEat = cntOfEat;
        }
    }
}
