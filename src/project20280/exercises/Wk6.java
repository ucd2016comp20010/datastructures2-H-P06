//package project20280.exercises;

import java.util.Arrays;
import java.util.Comparator;

public class Wk6 {

    //A is the generic array, i is the starting index
    //j is the ending index
    //i and j are non negative
    public static int count = 1;
    public static long countFib = 0;
    public static long countTrib = 0;

    public static <E> void reverseArray(E[]A, int i,int j){
        //check if they are non negatives
        if(i < 0 || j < 0 || i >= A.length || j >= A.length){
            throw new IndexOutOfBoundsException("The indexes should not be a negative number");
        }

        if(i < j){
            E temp= A[i];
            A[i] = A[j];
            A[j] = temp;


            System.out.println("Swap " + count++ + " " + Arrays.toString(A));

            reverseArray(A,i+1, j-1);
        }
    }

    //question 2 fibonacci
    public static int fibonacci(int n){
        countFib++;
        if(n <= 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static int tribonacci(int n){
        countTrib++;
        if(n <= 1){
            return 0;
        }
        if(n == 2){
            return 1;
        }

        int result = tribonacci(n-1) + tribonacci(n-2) + tribonacci(n-3);
        //System.out.println("tribonacci " + n + " " + result);
        //for 9 i need 8, 7, 6
        //for 8 i need 7, 6, 5
        return result;
    }


    public static int mcCarthy91(int n){
        if(n > 100){
            System.out.println(n + ": - 10. RESULT: " + (n-10));
            return n-10;
        }

        System.out.println(n + ": mcCarthy91(n + 11)");
        return mcCarthy91(mcCarthy91(n+11));
    }

    public static int foo(int n){
        if (n/2 == 0) {
            System.out.print(n);
            return 0;
        }

        foo(n/2);
        System.out.print(n%2);
        return 1;
    }



    public static int mystery(int n, int a, int d){
        if(n==1){
            System.out.println("n == 1 so we return a. a = " + a + " which gets added to d. d = " + d);
            return a;
        }
        else{
            System.out.println("calculation: d + mystery(n-1, a,d). d = " + d + ", n-1 = " + (n-1) + ", a = " + a);
            return d + mystery(n-1, a,d);
        }
    }


}//end of class


//Running it to see it works!
public static void main(String[] args) {
    Integer[] arr = {2,8,3,9,0,1,21,5,9,4};

    Wk6.reverseArray(arr, 2, 5);

    System.out.println("Final reversed array: " + Arrays.toString(arr));

    //--Fibonacci----------------------------------------------------------------------------
    System.out.println("Fibonacci number: " + Wk6.fibonacci(5));

    //time the fibonacci
//    long startTime = System.currentTimeMillis();
//    Wk6.fibonacci(50);
//    long endTime = System.currentTimeMillis();
//    long duration = endTime - startTime;
//
//    System.out.println("Time taken: " + duration + " ms. Number of calls: " + Wk6.countFib);

    //--Tribonacci---------------------------------------------------------------------------
    System.out.println("Tribonacci number: " + Wk6.tribonacci(9));

    //--McCarthy 91--------------------------------------------------------------------------
    System.out.println("McCarthy91: " +  Wk6.mcCarthy91(87));

    //--foo----------------------------------------------------------------------------------
    System.out.println("Foo function: ");
    Wk6.foo(2468);

    //--mystery------------------------------------------------------------------------------
    System.out.println("\nMystery function: ");
    int thing = Wk6.mystery(2,4,4);

    System.out.println("Answer: " + thing);

}
