import java.util.*;
public class Graphs{
    // //using adjacency matrix
    // public static class Graph
    // {
    //     int V;
    //     int matrix[][];
    //     public Graph(int V) //n denotes the no. of nodes
    //     {
    //         this.V=V;
    //         this.matrix = new int[V][V];
    //         for(int i=0;i<V;i++)
    //         {
    //             for(int j=0;j<V;j++)
    //             {
    //                 matrix[i][j]=0;
    //             }
    //         }
    //     }
    //     public int countEdges(){
    //         int count=0;
    //         for (int i=0;i<V;i++){
    //             for(int j=0;j<V;j++){
    //                 if(matrix[i][j]!=0){
    //                     count++;
    //                 }
    //             }
    //         }return count/2;
    //     }
    //     public void add(int src, int dest, int cost){
    //         matrix[src][dest]=cost;
    //         matrix[dest][src]=cost;
    //     }
    //     public void display()
    //     {
    //         for (int i=0;i<V;i++){
    //             for(int j=0;j<V;j++){
    //                 if(matrix[i][j]!=0){
    //                     System.out.println(i+"->"+j+"@"+matrix[i][j]);
    //                 }
    //             }
    //         }
    //     }
    //     public boolean BFS(int src, int dest){ //does a path exist to go from src to dest?
    //         Queue<Integer> q1 = new LinkedList();
    //         int[] visited = new int[V]; 
    //         for(int i=0;i<V;i++) visited[i]=0;
    //         q1.add(src);

    //         while(!q1.isEmpty()){
    //             //remove the topmost element
    //             int top = q1.peek();
    //             q1.remove();

    //             //mark it visited
    //             if(visited[top]==1) continue;
    //             visited[top]=1;

    //             //check the element
    //             if(top==dest) return true;

    //             //add the neighbours that are unvisited
    //             for(int j=0;j<V;j++){
    //                 if(matrix[top][j]!=0 && visited[j]==0){
    //                     q1.add(j);
    //                 }
    //             }
    //         }return false;
    //     }

    //     public boolean DFS(int src, int dest){ //does a path exist to go from src to dest?
    //         Stack<Integer> st1 = new Stack<>();
    //         int[] visited = new int[V]; 
    //         for(int i=0;i<V;i++) visited[i]=0;
    //         st1.push(src);

    //         while(!st1.isEmpty()){
    //             //remove the topmost element
    //             int top = st1.peek();
    //             st1.pop();

    //             //mark it visited
    //             if(visited[top]==1) continue;
    //             visited[top]=1;

    //             //check the element
    //             if(top==dest) return true;

    //             //add the neighbours that are unvisited
    //             for(int j=0;j<V;j++){
    //                 if(matrix[top][j]!=0 && visited[j]==0){
    //                     st1.push(j);
    //                 }
    //             }
    //         }return false;
    //     }

    //     public boolean hasCycleBFS(){ //if we come to an already visited node again, it has cycle
    //         Queue<Integer> q1 = new LinkedList();
    //         int[] visited = new int[V]; 
    //         int comp=0;
    //         for(int src = 0;src<V;src++){
    //             if (visited[comp]==1) continue;
    //             comp++;
    //             q1.add(src);

    //             while(!q1.isEmpty()){
    //                 //remove the topmost element
    //                 int top = q1.peek();
    //                 q1.remove();

    //                 //mark it visited
    //                 if(visited[top]==1) return true;
    //                 visited[top]=1;

    //                 //add the neighbours that are unvisited
    //                 for(int j=0;j<V;j++){
    //                     if(matrix[top][j]!=0 && visited[j]==0){
    //                         q1.add(j);
    //                     }
    //                 }
    //             }
    //         }return false;
    //     }
    // }

