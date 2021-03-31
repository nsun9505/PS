package Backjoon.BOJ1759;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2 {
    static int L, C;
    static char[] arr;
    static StringBuilder stringBuilder = new StringBuilder();
    static StringBuilder sb = new StringBuilder();
    static boolean[] isSelect;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        isSelect = new boolean[C];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<C; i++)
            arr[i] = st.nextToken().charAt(0);

        // 사전식으로 출력하기 위해 정렬
        Arrays.sort(arr);

        // 조합을 담을 배열 생성
        char[] answer = new char[L];

        // 조합돌리기!!
        combination(0, 0, answer, 0, 0);

        // 정답 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    /*
    * depth : 입력 받은 C개의 문자 중 하나를 가리키는 index
    * index : L개의 조합을 만들 때 0~L 중의 index
    * answer : 조합을 담을 배열
    * moeumCnt : 모음 몇개를 사용했는지 카운트
    * jaeumCnt : 자음 몇개를 사용했는지 카운트
     */
    public static void combination(int depth, int index, char[] answer, int moeumCnt, int jaeumCnt){
        // L개의 조합을 만들었다면
        if(index == L){

            // 하지만, 자음 수가 2개가 안 되거나 모음 수가 1개가 안 되는 경우 볼 필요 없음.
            if(jaeumCnt < 2 || moeumCnt < 1)
                return;

            stringBuilder.setLength(0);
            // char[]이니깐 String으로 바꾸기 위해 StringBuilder에 담기
            for(int i=0; i<L; i++)
                stringBuilder.append(answer[i]);
            // 답으로 출력할 sb에 추가
            // 따로 정렬할 필요가 없는 이유는 이미 arr[]을 알파벳에 따라 오름차순 정렬하였고
            // 조합이므로 문제에서 원하는 abc만 나옴, bac같은 것은 안 나옴!
            sb.append(stringBuilder.toString() + "\n");
            return;
        }

        // 범위를 넘어서면 백!
        if(depth >= C)
            return;

        // 현재 조합의 위치에 담기
        answer[index] = arr[depth];

        // 모음이면 모음 카운트 증가
        if(arr[depth] == 'a' || arr[depth] == 'e' || arr[depth] == 'i' || arr[depth] == 'o' || arr[depth] == 'u')
            combination(depth + 1, index+1, answer, moeumCnt+1, jaeumCnt);
        // 자음이면 자음 카운트 증가
        else
            combination(depth+1, index+1, answer, moeumCnt, jaeumCnt+1);
        // 이걸 포함하지 않을 경우
        combination(depth+1, index, answer, moeumCnt, jaeumCnt);
    }
}
