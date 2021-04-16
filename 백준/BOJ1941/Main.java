package Backjoon.BOJ1941;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static Element[] elements = new Element[25];
    static boolean[] used = new boolean[25];
    static boolean[][] visited = new boolean[5][5];
    static Queue<Position> queue = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();


        int index = 0;
        for(int i=0; i<5; i++){
            String input = br.readLine();
            for(int j=0; j<5; j++){
                elements[index] = new Element(index, i, j, input.charAt(j));
                index++;
            }
        }

        solution(0, 0, 0, 0);
        sb.append(answer);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int cnt, int index, int cntOfSom, int cntOfYeon){
        if(cnt == 7){
            if(cntOfSom < 4) return;
            if(BFS()) answer++;

            return;
        }

        if(index >= 25)
            return;
        used[index] = true;
        if(elements[index].belong == 'S')
            solution(cnt+1,index+1, cntOfSom+1, cntOfYeon);
        else
            solution(cnt+1,index+1, cntOfSom, cntOfYeon+1);
        used[index] = false;
        solution(cnt, index+1, cntOfSom, cntOfYeon);
    }

    public static boolean BFS(){
        queue.clear();
        for(int i=0; i<5; i++)
            Arrays.fill(visited[i], false);

        for(int i=0; i<25; i++) {
            if (used[i]){
                queue.offer(new Position(elements[i].row, elements[i].col));
                visited[elements[i].row][elements[i].col] = true;
                break;
            }
        }

        int cnt = 0;
        while(!queue.isEmpty()){
            Position pos = queue.poll();
            cnt++;

            for(int dir=0; dir<4; dir++){
                int nx = pos.row + dx[dir];
                int ny = pos.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5)
                    continue;

                if(visited[nx][ny])
                    continue;

                int index = (5*nx) + ny;
                if(!used[index])
                    continue;

                queue.offer(new Position(nx, ny));
                visited[nx][ny] = true;
            }
        }
        if(cnt == 7)
            return true;
        return false;
    }

    static class Element{
        int index;
        int row;
        int col;
        char belong;

        public Element(int index, int row, int col, char belong) {
            this.index = index;
            this.row = row;
            this.col = col;
            this.belong = belong;
        }
    }
    static class Position{
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
