package Backjoon.BOJ14889;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }


        ArrayList<Integer> startTeam = new ArrayList<>();
        ArrayList<Integer> linkTeam = new ArrayList<>();
        int ans = Integer.MAX_VALUE;
        for(int i=1; i<(1<<N); i++){
            startTeam.clear();
            linkTeam.clear();
            for(int j=0; j<N; j++){
                if((i & (1 << j)) == 0) linkTeam.add(j);
                else startTeam.add(j);
            }

            if(startTeam.size() == N/2 && linkTeam.size() == N/2){
                int startSum = getSum(startTeam);
                int linkSum = getSum(linkTeam);
                ans = Math.min(ans, Math.abs(startSum - linkSum));
            }
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int getSum(ArrayList<Integer> team){
        int sum = 0;
        for(int i=0; i<N/2; i++){
            for(int j=i+1; j<N/2; j++){
                sum += map[team.get(i)][team.get(j)] + map[team.get(j)][team.get(i)];
            }
        }
        return sum;
    }
}
