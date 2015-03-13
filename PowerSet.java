/* PowerSet
 *
 * Computes all the possible subsets of the set { 0, 1, 2, ..., n-1 }.
 * Note that the set of all possible subsets is called the "power set".
 * You do not need to write the code below here.
 * You just need to be able to USE it.
 */

import java.util.*;

public class PowerSet
{ 
    private int n;
    private int index = 0;
    private boolean hasNext = true;
    
    public PowerSet(int n)
    { this.n = n; }
    
    public boolean hasNext()
    { return hasNext; }
    
    public int[] next()
    {
        if (!hasNext)
            return null;
        // the 1 bits of "index" indicate which numbers to include in this subset
        int bits, j=0, k=0;
        for (bits = index; bits > 0; bits /= 2)
            k += bits % 2; // k = # of bits set to 1
        int[] result = new int[k];
        bits = index;
        for (int i = 0; i < n; i++)
        {
            if (bits % 2 == 1)
                result[j++] = i;
            bits /= 2;
        }
        if (k == n)
            hasNext = false; // last one!
        index++;
        return result;
    }
    
    public static void main(String[] args)
    {
        PowerSet ps = new PowerSet(4);
        while (ps.hasNext())
        {
            int[] a = ps.next();
            System.out.println(Arrays.toString(a));
        }
    }
}