package test_classes;

import java.util.HashSet;
import java.util.Iterator;

public class Test123 {

    public static void main(String[] args)
    {
        String s1= "Welcome to java Welcome to prrogram";


        String[] s = s1.split(" ");

        String res ="";

        HashSet set = new HashSet();

        for(String a: s)
        {
            set.add(a);
        }


        Iterator itr= set.iterator();

        while(itr.hasNext())
        {
            res+=" "+itr.next();
        }

        System.out.println("OUTPUT:  "+res);





    }
}