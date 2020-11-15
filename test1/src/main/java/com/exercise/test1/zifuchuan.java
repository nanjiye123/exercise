package com.exercise.test1;

public class zifuchuan {


    public static void main(String[] args) {
        String str1 = new StringBuilder("58").append("tong").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        String str2 = new StringBuilder("vo").append("id").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());


        /*String str1 = new String("58tong");
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1 == str1.intern());

        String str2 = new String("void");
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2 == str2.intern());*/



        /*String a = "aaa";
        String b = new StringBuilder("aa").append("a").toString();
        System.out.println(a == b);
        System.out.println(a.equals(b));*/

        /*String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);*/

        /*String s3 = new String("1") + new String("1");
        s3.intern();
        String s4 = "11";
        System.out.println(s3 == s4);*/



    }

}
