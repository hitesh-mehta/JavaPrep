public class MergeSort {
    public static int[] merge(int[] arr1, int[] arr2){
        int m = arr1.length;
        int n = arr2.length;
        if(m==0) return arr2;
        if(n==0) return arr1;

        int[] arr = new int[m+n];
        int i=0,j=0;
        int k=0;
        while(i<m&&j<n){
            if(arr1[i]<arr2[j]){
                arr[k]=arr1[i];
                i++;
            }else{
                arr[k]=arr2[j];
                j++;
            }k++;
        }
        if(i<m){
            for(int a = i;a<=m-1;a++){
                arr[k]=arr1[a];
                k++;
            }
        }
        else if(j<n){
            for(int a = j;a<=n-1;a++){
                arr[k]=arr2[a];
                k++;
            }
        }
        return arr;

    }
    public static int[] slicedArray(int[] arr,int low,int high){
        int[] res = new int[high-low+1];
        for(int i=low;i<=high;i++){
            res[i-low]=arr[i];
        }
        return res;
    }
    public static void print(int[] arr){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }System.out.println();
    }
    public static int[] mergeSort(int[] arr){
        if(arr.length == 1||arr.length == 0) return arr;
        //TC = n
        if(arr.length==2) {
            if(arr[0]>arr[1]){
                int temp = arr[0];
                arr[0]=arr[1];
                arr[1]=temp;
            }
        }
        int low=0,high=arr.length-1;
        int mid = low+(high-low)/2;
        //TC = T(n/2)
        int[] arr1 = mergeSort(slicedArray(arr,low,mid));
        //TC = T(n/2)
        int[] arr2 = mergeSort(slicedArray(arr,mid+1,high));
        //Recurrence relation : T(n)=2T(n/2)+1
        return merge(arr1,arr2);
    }
    public static void main(String[] args){
        int[] arr = {10,2,3,34,36,-1,32,80};
        int[] res = mergeSort(arr);
        print(res);
    }
}

