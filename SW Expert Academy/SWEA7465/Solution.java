package SWEA.SWEA7465;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            parent = new int[N+1];
            rank = new int[N+1];

            for(int i=1; i<=N; i++){
                parent[i] = i;
                rank[i] = 1;
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            set.clear();
            for(int i=1; i<=N; i++)
                set.add(find(i));

            sb.append("#" + t + " " + set.size() + " \n");
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

        if(rank[x] >= rank[y]){
            parent[y] = x;
            rank[x] += rank[y];
        } else {
            parent[x] = y;
            rank[y] += rank[x];
        }
    }
}
