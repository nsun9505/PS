package Programmers.KAKAO2019.길찾기게임;

import java.util.*;

public class Solution {
    List<Node> nodes = new ArrayList<>();
    int[][] ans;
    int preOrderIndex = 0;
    int posOrderIndex = 0;
    HashMap<Integer, ArrayList<Node>> levelMap = new HashMap<>();
    public int[][] solution(int[][] nodeinfo) {
        ans = new int[2][nodeinfo.length];

        for(int i=0; i<nodeinfo.length; i++)
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));

        Collections.sort(nodes);
        for(Node node : nodes){
            if(!levelMap.containsKey(node.y))
                levelMap.put(node.y, new ArrayList<Node>());
            ArrayList<Node> list = levelMap.get(node.y);
            list.add(node);
        }

        createTree(nodes.get(0), 0, 100001);

        DFS(nodes.get(0));
        return ans;
    }

    public void createTree(Node cur, int minX, int maxX) {
        ArrayList<Node> list = null;
        for(int level=cur.y-1; level>=0; level--){
            if(levelMap.containsKey(level)){
                list = levelMap.get(level);
                break;
            }
        }

        if(list == null)
            return;

        for(Node child : list){
            if(minX <= child.x && child.x < cur.x){
                cur.leftChild = child;
                createTree(child, minX, cur.x);
                break;
            }
        }

        for(Node child : list){
            if(cur.x < child.x && child.x < maxX){
                cur.rightChild = child;
                createTree(child, cur.x+1, maxX);
            }
        }
    }

    public void DFS(Node node){
        if(node != null){
            ans[0][preOrderIndex++] = node.index;
            DFS(node.leftChild);
            DFS(node.rightChild);
            ans[1][posOrderIndex++] = node.index;
        }
    }

    public boolean isLeftNode(Node curNode, Node targetNode){
        if(curNode.x > targetNode.x)
            return true;
        return false;
    }

    public boolean isRightNode(Node curNode, Node targetNode, int rightMax){
        if(curNode.x < targetNode.x && targetNode.x < rightMax)
            return true;
        return false;
    }

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int index;
        Node leftChild;
        Node rightChild;

        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.leftChild = null;
            this.rightChild = null;
        }

        @Override
        public int compareTo(Node o) {
            if(this.y < o.y)
                return 1;
            else if(this.y == o.y)
                return Integer.compare(this.x, o.x);
            return -1;
        }
    }

    public static void main(String[] args) {
        int[][] ret = new Solution().solution(new int[][] {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1},
            {1, 3}, {8, 6}, {7, 2}, {2, 2}});
        System.out.println(Arrays.toString(ret[0]));
        System.out.println(Arrays.toString(ret[1]));
    }
}
