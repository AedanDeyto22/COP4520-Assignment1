// Aedan Gilbert D. Deyto
// Project 1 for COP4520
// 1/28/2022

import java.io.*;
import java.lang.*;
import java.util.*;

public class PrimeCount
{
    public List<Prime> threads;
    public int max;
    public int primeCount;
    public int track;
    public long primeTotal;
    public int value;
    public boolean prime [];

    // This function gives a thread a value to use and increments it
    public static synchronized int getAndSet(PrimeCount count)
    {
        int val = count.value;
        count.value++;
        return val;
    }

    // This will be used to update the boolean array at the given index to fasle
    public static synchronized void updatePrime(int i, PrimeCount count)
    {
        count.prime[i] = false;
    }

    // Gets sent the boolean value of the array at the given index
    public static synchronized boolean getPrime(int i, PrimeCount count)
    {
        return count.prime[i];
    }

    // Will tell the threads to stop after the value squared is greater than max
    public static synchronized  boolean checkCount(PrimeCount count)
    {
        if ((count.value * count.value) > count.max )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void main(String agrs [])
    {
        // Variables just being set
        PrimeCount count = new PrimeCount();
        count.max = 100000000;
        count.value = 2;
        count.primeCount = 0;
        count.primeTotal = 0;
        count.track = 0;
        count.prime = new boolean [count.max + 1];
        long start = System.nanoTime();

        // Filling the array with true
        Arrays.fill(count.prime, true);

        // Create the 8 instances of the threads for the program
        Prime prime1 = new Prime(1, count);
        Prime prime2 = new Prime(2, count);
        Prime prime3 = new Prime(3, count);
        Prime prime4 = new Prime(4, count);
        Prime prime5 = new Prime(5, count);
        Prime prime6 = new Prime(6, count);
        Prime prime7 = new Prime(7, count);
        Prime prime8 = new Prime(8, count);

        // Creates all 8 threads
        Thread thread1 = new Thread(prime1);
        Thread thread2 = new Thread(prime2);
        Thread thread3 = new Thread(prime3);
        Thread thread4 = new Thread(prime4);
        Thread thread5 = new Thread(prime5);
        Thread thread6 = new Thread(prime6);
        Thread thread7 = new Thread(prime7);
        Thread thread8 = new Thread(prime8);

        // Starts to run all 8
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();

        // The threads will run concurrently to each other and join will allow
        // us to make sure all threds are done before we keep on going
        try
        {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
            thread7.join();
            thread8.join();
        }
        catch (InterruptedException error)
        {
            error.printStackTrace();
        }

        // After all threads are done we count how many primes are there from 2 to max
        // And the sum of all of those primes
        for (int i = 2; i <= count.max; i++)
        {
            if (count.prime[i] == true)
            {
                count.primeCount++;
                count.primeTotal += i;
            }
        }

        // We create a list that will hold the list of the top 10 largets primes
        // from largest to smallest
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = count.max; i >= 2; i--)
        {
            if (count.prime[i] == true)
            {
                list.add(i);
                count.track++;
                if (count.track == 10)
                {
                    break;
                }
            }
        }

        // This will reverse the list from smallest to largest
        Collections.reverse(list);

        // Get the execution time based on nano seconds and convert it to seconds
        long end = System.nanoTime();
        long exectution = end - start;
        double convert = exectution / 1000000000;

        // This will print the execution time, prime count, prime total,
        // and top 10 list in a text file called ptime.text
        try
        {
            FileWriter writer = new FileWriter("primes.txt");
            writer.write("Execution Time: " + Double.toString(convert));
            writer.write(" Prime count: " + Integer.toString(count.primeCount));
            writer.write(" Sum of Primes: " + Long.toString(count.primeTotal) + "\n");
            writer.write("Top Max Primes: " + list);
            writer.close();
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
    }
}
