import java.util.*;

public class Classroom {
    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static boolean isIdentical(Node node, Node subRoot) {
        //check if the structure of the subroot is same or not
        if(node == null && subRoot==null) {
            return true;
        } else if(node == null || subRoot == null || node.data != subRoot.data) {       //check for non-identical
            return false;
        } 

        if(!isIdentical(node.left, subRoot.left)) {
            return false;
        }
        if(!isIdentical(node.right, subRoot.right)) {
            return false;
        }

        return true;
    }

    //Top View
    static class Info {
        Node node;
        int hd;     //horizontal distance

        public Info(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void topView(Node root) {
        //Level Order traversal
        Queue<Info> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();

        int min = 0, max = 0;
        //add Info object of root
        //by default horizontal distance of root node is 0
        q.add(new Info(root, 0));
        q.add(null);

        while (!q.isEmpty()) {
            Info curr = q.remove();
            if(curr == null) {
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {              
                if(!map.containsKey(curr.hd)) {     //first time hd is occuring
                    map.put(curr.hd, curr.node);
                }

                if(curr.node.left != null) {
                    q.add(new Info(curr.node.left, curr.hd - 1));
                    min = Math.min(min, curr.hd-1);
                }

                if(curr.node.right != null) {
                    q.add(new Info(curr.node.right, curr.hd+1));
                    max = Math.max(max, curr.hd+1);
                }
            }

        }

        for(int i=min; i<= max; i++) {
            System.out.print(map.get(i).data + " ");
        }
        System.out.println();


    }


    public static boolean isSubtree(Node root, Node subRoot) {

        if(root.data == subRoot.data) {
            if(isIdentical(root, subRoot)) {
                return true;
            }
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public static void main(String args[]) {
        /*
                    1
                   / \
                  2   3
                 / \ / \
                4  5 6  7
          
         */

         Node root = new Node(1);
         root.left = new Node(2);
         root.right = new Node(3);
         root.left.left = new Node(4);
         root.left.right = new Node(5);
         root.right.left = new Node(6);
         root.right.right = new Node(7);

         /* 
          
                        2
                       / \
                      4   5

         */

         Node subRoot = new Node(2);
         subRoot.left = new Node(4);
         subRoot.right = new Node(5);

        //  System.out.println(isSubtree(root, subRoot));

        topView(root);


    }
}
