package Backjoon.BOJ3197;

import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] days;
    static int[][] maxDays;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static List<Element> list = new ArrayList<>();
    static LinkedList<Element> queue = new LinkedList<>();
    static List<Element> iceList = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        days = new int[R][C];
        visited = new boolean[R][C];
        maxDays = new int[R][C];
        for(int i=0; i<R; i++){
            String input = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = input.charAt(j);
                if(map[i][j] == 'L') {
                    list.add(new Element(i, j));
                    map[i][j] = '.';
                }
            }
        }

        // 물과 닿아 있는 얼음들을 알아낸다.
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j] == '.')
                    continue;
                for(int dir = 0; dir<4; dir++){
                    int nx = i + dx[dir];
                    int ny = j + dy[dir];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C)
                        continue;

                    if(map[nx][ny] == '.'){
                        iceList.add(new Element(i, j));
                        break;
                    }
                }
            }
        }

        // 얼음들의 녹는 날짜를 알아낸다.
        setMeltDay();

        // check() : 백조가 만나는 날을 리턴
        sb.append(check(0));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 얼음이 녹는 날 계산
    public static int setMeltDay(){
        int maxDay = 1;
        for(int i=0; i<R; i++)
            Arrays.fill(visited[i], false);
        queue.clear();
        for(Element elem : iceList){
            queue.offer(elem);
            visited[elem.row][elem.col] = true;
            days[elem.row][elem.col] = 1;
        }

        // 얼음이 언제 녹는지 BFS로 계산
        while(!queue.isEmpty()){
            Element elem = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = elem.row + dx[dir];
                int ny = elem.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;

                if(visited[nx][ny] || map[nx][ny] == '.')
                    continue;

                visited[nx][ny] = true;
                days[nx][ny] = days[elem.row][elem.col] + 1;
                maxDay = Math.max(days[nx][ny], maxDay);
                queue.offer(new Element(nx, ny));
            }
        }
        return maxDay;
    }

    public static int check(int day){
        int minDay = Integer.MAX_VALUE;
        queue.clear();
        for(int i=0; i<R; i++)
            Arrays.fill(visited[i], false);
        queue.offer(new Element(list.get(0).row, list.get(0).col, 0));
        visited[list.get(0).row][list.get(0).col] = true;
        Element end = list.get(1);

        while(!queue.isEmpty()){
            Element cur = queue.poll();

            for(int dir=0; dir<4; dir++){
                int nx = cur.row + dx[dir];
                int ny = cur.col + dy[dir];

                if(nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;
                if(visited[nx][ny])
                    continue;

                if(nx == end.row && ny == end.col){
                    return cur.day;
                }

                visited[nx][ny] = true;
                // 다음 위치가 얼음이 녹는데 오래 걸린다면, 뒤쪽에 넣는다
                if(cur.day < days[nx][ny]){
                    queue.offerLast(new Element(nx, ny, days[nx][ny]));
                }
                // 다음 위치가 얼음이 녹는데 현재 날과 똑같거나 작다면 앞에 넣어서 먼저 방문하도록 한다.
                // 즉, 우선순위를 준다는 말.
                else {
                    queue.offerFirst(new Element(nx, ny, Math.max(cur.day, days[nx][ny])));
                }
            }
        }
        return minDay;
    }

    static class Element{
        int row;
        int col;
        int day;

        public Element(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Element(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
        }
    }
}
