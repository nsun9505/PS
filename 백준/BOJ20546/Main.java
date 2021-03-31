package Backjoon.BOJ20546;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int money = Integer.parseInt(br.readLine());

        int jMoney = money;
        int jRet = 0;
        int sMoney = money;
        int sRet = 0;

        int prev = 0;
        int cur = 0;
        int cntUp = 0;
        int cntDown = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=14; i++){
            cur = Integer.parseInt(st.nextToken());

            if(cur <= jMoney){
                jRet += (jMoney / cur);
                jMoney -= (jMoney / cur) * cur;
            }

            if(prev < cur) {
                cntUp++;
                cntDown = 0;
            } else if(prev > cur){
                cntDown++;
                cntUp = 0;
            } else {
                cntDown = 0;
                cntUp = 0;
            }

            prev = cur;
            if(cntUp >= 3){
                sMoney += cur * sRet;
                sRet = 0;
            } else if(cntDown >= 3){
                if(cur <= sMoney){
                    sRet += (sMoney / cur);
                    sMoney -= (sMoney / cur) * cur;
                }
            }
        }

        jMoney = jMoney + (cur * jRet);
        sMoney = sMoney + (cur * sRet);
        if(sMoney < jMoney)
            System.out.println("BNP");
        else if(jMoney < sMoney)
            System.out.println("TIMING");
        else
            System.out.println("SAMESAME");
    }
}
