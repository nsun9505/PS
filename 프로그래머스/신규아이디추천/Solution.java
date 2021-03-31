package Programmers.신규아이디추천;

class Solution {
    public String solution(String new_id) {

        // 1단계
        new_id = new_id.toLowerCase();
        System.out.println("1 : " + new_id);

        // 2단계
        String tmp = "";
        for(int i=0; i<new_id.length(); i++){
            char ch = new_id.charAt(i);
            if((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9') ||
                    ch == '.' || ch == '-' || ch == '_')
                tmp += String.valueOf(ch);
        }
        new_id = tmp;
        System.out.println("2 : " + new_id);

        // 3단계
        new_id = new_id.replaceAll("\\.{2,}", ".");
        System.out.println("3 : " + new_id);
        // 4단계
        new_id = new_id.replaceAll("\\.$", "");
        new_id = new_id.replaceAll("^\\.", "");
        System.out.println("4 : " + new_id);

        // 5단계
        if(new_id.length() == 0)
            new_id = "a";
        System.out.println("5 : " + new_id);
        
        // 6단계
        if(new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("\\.$", "");
        }

        // 7단계
        while(new_id.length() < 3){
            new_id += new_id.substring(new_id.length()-1);
        }

        return new_id;
    }
}