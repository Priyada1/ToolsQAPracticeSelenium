package SpecificTestcases;

public class Test1234 {

    /*
         Input : ABCDEFGHIJKLMNOPQRSTUVWXYZ

           Vowels : AEIOU

          Output : ABCDEFFGGHHIJJJKKKLLLMMMNNNOPPPPQQQQRRRRSSSSTTTTUVVVVVWWWWWXXXXXYYYYYZZZZZ
     */

    public static void main(String[] args)
    {
        String s1= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();

        char[] c = s1.toCharArray();

        for(int i=0;i<c.length;i++)
        {

            if(c[i]<='E')
            {
                sb.append(c[i]);
            }
            if(c[i]>'E' && c[i]<='I')
            {
                int j=0;
                //int count =2;
                while(j<2)
                {
                    sb.append(c[i]);
                    j++;
                }
            }
            if(c[i]> 'I' && c[i]<='O'){
                int j=0;
                //int count =2;
                while(j<3)
                {
                    sb.append(c[i]);
                    j++;
                }
            }
            if(c[i]>'O' && c[i]<='U')
            {
                int j=0;
                //int count =2;
                while(j<4)
                {
                    sb.append(c[i]);
                    j++;
                }
            }

            if(c[i]>'U' && c[i]<='Z')
            {
                int j=0;
                while(j<5)
                {
                    sb.append(c[i]);
                    j++;
                }
            }


        }

        System.out.println(sb);



    }

}