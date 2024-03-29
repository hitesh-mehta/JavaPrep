import java.util.*;
public class BST {
    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }        
    }
    static class BSTree{
        public static Node buildSorted(int[] arr){
        return construct(arr, 0, arr.length-1);

        }
        public static Node buildUnsorted(int[] arr){
            Node root = new Node(arr[0]);
            for(int i=1;i<arr.length;i++){
                root=insert(root,arr[i]);
            }return root;
        }
        //construction of a Binary Search Tree in case sorted array is given
        static Node construct(int[] arr,int low,int high){
            if(low>high) return null;
            int mid = (low+high)/2;
            Node newNode = new Node(arr[mid]);
            newNode.left = construct(arr,low,mid-1);
            newNode.right = construct(arr,mid+1,high);
            return newNode;
        }
        //construction is unsorted array is given
        public static Node insert(Node root, int key){
            if(root==null) {
                root = new Node(key);
                return root;
            }
            if(root.data>key){
                root.left = insert(root.left,key);
            }else{
                root.right = insert(root.right,key);
            }
            return root;

        }


        public static void display(Node node){
            if(node==null) return;
            if(node.left!=null && node.right!=null){
                System.out.println(node.left.data+"->"+node.data+"<-"+node.right.data);
            }else if(node.left==null && node.right==null){
                System.out.println(null+"->"+node.data+"<-"+null);
            }
            else if (node.left==null){
                System.out.println(null+"->"+node.data+"<-"+node.right.data);
            }else System.out.println(node.left.data+"->"+node.data+"<-"+null);
            display(node.left);
            display(node.right);
        }

        public static boolean find(Node node, int key){
            if(node==null) return false;
            if(node.data==key) return true;
            else if(node.data<key) return find(node.right,key);
            else return find(node.left,key);
        }

        public static int findMax(Node node){
            if (node==null) return 0;
            if(node.right==null) return node.data;
            return findMax(node.right);
        }
        
        public static Node deleteNode(Node node, int key){
            if(node==null) return null;
            if(node.data==key) //we have found the position of the key
            {
                //Case 1 : No children
                if(node.left == null && node.right == null){
                    return null;
                }//case 2 : only one child
                else if(node.left!=null && node.right==null){
                    return node.left;
                }else if(node.left==null && node.right!=null){
                    return node.right;
                }//case 3 : two children
                else{
                    //find the inorder successor/predecessor of the element
                    //inorder successor is the minimum of the right subtree and inorder predecessor is the max of the left subtree, in this we'll use the inorder predecessor
                    int ele = findMax(node.left);
                    node.data = ele;
                    node.left = deleteNode(node.left,ele);
                    return node;
                }
            }
            else if (node.data<key) {
                node.right = deleteNode(node.right,key);
            }
            else node.left = deleteNode(node.left, key);
            return node;
        }

        //printInRange(X,Y)
        public static void printInRange(int x, int y, Node node){
            if (node==null) return;
            if(x<=node.data && node.data<=y){
                printInRange(x, y, node.left);
                System.out.print(node.data+" ");
                printInRange(x, y, node.right);
            }if(node.data>y){
                printInRange(x, y, node.left);
            }if(node.data<x){
                printInRange(x, y, node.right);
            }
        }

        //display all paths from root to node
        public static void printPath(ArrayList<Integer> path){
            for(int i=0;i<path.size();i++){
                System.out.print(path.get(i)+"->");
            }System.out.println("null");
        }
        public static void allPaths(Node root,ArrayList<Integer> path){
            if(root==null) return;
            path.add(root.data);
            if(root.left==null&&root.right == null){ //base case for leaf nodes
                printPath(path);
            }else{ //non-leaf cases 
                allPaths(root.left, path); 
                allPaths(root.right, path);
            }
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        BSTree bst1 = new BSTree();
        int[] arr = {1,2,3,4,5,6,7};
        int[] arr2 = {50,20,100,10,30,65,150,5,11,35,125,200,18,32};
        BSTree bst2 = new BSTree();
        Node root = bst1.buildSorted(arr);
        Node root2 = bst2.buildUnsorted(arr2);
        bst1.display(root);
        System.out.println("----------");
        bst2.display(root2);
        System.out.println(bst1.find(root, 10));
        System.out.println(bst1.findMax(root));
        System.out.println("-----------");

        root2 = bst2.deleteNode(root2,125); //leaf node
        bst2.display(root2);
        System.out.println("-----------");

        root2 = bst2.deleteNode(root2,150); //node with single child
        bst2.display(root2);
        System.out.println("-----------");

        root2 = bst2.deleteNode(root2,20);
        bst2.display(root2);

        bst2.printInRange(20,51,root2);

        bst2.allPaths(root2, new ArrayList<Integer>());
        
    }

}
