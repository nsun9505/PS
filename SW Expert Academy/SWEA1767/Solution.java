package SWEA.SWEA1767;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static final int INF = 10000;
    static int[][] map;
    static List<Element> list = new ArrayList<>();
    static int[] cntArr;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        map = new int[12][12];
        cntArr = new int[20];
        for(int t = 1; t<=T; t++){
            N = Integer.parseInt(br.readLine());
            list.clear();

            for(int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1 && i != 0 && i != N-1 && j != 0 && j != N-1)
                        list.add(new Element(i, j));
                }
            }
            Arrays.fill(cntArr, INF);
            solution(0, 0, 0);
            for(int i=list.size(); i>=0; i--) {
                if(cntArr[i] != INF) {
                    sb.append("#" + t + " " + cntArr[i] + "\n");
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static void solution(int idx, int sum, int cnt){
        if(idx >= list.size()){
            cntArr[cnt] = Math.min(cntArr[cnt], sum);
            return;
        }

        Element cur = list.get(idx);
        ArrayList<Element> putlist = new ArrayList<>();
        for(int dir = 0; dir<4; dir++){
            int ret = check(cur.row, cur.col, dir, putlist);
            if(ret != INF)
                solution(idx+1, sum + ret, cnt+1);
            recovery(putlist);
        }
        solution(idx+1, sum, cnt);
    }

    public static int check(int row, int col, int dir, ArrayList<Element> list){
        list.clear();
        boolean isOk = true;
        while(true){
            int nx = row + dx[dir];
            int ny = col + dy[dir];

            if(nx < 0 || ny < 0 || nx >= N || ny >= N)
                break;

            if(map[nx][ny] == 1) {
                isOk = false;
                break;
            }
            map[nx][ny] = 1;
            list.add(new Element(nx, ny));
            row = nx;
            col = ny;
        }

        if(isOk)
            return list.size();
        return INF;
    }

    public static void recovery(ArrayList<Element> putlist){
        for(Element elem : putlist)
            map[elem.row][elem.col] = 0;
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
