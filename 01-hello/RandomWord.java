/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        int i = 0;
        String champion = null;
        while (!StdIn.isEmpty()) {
            i++;
            String str = StdIn.readString();
            double prob = 1.0 / i;
            //System.out.println(str + ", " + prob);
            if (StdRandom.bernoulli(prob)) {
                champion = str;
            }
        }
        System.out.println(champion);

    }
}
