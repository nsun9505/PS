package SWEA.SWEA1251;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static Node[] nodes;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int t=1; t<=T; t++){
            int N = Integer.parseInt(br.readLine());
            parent = new int[N];
            nodes = new Node[N];
            StringTokenizer posX = new StringTokenizer(br.readLine());
            StringTokenizer posY = new StringTokenizer(br.readLine());
            double E = Double.parseDouble(br.readLine());

            for(int i=0; i<N; i++){
                int x = Integer.parseInt(posX.nextToken());
                int y = Integer.parseInt(posY.nextToken());
                nodes[i] = new Node(x, y);
                parent[i] = i;
            }

            pq.clear();
            for(int i=0; i<N; i++){
                for(int j=i+1; j<N; j++){
                    // x2 - x1의 제곱 또는 y2 - y1의 제곱이 int 범위를 넘어설 수 있으므로 주의
                    double distance = Math.pow(nodes[j].x - nodes[i].x, 2) + Math.pow(nodes[j].y - nodes[i].y, 2);
                    double weight = E * distance;
                    pq.offer(new Edge(i, j, weight));
                }
            }

            int numOfNode = 0;
            double answer = 0;
            while(numOfNode < N-1){
                Edge edge = pq.poll();

                int u = find(edge.v1);
                int v = find(edge.v2);

                if(u == v)
                    continue;

                union(u, v);

                numOfNode++;
                answer += edge.weight;
            }

            sb.append("#" + t + " " + (long)Math.round(answer) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int find(int x){
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x == y)
            return;
        if(x > y) parent[x] = y;
        else parent[y] = x;
    }

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        double weight;

        public Edge(int v1, int v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
