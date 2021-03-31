package SWEA.SWEA1247;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
    static int ans = Integer.MAX_VALUE;
    static Position[] customers;
    static Position home;
    static Position company;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<=T; t++){
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            customers = new Position[N+2];
            customers[0] = new Position();
            customers[N+1] = new Position();

            StringTokenizer st = new StringTokenizer(br.readLine());
            customers[0].x = Integer.parseInt(st.nextToken());
            customers[0].y = Integer.parseInt(st.nextToken());
            customers[N+1].x = Integer.parseInt(st.nextToken());
            customers[N+1].y = Integer.parseInt(st.nextToken());

            for(int i=1; i<=N; i++){
                customers[i] = new Position();
                customers[i].x = Integer.parseInt(st.nextToken());
                customers[i].y = Integer.parseInt(st.nextToken());
            }

            perm(1,  0);

            sb.append("#" + t + " " + ans + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void perm(int depth, int sum){
        if(sum >= ans)
            return;

        if(depth == N+1){
            sum += Math.abs(customers[N+1].x - customers[N].x) + Math.abs(customers[N+1].y - customers[N].y);
            ans = Math.min(sum, ans);
            return;
        }

        for(int i=depth; i<=N; i++){
            swap(depth, i);
            int tmp = Math.abs(customers[depth-1].x - customers[depth].x) + Math.abs(customers[depth-1].y - customers[depth].y);
            perm(depth+1, sum + tmp);
            swap(depth, i);
        }
    }

    public static void swap(int x, int y){
        Position tmp = customers[x];
        customers[x] = customers[y];
        customers[y] = tmp;
    }

    static class Position{
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position() {
        }
    }
}
