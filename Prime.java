// Aedan Gilbert D. Deyto
// Project 1 for COP4520
// 1/28/2022

public class Prime implements Runnable
{
    private boolean canRun;
    private int name;
    private PrimeCount count;

    // The constructor sets the name of the thread and allows the while loop
    // to run. And gives it access to PrimeCount functions
    public Prime (int name, PrimeCount count)
    {
        this.name = name;
        this.count = count;
        this.canRun = true;
    }

    @Override
    public void run()
    {
        // Will keep running until canRun is set to false
        while (canRun)
        {
            // Get a value of count.value and increments its for the next thread
            // Also getst the max value we are trying to reach
            int val = count.getAndSet(count);
            int n = count.max;

            // Sees if the value we are using is true based on the boolean array
            // in PrimeCount
            if (count.getPrime(val, count))
            {
                // If is, it then goes through the array starting at its squared value
                // and will keep incrementing based the value.
                // And seting those values to false in the boolean value
                for (int i = val * val; i <= n; i += val)
                {
                    if (count.prime[i] == true)
                    {
                        count.updatePrime(i, count);
                    }
                }
            }

            // Checks to see if we need to keep running if not then we can
            // terminate this thread.
            if (count.checkCount(count))
            {
                this.canRun = false;
            }
        }
    }
}
