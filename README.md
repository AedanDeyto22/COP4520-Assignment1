# COP4520-Assignment1

## To Run Code in the Command Line:
* First type "javac PrimeCount.java"
* Then type "java PrimeCount"

## Summary
The way I first tackled this project was by trying to find an efficient way to find all primes numbers from 1 to x. After finding out about the Sieve of Eratosthenes, I then tried to understand it to a greater degree, and then shifted my focus on turning the Sieve Of Eratosthenes algorithm to run concurrently. I did so by using the 8 threads to cross out the non prime values together instead of a for loop doing it one at a time. So each thread would be given a value, if it's already crossed out then the thread can move to the next value. However, if a value is not crossed out yet then the thread will cross out all the multiples of that value. After all the threads are done we check to see what values are not crossed out yet and those values are our primes values. 

This is a correct and efficient way because of how each thread will be working on multiple values at once and will be crossing out more values faster than a for loop. Since a given loop will be doing it one at a time, the threads will be doing it 8 different values at a time. The way I went to test it was first to see how my program dealt with smaller ranges such as from 1 to 100, if that was correctly done I moved to 1 to 1000 then 1 to 1000000 until I reached 1 to 100000000.

