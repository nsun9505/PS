package Backjoon.BOJ1244;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] arr = new char[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++)
            arr[i] = st.nextToken().charAt(0);

        int M = Integer.parseInt(br.readLine());
        for(int idx=0; idx<M; idx++){
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int switchNum = Integer.parseInt(st.nextToken());

            if(sex == 1){
                for(int i=switchNum; i<=N; i+=switchNum)
                    arr[i] = arr[i] == '0' ? '1' : '0';
            } else {
                int left = switchNum;
                int right = switchNum;
                while(true){
                    if(left <= 0 || right > N)
                        break;
                    if(arr[left] != arr[right])
                        break;

                    char change = arr[left] == '0' ? '1' : '0';
                    arr[left] = change;
                    arr[right] = change;

                    left -= 1;
                    right += 1;
                }
            }
        }

        int cnt = 0;
        for(int i=1; i<=N; i++) {
            System.out.print(arr[i] + " ");cnt++;
            if(cnt % 20 == 0)
                System.out.println();
        }
    }
}
