/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 1;
        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            double prob = (double) 1 / i;
            System.out.println(str + ", " + prob);
            System.out.println(StdRandom.bernoulli(prob));
            i++;
        }

    }
}
