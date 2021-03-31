package Backjoon.BOJ17472;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static Queue<Element> queue = new LinkedList<>();
    static Queue<Area> areaQueue = new LinkedList<>();
    static ArrayList<Area> areaList = new ArrayList<>();
    static ArrayList<Area>[] graph = new ArrayList[7];
    static PriorityQueue<Edge> pq = new PriorityQueue<>();
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
        visited = new boolean[N][M];
        for(int i=1; i<graph.length; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1)
                    areaList.add(new Area(i, j));
            }
        }

        int index = 1;
        for(Area area : areaList){
            if(!visited[area.row][area.col])
                setArea(index++, area.row, area.col);
            graph[map[area.row][area.col]].add(area);
        }

        int numOfVertex = index - 1;

        for(int i=1; i<=numOfVertex; i++)
            setDistance(i);

        int[] parent = new int[numOfVertex+1];
        for(int i=1; i<=numOfVertex; i++)
            parent[i] = i;

        int numOfEdge = 0;
        int answer = 0;
        while(numOfEdge < numOfVertex - 1){
            if(pq.isEmpty())
                break;
            Edge edge = pq.poll();

            int u = find(parent, edge.v1);
            int v = find(parent, edge.v2);

            if(u == v)
                continue;

            numOfEdge++;
            answer += edge.dist;
            if(u > v) parent[v] = u;
            else parent[u] = v;
        }
        if(numOfEdge != numOfVertex - 1)
            answer = -1;
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int[] parent, int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent, parent[x]);
    }

    public static void setArea(int index, int row, int col){
        areaQueue.offer(new Area(row, col));
        visited[row][col] = true;

        while(!areaQueue.isEmpty()){
            Area area = areaQueue.poll();
            map[area.row][area.col] = index;

            for(int dir=0; dir<4; dir++){
                int nx = area.row + dx[dir];
                int ny = area.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(visited[nx][ny] || map[nx][ny] == 0)
                    continue;

                visited[nx][ny] = true;
                areaQueue.offer(new Area(nx, ny));
            }
        }
    }

    public static void setDistance(int index){
        for(Area area : graph[index]){
            for(int dir=0; dir<4; dir++)
                queue.offer(new Element(area.row, area.col, 0, dir));
        }

        while(!queue.isEmpty()){
            Element elem = queue.poll();
            int nx = elem.row + dx[elem.dir];
            int ny = elem.col + dy[elem.dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                continue;

            if(map[nx][ny] == 0)
                queue.offer(new Element(nx, ny, elem.dist + 1, elem.dir));
            else if(map[nx][ny] != index && map[nx][ny] > 0 && elem.dist >= 2)
                pq.offer(new Edge(index, map[nx][ny], elem.dist));
        }
    }

    static class Area{
        int row;
        int col;

        public Area(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Element{
        int row;
        int col;
        int dist;
        int dir;

        public Element(int row, int col, int dist, int dir) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.dir = dir;
        }
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int dist;

        public Edge(int v1, int v2, int dist) {
            this.v1 = v1;
            this.v2 = v2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return this.dist - o.dist;
        }
    }
}
