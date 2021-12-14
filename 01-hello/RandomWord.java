/* *****************************************************************************
 *  Name:              Chen Zhou
 *  Coursera User ID:  c0a3c14e1095421aaa0a72362b56de50
 *  Last modified:     December 14, 2021
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
