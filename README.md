# COP4520-Assignment1

## To Run Code in the Command Line:
* First type "javac PrimeCount.java"
* Then type "java PrimeCount"

## Summary
The way I first tackled this project was by trying to find an efficient way to find all primes numbers from 1 to x. After finding out about the Sieve of Eratosthenes, I then tried to understand it to a greater degree, and then shifted my focus on turning the Sieve Of Eratosthenes algorithm to run concurrently. I did so by using the 8 threads to cross out the non prime values together instead of a for loop doing it one at a time. So each thread would be given a value, if it's already crossed out then the thread can move to the next value. However, if a value is not crossed out yet then the thread will cross out all the multiples of that value. After all the threads are done we check to see what values are not crossed out yet and those values are our primes values. 

This is a correct and efficient way because of how each thread will be working on multiple values at once and will be crossing out more values faster than a for loop. Since a given loop will be doing it one at a time, the threads will be doing it 8 different values at a time. Also, I used synchronized functions to prevent the threads from clashing when they try to update a value or get a value. And, each of the threads do their own thing without the need for the other threads. Since each thread will keep going unless they stop themselves because the program indicates when they can stop, other than that they don’t have to wait for other threads to do what they need to for them to work. The way I went to test my program was first to see how my program dealt with smaller ranges such as from 1 to 100, if that was correctly done I moved to 1 to 1000 then 1 to 1000000 until I reached 1 to 100000000. I started small so that I could check that the program was performing how I wanted it to, then I kept testing to see if it kept performing and obtaining the right information as we kept using larger and larger ranges. 

