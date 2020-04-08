import java.util.*;

public class Driver
{
    public static void main(String[] args)
    {
        //create AVL Tree with at least 15 nodes with random non-repeating values
        AVLTree tree = new AVLTree();

        /*int size = 15;
        String[] stringArray = {"g", "y", "x", "i", "v", "q", "n", "p", "o", "u", "c", "z", "j", "l", "f"};
        //String[] stringArray = {"q", "o", "e", "r", "w", "g", "f", "x", "j", "n", "b", "l", "v", "s", "t"};
        for(int i = 0; i < size; i++)
        {
            System.out.print(stringArray[i]);
            tree.insert(stringArray[i]);
        }*/

        Random r = new Random();
        char c;
        String s = "";
        int size = 15;
        String[] stringArray = new String[size];
        for(int i = 0; i < size; i++)
        {
            c = (char) (r.nextInt(26) + 'a');
            s = Character.toString(c);
            stringArray[i] = s;   //insert into array
            int temp = 0;
            for(int index = 0; index < i; index++)
            {
                if(stringArray[i].equals(stringArray[index]))
                    temp = 1;
            }
            if(temp == 1)
                i--;
            else
                {
                    System.out.print(s + "  ");
                    tree.insert(s);
                }
        }

        //String targetKey = stringArray[14];
        String targetKey = s;
        System.out.println("\nLeft Height - Node - Right Height");
        tree.nodeHeight(targetKey);
    }
}