    // public static void main(String[] args) {
    //     Graph g1 = new Graph(7);
    //     g1.add(0,2,10);
    //     g1.add(2,3,20);
    //     g1.add(2,1,100);
    //     g1.add(3,1,50);
    //     g1.add(4,5,40);
    //     g1.add(5,6,100);
    //     g1.display();
    //     System.out.println("no. of edges :" + g1.countEdges());
    //     System.out.println(g1.BFS(1,5));
    //     System.out.println(g1.DFS(1,3));
    //     System.out.println(g1.hasCycleBFS());
    // }

    
    //using adjacency list - we'll use arraylist<arraylist<int>>
    static class Graph{
        int V;
        ArrayList<ArrayList<Integer>> arr;
        Graph(int V){
           this.V = V;
           this.arr = new ArrayList<>();
           for (int i = 0; i < V; i++) {
                arr.add(new ArrayList<Integer>()); 
            }
        }
        public void add(int src, int dst){
            arr.get(src).add(dst);
            
        }
        public void display(){
            for(int i=0;i<arr.size();i++){
                if (arr.get(i).size()==0) continue;
                System.out.print(i+"->");
                for(int j=0;j<arr.get(i).size();j++){
                    System.out.print(arr.get(i).get(j)+" ");
                }System.out.println();
            }
        }
        public boolean BFS(int src, int dst){
            Queue<Integer> q1 = new LinkedList();
            q1.add(src);
            int[] visited = new int[V];
            while(!q1.isEmpty()){
                //remove
                int top = q1.peek();
                q1.remove();

                //mark visited
                if(visited[top]==1) continue;
                visited[top]=1;

                //check
                if(top==dst) return true;
                
                //add neighbours
                for(int i=0;i<arr.get(top).size();i++){
                    if(visited[arr.get(top).get(i)]==0) q1.add(arr.get(top).get(i));
                }
            }return false;
        }
        public boolean DFS(int src, int dst){
            Stack<Integer> q1 = new Stack<>();
            q1.push(src);
            int[] visited = new int[V];
            while(!q1.isEmpty()){
                //remove
                int top = q1.peek();
                q1.pop();

                //mark visited
                if(visited[top]==1) continue;
                visited[top]=1;

                //check
                if(top==dst) return true;
                
                //add neighbours
                for(int i=0;i<arr.get(top).size();i++){
                    if(visited[arr.get(top).get(i)]==0) q1.push(arr.get(top).get(i));
                }
            }return false;
        }
        public void BFT(){
            Queue<Integer> q1 = new LinkedList();
            int[] visited = new int[V];
            for(int src=0;src<V;src++){
                if(visited[src]==1) continue;
                q1.add(src);
                while(!q1.isEmpty()){
                    //remove
                    int top = q1.peek();
                    q1.remove();

                    //mark visited
                    if(visited[top]==1) continue;
                    visited[top]=1;

                    //print
                    System.out.print(top+" ");
                    
                    //add neighbours
                    for(int i=0;i<arr.get(top).size();i++){
                        if(visited[arr.get(top).get(i)]==0) q1.add(arr.get(top).get(i));
                    }
                }System.out.println();
            }          
        }
        public void DFT(){
            Stack<Integer> q1 = new Stack<>();
            int[] visited = new int[V];
            for(int src=0;src<V;src++){
                if(visited[src]==1) continue;
                q1.push(src);
                while(!q1.isEmpty()){
                    //remove
                    int top = q1.peek();
                    q1.pop();

                    //mark visited
                    if(visited[top]==1) continue;
                    visited[top]=1;

                    //print
                    System.out.print(top+" ");
                    
                    //add neighbours
                    for(int i=0;i<arr.get(top).size();i++){
                        if(visited[arr.get(top).get(i)]==0) q1.push(arr.get(top).get(i));
                    }
                }System.out.println();
            }          
        }

        public boolean isConnected(){
            int comp=0;
            int[] visited = new int[V];
            Queue<Integer> q1 = new LinkedList();
            for(int src=0;src<V;src++){
                if (visited[src]==1) continue;
                q1.add(src);
                comp++;
                while(!q1.isEmpty()){
                    //remove
                    int top = q1.peek();
                    q1.remove();

                    //mark visited 
                    if(visited[top]==1) continue;
                    visited[top]=1;

                    //we have to do nothing here

                    //add unvisited neighbours
                    for(int j=0;j<arr.get(top).size();j++){
                        if(visited[arr.get(top).get(j)]!=1) q1.add(arr.get(top).get(j));
                    }

                }
            } return comp==1;
        }


        public boolean isCyclic(){
            int[] visited = new int[V];
            Queue<Integer> q1 = new LinkedList();
            for(int src=0;src<V;src++){
                if (visited[src]==1) continue;
                q1.add(src);
                while(!q1.isEmpty()){
                    //remove
                    int top = q1.peek();
                    q1.remove();

                    //mark visited 
                    if(visited[top]==1) return true;
                    visited[top]=1;

                    //we have to do nothing here

                    //add unvisited neighbours
                    for(int j=0;j<arr.get(top).size();j++){
                        if(visited[arr.get(top).get(j)]!=1) q1.add(arr.get(top).get(j));
                    }

                }
            } return false;
        }

        public boolean isTree() {  //if the graph has no cycle and no forest, then it is a tree
            return isConnected()&& !isCyclic();
        }

