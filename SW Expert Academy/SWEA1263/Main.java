package SWEA.SWEA1263;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[][] map = new int[1000][1000];
        long[][] dist = new long[1000][1000];
        for(int t=1; t<=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(i == j) dist[i][j] = 0;
                    else dist[i][j] = map[i][j] == 1 ? 1 : Integer.MAX_VALUE;
                }
            }

            for(int k=0; k<N; k++){
                for(int i=0; i<N; i++){
                    if(i == k)
                        continue;
                    for(int j=0; j<N; j++){
                        if(i == j || j == k)
                            continue;

                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }


            long answer = Integer.MAX_VALUE;
            for(int i=0; i<N; i++){
                long sum = 0;
                for(int j=0; j<N; j++)
                    sum += dist[i][j];
                answer = Math.min(answer, sum);
            }


            sb.append("#" + t + " " + answer + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
