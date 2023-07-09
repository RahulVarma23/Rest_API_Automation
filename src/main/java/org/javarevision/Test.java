package org.javarevision;


public class Test {

     class InnerClass {

         public void display() {
             System.out.println("java");
         }

    }

    public static void main(String[] args) {
         Test.InnerClass t = new Test().new InnerClass();
         t.display();

         String s1 = "Pune";
         String s2 = "Pune";
         String s3 = new String("Pune");
         String s4 = new String("Pune");


        System.out.println(s1.equals(s3));


    }

}