package Backjoon.BOJ1202;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Jewelry> jewelries = new ArrayList<>();
        ArrayList<Integer> packs = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jewelries.add(new Jewelry(w, v));
        }

        for(int i=0; i<K; i++)
            packs.add(Integer.parseInt(br.readLine()));

        /*
        * 둘 다 무게를 기준으로 정렬하는 이유
        * 가방에 담을 수 있는 것 까지 모두 담습니다.
        * 만약 i번째 가방에 못 담는다면, i+1 이후 가방에 담을 수 있습니다.
        * 또한, i번째 가방에 담을 수 있는 보섯은 i+1 이후 가방에도 담을 수 있습니다.
        * 담을 수 있는 것이 다 담긴 우선순위 큐에는 그 중 가장 값어치가 큰 것을 i번째 가방에 넣으면 됩니다.
        * 그래서 현재 가방에 담길 수 있는 것들은 모두 담기 위해 무게를 기준으로 둘 다 정렬한느 것입니다.
         */
        // 가방을 무게에 대해서 오름차순 정렬
        Collections.sort(packs);
        // 보석 무게를 기준으로 정렬
        Collections.sort(jewelries);

        // 현재까지 담은 보석들 중 가장 무거운 것을 가지도록 MAX Heap 선언
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long answer = 0;
        int jewelryIdx = 0;
        for(int i=0; i<K; i++){

            // 현재 가방에 담길 수 있는거 다 담음, 담긴 보석들은 이후 가방들에도 담을 수 있음.
            // 보석에 대해서 1번씩만 본다. 두 번 이상 보지 않음!
            while(jewelryIdx < N && jewelries.get(jewelryIdx).weight <= packs.get(i))
                pq.offer(jewelries.get(jewelryIdx++).value);

            // 거기서 제일 큰 것을 추가
            // 현재 가방에 담을 수 있는 것들 중(이전에 가방에도 담을 수 있는 것들도 함해서)
            // 가장 값어치가 큰 것을 답에 추가
            if(!pq.isEmpty())
                answer += (long)pq.poll();
        }
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Jewelry implements Comparable<Jewelry>{
        int weight;
        int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            return this.weight - o.weight;
        }
    }
}
