package Backjoon.BOJ16137;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] isVisited;
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        isVisited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 0 && checkCross(i, j)){
                    map[i][j] = M;
                    int ret = BFS();
                    if(ret != -1)
                        ans = Math.min(ans, ret);
                    map[i][j] = 0;
                }
            }
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(){
        int ret = Integer.MAX_VALUE;
        for(int i=0; i<N; i++)
            Arrays.fill(isVisited[i], false);
        isVisited[0][0] = true;
        queue.clear();
        queue.offer(new Element(0, 0, 0));

        while(!queue.isEmpty()){
            Element cur = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(isVisited[nx][ny] || map[nx][ny] == 0)
                    continue;

                if(nx == N-1 && ny == N-1){
                    ret = Math.min(cur.dist+1, ret);
                    continue;
                }

                // 땅으로 가는 경우 바로 가기
                if(map[nx][ny] == 1){
                    isVisited[nx][ny] = true;
                    queue.offer(new Element(nx, ny, cur.dist + 1));
                }
                // 땅이 아니라 오작교로 가는 경우
                else if(map[cur.row][cur.col] == 1 && map[nx][ny] >= 2){
                    // 이동 시간이 해당 오작교의 주기와 동일하다면
                    // 다음에 이동할 시간이므로 cur.dist + 1이
                    // map[nx][ny]의 배수라면 해당 오작교로 이동할 수 있음을 의미
                    if((cur.dist + 1) % map[nx][ny] == 0) {
                        isVisited[nx][ny] = true;
                        queue.offer(new Element(nx, ny, cur.dist + 1));
                    }
                    else // 현재 위치를 큐에 넣음으로써 기다린다! 대신 시간은 늘린다.
                        queue.offer(new Element(cur.row, cur.col, cur.dist + 1));
                }
            }
        }

        return ret;
    }

    public static boolean checkCross(int row, int col){
        // 가로 방향에 절벽이 있는지
        boolean garo = false;
        if(col-1 >= 0 && map[row][col-1] == 0) garo = true;
        if(col+1 < N && map[row][col+1] == 0) garo = true;
        
        // 세로 방향에 절벽이 있는지
        boolean sero = false;
        if(row-1 >= 0 && map[row-1][col] == 0) sero = true;
        if(row+1 < N && map[row+1][col] == 0) sero = true;

        // 가로 세로 모두 절벽이 있는 경우 true 리턴
        // (좌, 상), (상, 우), (우, 하), (하, 우)
        return !(garo && sero);
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
