package Backjoon.BOJ2933;

import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static boolean[][] isVisited;
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        isVisited = new boolean[R][C];
        map = new char[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0;j<input.length(); j++)
                map[i][j] = input.charAt(j);
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            int row = R - Integer.parseInt(st.nextToken());
            int col = -1;
            int dir = 1;
            if(i % 2 == 0) {
                col = C;
                dir = 3;
            }

            // 미네랄 뿌시기
            boolean isBreak = false;
            while(true){
                int ny = col + dy[dir];

                if(ny < 0 || ny >= C)
                    break;

                if(map[row][ny] == 'x'){
                    isBreak = true;
                    map[row][ny] = '.';
                    col = ny;
                    break;
                }
                col = ny;
            }

            if(!isBreak)
                continue;

            ArrayList<Element> cluster = new ArrayList<>();
            for(int d = 0; d < 4; d++){
                cluster.clear();
                int nx = row + dx[d];
                int ny = col + dy[d];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;

                if(map[nx][ny] == '.')
                    continue;

                boolean ret = BFS(new Element(nx, ny), cluster);
                if(ret)
                    continue;

                int moveCnt = Integer.MAX_VALUE;
                for(Element elem : cluster){
                    int before = elem.row;
                    int cur = elem.row;
                    while(true){
                        int next = cur + dx[2];
                        if(next >= R)
                            break;

                        if(isVisited[next][elem.col]){
                            before = -1;
                            break;
                        }
                        else if(map[next][elem.col] == 'x'){
                            break;
                        }

                        cur = next;
                    }
                    if(before == -1)
                        continue;

                    moveCnt = Math.min(moveCnt, (cur - before));

                }

                for(Element elem : cluster)
                    map[elem.row][elem.col] = '.';

                for(Element elem : cluster)
                    map[elem.row + (dx[2] * moveCnt)][elem.col] = 'x';
            }

        }

        for(int i = 0; i<R; i++){
            for(int j=0; j<C; j++)
                sb.append(map[i][j]);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void print(){
        for(int i = 0; i<R; i++){
            for(int j=0; j<C; j++)
                System.out.print(map[i][j]);
            System.out.println();
        }
    }

    public static boolean BFS(Element start, ArrayList<Element> cluster){
        queue.clear();
        for(int i=0; i<R; i++)
            Arrays.fill(isVisited[i], false);

        isVisited[start.row][start.col] = true;
        cluster.add(start);
        queue.offer(start);
        boolean ret = false;
        while(!queue.isEmpty()){
            Element cur = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;

                if(isVisited[nx][ny] || map[nx][ny] == '.')
                    continue;

                if(nx == R-1)
                    ret = true;

                isVisited[nx][ny] = true;
                Element next = new Element(nx, ny);
                queue.offer(new Element(nx, ny));
                cluster.add(next);
            }
        }
        return ret;
    }

    static class Element {
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
