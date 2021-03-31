package SWEA.SWEA3289;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int[] parent;
    static int[] rank;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            answer.setLength(0);
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            parent = new int[N+1];
            rank = new int[N+1];
            for(int i=1; i<=N; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(cmd == 0)
                    union(a, b);
                else{
                    a = find(a);
                    b = find(b);

                    if(a == b) answer.append("1");
                    else answer.append("0");
                }
            }

            sb.append("#" + t + " " + answer.toString() + "\n");

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

        if(x > y){
            int tmp = x;
            x = y;
            y = tmp;
        }

        if(rank[x] >= rank[y]){
            rank[x] += rank[y];
            parent[y] = x;
        } else {
            rank[y] += rank[x];
            parent[x] = y;
        }
    }
}
