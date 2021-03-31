package Backjoon.BOJ16932;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Element> zeroList = new ArrayList<>();
    static Queue<Element> queue = new LinkedList<>();
    static ArrayList<Element> oneList = new ArrayList<>();
    static HashMap<Element, Integer> areaMap = new HashMap<>();
    static HashSet<Element> areaSet = new HashSet<>();

    static int N, M;
    static int[][] map;
    static Element[][] parent;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        parent = new Element[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    zeroList.add(new Element(i, j));
                else
                    oneList.add(new Element(i, j));
            }
        }

        for(Element start : oneList){
            if(parent[start.row][start.col] != null)
                continue;
            BFS(start);
        }


        int answer = 0;
        for(Element start : zeroList){
            areaSet.clear();
            for(int dir=0; dir<4; dir++){
                int nx = start.row + dx[dir];
                int ny = start.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(map[nx][ny] == 0)
                    continue;

                areaSet.add(parent[nx][ny]);
            }

            int sum = 1;
            for(Element elem : areaSet)
                sum += areaMap.get(elem);

            answer = Math.max(answer, sum);
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void BFS(Element start){
        queue.offer(start);
        parent[start.row][start.col] = start;
        int cnt = 0;

        while(!queue.isEmpty()){
            Element cur = queue.poll();
            cnt++;

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(map[nx][ny] == 0)
                    continue;

                if(parent[nx][ny] == null) {
                    parent[nx][ny] = start;
                    queue.offer(new Element(nx, ny));
                }
            }
        }

        areaMap.put(start, cnt);
    }

    static class Element{
        int row;
        int col;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return row == element.row &&
                    col == element.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
