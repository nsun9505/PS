package Backjoon.BOJ21609;

import java.io.*;
import java.util.*;

public class Main {
    static int EMPTY = -2;
    static int STONE = -1;
    static int RAINBOW = 0;
    static int[][] map;
    static int[][] tmp;
    static boolean[][] visited;
    static int N, M;
    static Queue<Element> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Block> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tmp = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while(true){
            int result = findBlock();
            if(result == 0)
                break;
            answer += result;

            moveDown();

            rotate();

            moveDown();
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int findBlock(){
        int result = 0;

        list.clear();
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == EMPTY || map[i][j] == STONE || map[i][j] == RAINBOW)
                    continue;

                if(visited[i][j])
                    continue;

                BFS(i, j);
            }
        }

        if(list.size() == 0)
            return 0;

        Collections.sort(list);
        Block block = list.get(0);
        removeBlock(block.row, block.col);
        result = block.size * block.size;

        return result;
    }

    public static void BFS(int row, int col){
        queue.clear();
        visited[row][col] = true;
        queue.offer(new Element(row, col));
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == RAINBOW)
                    visited[i][j] = false;
            }
        }

        int size = 0;
        int rainbow = 0;
        int color = map[row][col];
        while(!queue.isEmpty()){
            Element elem = queue.poll();
            size++;
            if(map[elem.row][elem.col] == color){
                if(elem.row < row){
                    row = elem.row;
                } else if(elem.row == row){
                    if(elem.col < col){
                        col = elem.col;
                    }
                }
            }

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(visited[nx][ny])
                    continue;

                if(map[nx][ny] == color || map[nx][ny] == RAINBOW){
                    if(map[nx][ny] == RAINBOW)
                        rainbow++;

                    visited[nx][ny] = true;
                    queue.offer(new Element(nx, ny));
                }
            }
        }
        if(size > 1)
            list.add(new Block(row, col, size, rainbow));
    }

    public static void removeBlock(int row, int col){
        for(int i=0; i<N; i++)
            Arrays.fill(visited[i], false);
        visited[row][col] = true;
        queue.offer(new Element(row, col));
        int color = map[row][col];
        while(!queue.isEmpty()){
            Element elem = queue.poll();
            map[elem.row][elem.col] = EMPTY;

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if(visited[nx][ny])
                    continue;

                if(map[nx][ny] == color || map[nx][ny] == RAINBOW){
                    visited[nx][ny] = true;
                    queue.offer(new Element(nx, ny));
                }
            }
        }
    }

    public static void rotate(){
        for(int i=0, col=N-1; i<N; i++, col--){
            for(int j=0, row=0; j<N; j++, row++){
                tmp[i][j] = map[row][col];
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = tmp[i][j];
            }
        }
    }

    public static void moveDown(){
        for(int i=N-1; i>=0; i--){
            for(int j=0; j<N; j++){
                if(map[i][j] == EMPTY || map[i][j] == STONE)
                    continue;

                int row = i;
                int color = map[i][j];
                map[i][j] = EMPTY;
                while(true){
                    int nx = row + 1;
                    if(nx >= N)
                        break;

                    if(map[nx][j] != EMPTY)
                        break;

                    row = nx;
                }

                map[row][j] = color;
            }
        }
    }

    public static void print(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.printf("%3d", map[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

    static class Block implements Comparable<Block>{
        int row;
        int col;
        int size;
        int rainbow;

        public Block(int row, int col, int size, int rainbow) {
            this.row = row;
            this.col = col;
            this.size = size;
            this.rainbow = rainbow;
        }

        @Override
        public int compareTo(Block o) {
            if(this.size < o.size)
                return 1;
            else if(this.size == o.size){
                if(this.rainbow < o.rainbow)
                    return 1;
                else if(this.rainbow == o.rainbow){
                    if(this.row < o.row)
                        return 1;
                    else if(this.row == o.row){
                        if(this.col < o.col)
                            return 1;
                    }
                }
            }
            return -1;
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
