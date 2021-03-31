package Backjoon.BOJ9205;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static long[][] map = new long[102][102];
    static ArrayList<Position> positions = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            int size = N + 2;
            positions.clear();
            for(int i=0; i<size; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) + 32768;
                int y = Integer.parseInt(st.nextToken()) + 32768;

                positions.add(new Position(x, y));
            }

            for(int i=0; i<size; i++){
                Position cur = positions.get(i);
                for(int j=0; j<size; j++){
                    map[i][j] = Integer.MAX_VALUE;
                    if(i == j){
                        map[i][j] = 0;
                        continue;
                    }
                    Position next = positions.get(j);
                    int dist = Math.abs(cur.x - next.x) + Math.abs(cur.y - next.y);
                    if(dist > 1000)
                        continue;
                    map[i][j] = dist;
                }
            }

            for(int k=0; k<size; k++){
                for(int i=0; i<size; i++){
                    if(i == k)
                        continue;

                    for(int j=0; j<size; j++){
                        if(i == j || j == k)
                            continue;

                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            String answer = "happy\n";
            if(map[0][size-1] == Integer.MAX_VALUE)
                answer = "sad\n";
            sb.append(answer);
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
