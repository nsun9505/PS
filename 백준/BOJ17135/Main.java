package Backjoon.BOJ17135;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Enemy> enemies = new ArrayList<>();
    static int N, M, D;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                if(st.nextToken().charAt(0) == '1')
                    enemies.add(new Enemy(i, j, false));
            }
        }

        // 고정적으로 3자리만 고르면 되기 때문에 3중포문 - 조합
        for(int i=0; i<M; i++){
            for(int j=i+1; j<M; j++){
                for(int k=j+1; k<M; k++){
                    int ret = 0;
                    // 각 {i, j, k}마다 서로 다른 map을 가지고 있어야 함.
                    ArrayList<Enemy> tmp = new ArrayList<>();
                    for(Enemy enemy : enemies)
                        tmp.add(new Enemy(enemy));

                    // 적이 다 없어질 때까지
                    while(!tmp.isEmpty()) {
                        // i, j, k번째에서 적을 죽이기
                        ret += killEnemy(i, tmp);
                        ret += killEnemy(j, tmp);
                        ret += killEnemy(k, tmp);

                        // 적들 이동
                        moveEnemy(tmp);
                    }

                    ans = Math.max(ret, ans);
                }
            }
        }

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static int killEnemy(int archerCol, ArrayList<Enemy> enemies){
        Enemy die = new Enemy(N, M, true);
        int min = D;
        int dieIdx = -1;
        for(int idx = 0; idx < enemies.size(); idx++){
            // 적과 궁수와의 거리 계산
            int dist = Math.abs(N - enemies.get(idx).row) + Math.abs(archerCol - enemies.get(idx).col);

            // 거리가 min보다 작으면
            if(dist < min){
                // 해당 위치 및 거리로 갱신
                min = dist;
                dieIdx = idx;
                die.col = enemies.get(idx).col;
                // 거리가 같다면
            } else if(min == dist && enemies.get(idx).col < die.col) {
                // min값은 그대로 두고 죽일 적의 위치만 갱신
                dieIdx = idx;
                die.col = enemies.get(idx).col;
            }
        }

        // -1이 아니라면 죽일 적이 있음.
        if(dieIdx != -1) {
            // 이미 누가 죽인 적이라면 return 0
            if(enemies.get(dieIdx).isDead)
                return 0;
            // 내가 처음으로 죽이면 isDead = true로 변경하고 return 1
            enemies.get(dieIdx).isDead = true;
            return 1;
        }
        // 아무도 죽이지 못한 경우 return 0;
        return 0;
    }

    // 적들이 이동
    public static void moveEnemy(ArrayList<Enemy> enemies){
        ArrayList<Enemy> tmp = new ArrayList<>();
        
        for(Enemy enemy : enemies){
            // 아래로 한 칸씩 이동
            enemy.row += 1;
            // 성에 도착했다면 죽었다고 표시
            if(enemy.row == N)
                enemy.isDead = true;
            // 아직 성에 도달하지 않았다면 다음 라운드에 포함시키기 위해 tmp에 저장
            // tmp에는 죽지 않았고, 성에 도착하지 않은 적들만 남아있음.
            if(!enemy.isDead)
                tmp.add(enemy);
        }
        enemies.clear();
        for(Enemy enemy : tmp)
            enemies.add(enemy);
    }

    static class Enemy{
        int row;
        int col;
        boolean isDead;

        public Enemy(int row, int col, boolean isDead) {
            this.row = row;
            this.col = col;
            this.isDead = isDead;
        }

        public Enemy(Enemy e) {
            this.row = e.row;
            this.col = e.col;
            this.isDead = e.isDead;
        }
    }
}
