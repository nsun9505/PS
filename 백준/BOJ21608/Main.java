package Backjoon.BOJ21608;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[][] favorite;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static PriorityQueue<Seat> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        favorite = new int[N*N+1][4];
        for(int i=0; i<N*N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            for(int idx=0; idx<4; idx++)
                favorite[index][idx] = Integer.parseInt(st.nextToken());

            findSeat(index);
        }

        sb.append(calc());

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void findSeat(int index){
        pq.clear();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] > 0)
                    continue;

                int cnt = 0;
                int empty = 0;
                for(int dir=0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    if(map[nx][ny] == 0)
                        empty++;
                    else{
                        for(int idx=0; idx<4; idx++){
                            if(map[nx][ny] == favorite[index][idx]){
                                cnt++;
                                break;
                            }
                        }
                    }
                }

                pq.offer(new Seat(i, j, cnt, empty));
            }
        }

        Seat seat = pq.poll();
        map[seat.row][seat.col] = index;
    }

    public static int calc(){
        int result = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                int cnt = 0;
                int index = map[i][j];
                for(int dir=0; dir<4;dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                        continue;

                    for(int idx=0; idx<4; idx++){
                        if(map[nx][ny] == favorite[index][idx]){
                            cnt++;
                            break;
                        }
                    }
                }

                if(cnt == 1) result += 1;
                else if(cnt == 2) result += 10;
                else if(cnt == 3) result += 100;
                else if(cnt == 4) result += 1000;
            }
        }
        return result;
    }

    static class Seat implements Comparable<Seat>{
        int row;
        int col;
        int cnt;
        int empty;

        public Seat(int row, int col, int cnt, int empty) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.empty = empty;
        }

        @Override
        public int compareTo(Seat o) {
            if(this.cnt < o.cnt)
                return 1;
            else if(this.cnt == o.cnt){
                if(this.empty < o.empty)
                    return 1;
                else if(this.empty == o.empty){
                    if(this.row > o.row)
                        return 1;
                    else if(this.row == o.row){
                        return this.col - o.col;
                    }
                }
            }
            return -1;
        }
    }
}
