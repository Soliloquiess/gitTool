package BOJ;

import java.util.Arrays;
import java.util.Scanner;

//public class BOJ_1920_수찾기 {
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		int T = sc.nextInt();
//		int arr[] = new int[T];
//		int arr2[] =new int[T];
//        boolean bool[] = new boolean[10];
//        int arr3[] = new int[T];
//		for(int i =0; i<T;i++) {
//			arr[i] = sc.nextInt();
//		}
//		int M =sc.nextInt();
//		for(int i =0; i<M;i++) {
//			arr2[i] = sc.nextInt();
//		}
////		Arrays.sort(arr);
////		Arrays.sort(arr2);
//		for(int i=0;i<M;i++) {
//			for(int j =0; j<M;j++) {
//				if(arr[j]==arr2[i])
//				{
//					arr3[i] = 1; 
//				}			
//			}
//		}
//		
//		for(int i=0; i<M;i++) {
//			System.out.println(arr3[i]);
//		}
//	}	
//}


 import java.util.*;

public class BOJ_1920_수찾기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i=0; i<n; i++) a[i] = sc.nextInt();
        Arrays.sort(a);
        int m = sc.nextInt();
        for(int i=0; i<m; i++){
            int result=0, low=0, high=n-1;
            int target = sc.nextInt();
            while(low<=high){
                int mid = (low+high)/2;
                if(a[mid]==target) {
                    result=1;	
                    break;
                }
                if(a[mid]>target) high = mid-1;
                else low = mid+1;
            }
            System.out.println(result);
        }
    }
}
 


