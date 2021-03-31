package Backjoon.BOJ1755;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static String[] intToString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Element> list = new ArrayList<>();
        for(int i=M; i<=N; i++)
            list.add(new Element(convertToString(i), i));

        Collections.sort(list, new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return o1.english.compareTo(o2.english);
            }
        });

        int cnt = 0;
        for(Element elem : list) {
            sb.append(elem.number + " ");
            cnt++;
            if(cnt % 10 == 0)
                sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    public static String convertToString(int number){
        String ret = "";
        while(number > 0){
            ret = intToString[number % 10] + " " + ret;
            number /= 10;
        }
        return ret;
    }

    static class Element{
        String english;
        int number;

        public Element(String english, int number) {
            this.english = english;
            this.number = number;
        }
    }
}
