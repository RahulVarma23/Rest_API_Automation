package org.javarevision;


import java.util.Arrays;

public class Test {


    String s = "INDIA";

   // rotate this tring by given iteration
    // n=1 , NDIAIjnjsdjdddddshabhbj
    //n=2 DIAIN



    public static void main(String[] args) {
       int [] arr = {1, 3, 0 , 1 ,0 , 2, 4, 0, 5 };

       int [] ans = new int[arr.length];

       int j=0;
       for(int i=0;i<arr.length;i++){
           if(arr[i] !=0){
               ans[j] = arr[i];
               j++;
           }
       }

        System.out.println(Arrays.toString(ans));
    }

}