package Backjoon.BOJ2493;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        Stack<Element> input = new Stack<>();
        Stack<Element> output = new Stack<>();
        for(int i=0; i<N; i++)
            input.push(new Element(i, Integer.parseInt(st.nextToken())));

        output.push(input.pop());

        while(!input.isEmpty()){
            Element elem = input.pop();
            if(elem.val > output.peek().val) {
                while (!output.isEmpty()) {
                    Element top = output.peek();
                    if(top.val < elem.val) {
                        arr[top.idx] = elem.idx + 1;
                        output.pop();
                    }
                    else
                        break;
                }
            }
            output.push(elem);
        }

        for(int i=0; i<arr.length; i++)
            sb.append(arr[i] + " ");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Element{
        int idx;
        int val;

        public Element(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}
