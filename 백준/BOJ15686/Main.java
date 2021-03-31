package Backjoon.BOJ15686;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Pos> homeList = new ArrayList<>();
    static ArrayList<Pos> chickenList = new ArrayList<>();
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                char ch = st.nextToken().charAt(0);

                if(ch == '1')
                    homeList.add(new Pos(i, j));
                else if(ch == '2')
                    chickenList.add(new Pos(i, j));
            }
        }

        solution(0, 0, new ArrayList<>());

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, int cnt, ArrayList<Pos> list){
        if(list.size() == M){
            int ret = 0;
            for(Pos home : homeList){
                int min = Integer.MAX_VALUE;
                for(Pos chicken : list)
                    min = Math.min(min, Math.abs(home.row - chicken.row) + Math.abs(home.col - chicken.col));
                ret += min;
            }
            ans = Math.min(ans, ret);
            return;
        }

        if(idx >= chickenList.size())
            return;

        list.add(chickenList.get(idx));
        solution(idx+1, cnt+1, list);
        list.remove(list.size()-1);
        solution(idx+1, cnt, list);
    }

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