        public ArrayList<ArrayList<Integer>> getConnectedComp(){
            ArrayList<ArrayList<Integer>> bl = new ArrayList<>(); //bl stands for big list
            int[] visited = new int[V];
            Queue<Integer> q1 = new LinkedList();
            for(int src=0;src<V;src++){
                if (visited[src]==1) continue;
                q1.add(src);
                ArrayList<Integer> sl = new ArrayList<>(); //sl stands for small-list
                while(!q1.isEmpty()){
                    //remove
                    int top = q1.peek();
                    q1.remove();
                    
                    //mark visited 
                    if(visited[top]==1) continue;
                    visited[top]=1;

                    //add the element to the small-list
                    sl.add(top);

                    //add unvisited neighbours
                    for(int j=0;j<arr.get(top).size();j++){
                        if(visited[arr.get(top).get(j)]!=1) q1.add(arr.get(top).get(j));
                    }

                }bl.add(sl);
            }return bl;
        }
        public boolean RecDFS(int src,int dst){
            int[] visited = new int[V];
            return DFS_Rec(src, dst, visited);
        }
        protected boolean DFS_Rec(int src, int dst, int[] visited){
            
            //mark the source visited 
            visited[src]=1;

            //check if we have reached the dst
            if(src==dst) return true;

            // if not then move to the neighbour(s)
            for(int j=0;j<arr.get(src).size();j++){
                if (visited[arr.get(src).get(j)]!=1){
                    if (DFS_Rec(arr.get(src).get(j),dst,visited)) return true;
                }
            }
            //return the result
            return false;
            
        }

        public void RecDFT(){
            int[] visited = new int[V];
            for(int src = 0;src<V;src++){
                if (visited[src]!=1){
                    RecDFT(src,visited);
                    System.out.println();
                }
            }
            
        }
        protected void RecDFT(int src, int[] visited){
            if (src==V) return;

            //mark visited
            visited[src]=1;

            //print the current node
            System.out.print(src+" ");

            //move to the neighbours
            for(int j=0;j<arr.get(src).size();j++){
                if (visited[arr.get(src).get(j)]!=1){
                    RecDFT(arr.get(src).get(j),visited); 
                }
            }
        }

        protected void helper(int src, int[] visited, Stack<Integer> st){
            //mark visited
            visited[src]=1;

            //move to the neighbours
            for(int j=0;j<arr.get(src).size();j++){
                if (visited[arr.get(src).get(j)]!=1){
                    helper(arr.get(src).get(j),visited,st); 
                }
            }
            //push to the stack
            st.push(src);
        }

        public ArrayList<ArrayList<Integer>> getSCC(){
            ArrayList<ArrayList<Integer>> bl = new ArrayList<>();
            int[] visited = new int[V];
            for(int i=0;i<V;i++){
                if (visited[i]==1) continue;
                ArrayList<Integer> sl = new ArrayList<>();
                sl.add(i);
                visited[i]=1;
                for(int j=i+1;j<V;j++){
                    if(DFS(i,j)&&DFS(j,i)) {
                        sl.add(j); 
                        visited[j]=1;
                    }
                }bl.add(sl);
            }return bl;
        }
        
        public void topologicalSort(){
            int[] visited = new int[V];
            Stack<Integer> q1 = new Stack<>();
            ArrayList<ArrayList<Integer>> outdegree = new ArrayList<>();

            for(int i=0;i<V;i++){
                ArrayList<Integer> sl = new ArrayList<>();
                sl.add(i);
                sl.add(arr.get(i).size());
                outdegree.add(sl);
            }
           
            Comparator<ArrayList<Integer>> comp = new Comparator<ArrayList<Integer>>(){
                public int compare(ArrayList<Integer> a, ArrayList<Integer> b){
                    return a.get(1)-b.get(1);
                }
            };
            Collections.sort(outdegree,comp);
            for(ArrayList<Integer> node : outdegree){
                int v = node.get(0);
                if(visited[v]==1) continue; 
                helper(v,visited,q1);
            }
            //print the elements
            while(!q1.isEmpty()){
                int ele = q1.peek();
                q1.pop();
                System.out.print(ele+" ");
            }
        }
        
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(14);
        g1.add(0,1);
        g1.add(0,2);
        g1.add(1,3);
        g1.add(2,3);
        g1.add(3,4);
        g1.add(4,5);
        g1.add(5,6);
        g1.add(6,7);
        g1.add(0,7);
        g1.add(2,7);
        g1.add(5,7);
        g1.add(8,9);
        g1.add(8,10);
        g1.add(9,10);
        g1.add(10,11);
        g1.add(12,13);

        // Graph g2 = new Graph(9);
        // g2.add(0,1);
        // g2.add(1,2);
        // g2.add(2,3);
        // g2.add(3,0);
        // g2.add(3,4);
        // g2.add(4,5);
        // g2.add(5,6);
        // g2.add(6,4);
        // g2.add(5,7);
        // g2.add(7,8);
        // g2.add(8,7);

        Graph g3 = new Graph(6);
        g3.add(5,2);
        g3.add(5,0);
        g3.add(2,3);
        g3.add(3,1);
        g3.add(4,1);
        g3.add(4,0);
        g1.display(); 
        System.out.println(g1.BFS(1,0));  
        g1.BFT(); 
        g1.DFT(); 
        System.out.println("isConnected: "+g1.isConnected());
        System.out.println("isCyclic: "+g1.isCyclic());
        System.out.println("isTree: "+g1.isTree());
        System.out.println(g1.getConnectedComp());
        System.out.println(g1.RecDFS(1,10));
        g1.RecDFT();
        // System.out.println(g2.getSCC());
        g3.topologicalSort();

        
    }
}