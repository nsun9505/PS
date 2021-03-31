package Backjoon.BOJ1764;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String[] noListen = new String[N];
        String[] nosee = new String[M];
        for(int i=0; i<N; i++)
            noListen[i] = br.readLine();
        for(int i=0; i<M; i++)
            nosee[i] = br.readLine();

        Arrays.sort(noListen);
        ArrayList<String> list = new ArrayList<>();
        for(String target : nosee){

            int left = 0;
            int right = noListen.length - 1;
            while(left <= right){
                int mid = (left + right)/2;
                if(noListen[mid].equals(target)){
                    list.add(target);
                    break;
                }

                // noListen[mid]가 target보다 뒤에 나온다는 뜻
                // 즉, mid에 b가 있고 target은 a
                // 그래서 right를 mid로 줄여야 함.
                if(noListen[mid].compareTo(target) > 0){
                    right = mid - 1;
                }
                // noListen[mid]가 target 보다 먼저 나온다.
                // 그래서 left를 mid로 줄여야 함.
                else {
                    left = mid + 1;
                }
            }
        }
        // list에 정렬되지 않은 채로 저장
        // 그러므로 리스트를 정렬하고 뽑으면 끝!
        System.out.println(list.size());
        Collections.sort(list);
        for(String ans : list)
            System.out.println(ans);
    }
}