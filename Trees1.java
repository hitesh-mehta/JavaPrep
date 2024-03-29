import java.util.*;
import java.Math;
public class Trees1 {
    static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        public static int idx = -1;
        public static Node buildTree(int[] nodes){
            idx++;
            if(nodes[idx]==-1) return null;
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            return newNode;
        }public static void displayPreorder(Node root){
            if(root==null) return;
            System.out.print(root.data+" ");
            displayPreorder(root.left);
            displayPreorder(root.right);
        }
        public static void display(Node root){
            if(root==null) return;
            if(root.left!=null&&root.right!=null)
                System.out.println(root.left.data+"->"+root.data+"<-"+root.right.data);
            else{
                if(root.left==null && root.right==null) System.out.println("null->"+root.data+"<-null");
                else if(root.left==null) System.out.println("null->"+root.data+"<-"+root.right.data);
                else System.out.println(root.left.data+"->"+root.data+"<-null");
            }
            display(root.left);
            display(root.right);

        }
        public static void displayInorder(Node root){
            if(root==null) return;
            displayInorder(root.left);
            System.out.print(root.data+" ");
            displayInorder(root.right);
        }
        public static void displayPostorder(Node root){
            if(root==null) return;
            displayPostorder(root.left);
            displayPostorder(root.right);
            System.out.print(root.data+" ");
        }


        public static void displayLevelorder(Node root){
            if (root==null) return;
            Queue<Node> q1 = new LinkedList<>();
            q1.add(root);
            q1.add(null);
            while(!q1.isEmpty()){
                Node currNode = q1.remove();
                if(currNode==null){
                    System.out.println();
                    if(q1.isEmpty()) break;
                    else q1.add(null);
                }else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null)q1.add(currNode.left);
                    if(currNode.right!=null)q1.add(currNode.right);
                }
            }
        }


        public static int count(Node root){
            if(root==null) return 0;
            int leftCount = count(root.left);
            int rightCount = count(root.right);
            return leftCount+rightCount+1;
        }

        public static int sumOfNodes(Node root){
            if(root==null) return 0;
            int leftSum = sumOfNodes(root.left);
            int rightSum = sumOfNodes(root.right);
            return leftSum+rightSum+root.data;
        }

        public static int height(Node root){
            if(root==null) return 0;
            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(lh,rh)+1;
        }
        
        public static int diameter(Node node){
            if(node==null) return 0;
            int diam1 = diameter(node.left);
            int diam2 = diameter(node.right);
            int diam3 = height(node.left)+height(node.right)+1; //calculation of height required an extra 'n' time for each traversal
            return Math.max(diam3,Math.max(diam1,diam2));
        }
        //to optimize the above code in O(n), we will create a class to store both height and diameter together and solve them simultaneously

        static class Helper{
            int height;
            int diameter;
            Helper(int height,int diameter){
                this.height= height;
                this.diameter = diameter;
            }
        }
        public static Helper diameterOpti(Node node){
            if (node == null){
                Helper newHelper = new Helper(0,0);
                return newHelper;
            }
            Helper left = diameterOpti(node.left);
            Helper right = diameterOpti(node.right);
            //these above two functions give the data of the left and right subtrees;

            int height = Math.max(left.height,right.height)+1;
            // this is the final height of the subtree
            int diam1 = left.diameter;
            int diam2 = right.diameter;
            int diam3 = left.height+right.height+1;
            int diam = Math.max(Math.max(diam1,diam2),diam3);
            //this is the final diameter of the subtree
            Helper newHelper = new Helper(height,diam);
            return newHelper;
            
        }

        public static boolean isIdentical(Node root,Node subRoot){
            if(root==null && subRoot==null) return true;
            if(root==null || subRoot==null) return false;
            if(root.data==subRoot.data)
                return isIdentical(root.left, subRoot)&&isIdentical(root.right, subRoot);
            return false;
        }

        public static boolean isSubTree(Node root, Node subRoot){
            if(subRoot==null) return true;
            if (root==null) return false;
            if(root.data == subRoot.data){
                if (isIdentical(root,subroot)) return true;
            }return isSubTree(root.left, subRoot)||isSubTree(root.right, subRoot);
        }


        public static int sumLevel(Node root, int k){
            if(root==null) return 0;
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);
            int currLevel = 0;
            int sum=0;
            while (!q.isEmpty() && currLevel <= k) {
                int levelSize = q.size(); // Get the size of the current level
                for (int i = 0; i < levelSize; i++) {
                    Node node = q.poll(); // Dequeue the node
                    if (node==null) {
                        currLevel++;
                        continue;
                    }
                    if (currLevel == k) {
                        sum += node.data; // If it's the desired level, add its value to sum
                    }
                    // Enqueue its children if they exist
                    if (node.left != null) q.add(node.left);
                    if (node.right != null) q.add(node.right);
                }
                currLevel++;
            }return sum;
        }
    }
    
    public static void main(String[] args){
        int[] nodes = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        
        BinaryTree bt1 = new BinaryTree();
        
        Node root = bt1.buildTree(nodes);

        
        bt1.display(root);
        bt1.displayPreorder(root);
        System.out.println();
        bt1.displayInorder(root);
        System.out.println();
        bt1.displayPostorder(root);
        System.out.println();
        bt1.displayLevelorder(root);

        System.out.println(bt1.count(root));
        System.out.println(bt1.sumOfNodes(root));
        System.out.println(bt1.height(root));
        System.out.println(bt1.diameter(root));
        System.out.println(bt1.diameterOpti(root).diameter);
        System.out.println(bt1.sumLevel(root, 3));
    }
}
