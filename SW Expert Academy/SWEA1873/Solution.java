package SWEA.SWEA1873;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static int H, W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            map = new char[H][W];
            Tank tank = new Tank(0, 0, 0);
            for(int i=0; i<H; i++){
                String input = br.readLine();
                for(int j = 0; j<W; j++) {
                    map[i][j] = input.charAt(j);
                    if(map[i][j] == '<' || map[i][j] == '>' || map[i][j] == 'v' || map[i][j] == '^'){
                        tank.row = i;
                        tank.col = j;
                    }
                }
            }

            tank.dir = shapeToIDir(map[tank.row][tank.col]);
            map[tank.row][tank.col] = '.';

            int cmd = Integer.parseInt(br.readLine());
            String cmds = br.readLine();
            for(int i = 0; i<cmd; i++){
                switch (cmds.charAt(i)){
                    case 'U':
                        move(tank, 0);
                        break;
                    case 'D':
                        move(tank, 2);
                        break;
                    case 'L':
                        move(tank, 3);
                        break;
                    case 'R':
                        move(tank, 1);
                        break;
                    case 'S':
                        shoot(tank);
                }
            }

            map[tank.row][tank.col] = dirToShape(tank.dir);

            sb.append("#" + t + " " + String.valueOf(map[0]) + "\n");
            for(int i=1; i<H; i++)
                sb.append(String.valueOf(map[i]) + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void move(Tank tank, int dir){
        tank.dir = dir;
        int nx = tank.row + dx[tank.dir];
        int ny = tank.col + dy[tank.dir];

        if(nx < 0 || ny < 0 || nx >= H || ny >= W)
            return;

        if(map[nx][ny] != '.')
            return;

        tank.row = nx;
        tank.col = ny;
    }

    public static void shoot(Tank tank){
        int missileRow = tank.row;
        int missileCol = tank.col;

        while(true){
            int nx = missileRow + dx[tank.dir];
            int ny = missileCol + dy[tank.dir];

            if(nx < 0 || ny < 0 || nx >= H || ny >= W)
                break;

            if(map[nx][ny] == '#')
                break;

            if(map[nx][ny] == '*') {
                map[nx][ny] = '.';
                break;
            }

            missileRow = nx;
            missileCol = ny;
        }
    }

    public static int shapeToIDir(char dir){
        if(dir == '<')
            return 3;
        else if(dir == '>')
            return 1;
        else if(dir == '^')
            return 0;
        return 2;
    }

    public static char dirToShape(int dir){
        if(dir == 0)
            return '^';
        else if(dir == 1)
            return '>';
        else if(dir == 2)
            return 'v';
        return '<';
    }

    static class Tank{
        int row;
        int col;
        int dir;

        public Tank(int row, int col, int dir) {
            this.row = row;
            this.col = col;
            this.dir = dir;
        }
    }
}
