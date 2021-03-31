package Backjoon.BOJ16920;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, P;
    static char[][] map;
    static Queue<Element>[] playerQueue;
    static Queue<Element> queue = new LinkedList<>();
    static int[] countOfArea;
    static int[] distanceOfPlayer;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        playerQueue = new Queue[P+1];
        map = new char[N][M];
        countOfArea = new int[P+1];
        distanceOfPlayer = new int[P+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=P; i++)
            distanceOfPlayer[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++)
            map[i] = br.readLine().toCharArray();
        for(int i=1; i<=P; i++)
            playerQueue[i] = new LinkedList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == '.' || map[i][j] == '#') {
                    continue;
                }

                int playNumber = map[i][j] - '0';
                countOfArea[playNumber] += 1;

                for(int dir=0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;

                    if(map[nx][ny] == '.'){
                        playerQueue[playNumber].add(new Element(i, j, 0));
                        break;
                    }
                }
            }
        }

        while(true){
            int sum = 0;
            for(int i=1; i<=P; i++)
                sum += BFS(i);

            if(sum == 0)
                break;
        }

        for(int i=1; i<=P; i++)
            sb.append(countOfArea[i] + " ");


        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int BFS(int playerNumber){
        queue.clear();
        queue.addAll(playerQueue[playerNumber]);
        playerQueue[playerNumber].clear();
        int count = 0;
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;

                if(map[nx][ny] == '.'){
                    map[nx][ny] = (char)('0' + playerNumber);
                    countOfArea[playerNumber]++;
                    if(elem.dist + 1 == distanceOfPlayer[playerNumber]){
                        playerQueue[playerNumber].add(new Element(nx, ny, 0));
                        count++;
                    } else {
                        queue.offer(new Element(nx ,ny, elem.dist + 1));

                    }
                }
            }
        }
        return count;
    }

    static class Element{
        int row;
        int col;
        int dist;

        public Element(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}
