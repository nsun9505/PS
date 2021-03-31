package Programmers.스킬트리;

import java.util.HashSet;

public class 스킬트리 {
    static HashSet<Character> isSkill = new HashSet<>();
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for(int i=0; i<skill.length(); i++)
            isSkill.add(skill.charAt(i));

        for(String sk : skill_trees){
            String ret = "";
            for(int i = 0; i<sk.length(); i++){
                if(isSkill.contains(sk.charAt(i)))
                    ret += String.valueOf(sk.charAt(i));
            }

            if(ret.length() == 0){
                answer++;
                continue;
            }

            for(int i=1; i<=skill.length(); i++){
                String tmp = skill.substring(0, i);
                if(tmp.equals(ret)){
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String str = "123";
        System.out.println("str = " + str.lastIndexOf('4'));
    }
}

/*
CBD [CAD] 0
CBD [AEF, ZJW] 2
REA [REA, POA] 1
CBDK [CB, CXYB, BD, AECD, ABC, AEX, CDB, CBKD, IJCB, LMDK] 4
BDC [AAAABACA] 0
CBD [C, D, CB, BDA] 2
*/
