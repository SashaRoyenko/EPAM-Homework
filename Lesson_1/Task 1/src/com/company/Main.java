package com.company;

import java.util.Scanner;

public class Main {

    public static void transformToBinary(String binaryFormat) {
        int decimalFormat = 0;
        for (int i = binaryFormat.length() - 1; i >= 0; i--) {
            if (binaryFormat.charAt(i) == '1') {
                decimalFormat += 1 << (binaryFormat.length() - 1 - i);
            }
        }
        System.out.println("\"" + binaryFormat + "\" -> " + decimalFormat);
    }

    public static void changeSign(int negativeNumber) {
        int positiveNumber = ~negativeNumber + 1;
        System.out.println("\"" + negativeNumber + "\" -> " + positiveNumber);
    }

    public static void showBitForPrimitives() {
        byte b = Byte.MAX_VALUE;
        int count = 1;
        while (b != 0) {
            b = (byte) (b >> 1);
            count++;
        }
        System.out.println("byte: " + count);

        short s = Short.MAX_VALUE;
        count = 1;
        while (s != 0) {
            s = (short) (s >> 1);
            count++;
        }
        System.out.println("int: " + count);

        int i = Integer.MAX_VALUE;
        count = 1;
        while (i != 0) {
            i = (i >> 1);
            count++;
        }
        System.out.println("short: " + count);

        long l = Long.MAX_VALUE;
        count = 1;
        while (l != 0) {
            l = (l >> 1);
            count++;
        }
        System.out.println("long: " + count);

    }

    public static void gcd(int firstNumber, int secondNumber) {
        int result;

        if (firstNumber == 0 || secondNumber == 0) {
            System.out.println("GCD: " + (firstNumber | secondNumber));
            return;
        }

	    /* Если m, n чётные, тогда НОД(m, n) = 2 * НОД(m / 2, n / 2).

           Пусть res := lg K, где K наибольшая степень 2 такая, что оба числа
           целиком делятся на 2 в степени K. */
        for (result = 0; ((firstNumber | secondNumber) & 1) == 0; ++result) {
            firstNumber >>= 1;
            secondNumber >>= 1;
        }

        // Если m чётное, тогда НОД(m, n) = НОД(m / 2, n).
        while ((firstNumber & 1) == 0)
            firstNumber >>= 1;

        /* Далее считается, что m нечетное. */
        do {
            while ((secondNumber & 1) == 0)  /* Если n чётное, тогда НОД(m, n) = НОД(m, n / 2). */
                secondNumber >>= 1;

            /* Далее считается, что m и n нечетные. Следовательно, m - n четно.
                Пусть m = min(m, n), n = (m - n) / 2. */
            if (firstNumber < secondNumber) /* Если m, n нечётные и m < n, тогда НОД(m, n) = НОД(n - m, m). */ {
                secondNumber -= firstNumber;
            } else /* Если m, n нечётные и m > n, тогда НОД(m, n) = НОД(m - n, m). */ {
                int diff = firstNumber - secondNumber;
                firstNumber = secondNumber;
                secondNumber = diff;
            }

            secondNumber >>= 1;
        }
        while (secondNumber != 0);

        System.out.println("GSD: " + (firstNumber << result));
    }

    public static int gcd2(int firstNumber, int secondNumber) {
        /*
            Алгоритм
            1. НОД(0, n) = n; НОД(m, 0) = m;
            2. Если m, n чётные, тогда НОД(m, n) = 2 * НОД(m / 2, n / 2).
            3. Если m чётное, тогда НОД(m, n) = НОД(m / 2, n).
            4. Если n чётное, тогда НОД(m, n) = НОД(m, n / 2).
            5. Если m, n нечётные и m > n, тогда НОД(m, n) = НОД(n, m - n).
            6. Если m, n нечётные и m < n, тогда НОД(m, n) = НОД(n, n - m).
            7. Если m = n, тогда НОД(m, n) = m;
        */
        if (firstNumber == 0 || secondNumber == 0) {
            return firstNumber | secondNumber;
        }
        if ((firstNumber & secondNumber) == firstNumber) {
            return firstNumber;
        }
//        if ((firstNumber & 1) == 0 && (secondNumber & 1) == 0) {
//            return gcd2(firstNumber >> 1, secondNumber >> 1) << 1;
//        } else if ((firstNumber & 1) == 0) {
//            return gcd2(firstNumber >> 1, secondNumber);
//        } else if ((secondNumber & 1) == 0) {
//            return gcd2(firstNumber, secondNumber >> 1);
//        } else if ((firstNumber & 1) != 0 && (secondNumber & 1) != 0 && firstNumber > secondNumber) {
//            return gcd2(secondNumber, firstNumber - secondNumber);
//        } else {
//            return gcd2(secondNumber, secondNumber - firstNumber);
//        }
        if ((firstNumber & 1) == 0) {
            return ((secondNumber & 1) == 0) ? (gcd2(firstNumber >> 1, secondNumber >> 1) << 1) : gcd2(firstNumber >> 1, secondNumber);
        } else {
            return ((secondNumber & 1) == 0) ? gcd2(firstNumber, secondNumber >> 1) : gcd2(secondNumber, (firstNumber > secondNumber) ? firstNumber - secondNumber : secondNumber- firstNumber);
        }
    }

    public static void changeBit(int number, int position) {
        System.out.println("Number before change bit: " + Integer.toBinaryString(number) + " " + number);
        number ^= (1 << position);
        System.out.println("Number after change bit: " + Integer.toBinaryString(number) + " " + number);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        //First task
        System.out.print("Input number in binary format: ");
        String binaryFormat = scan.next();
        transformToBinary(binaryFormat);
        //Second task
        System.out.print("Input number which sign you want to transform: ");
        int negativeNumber = scan.nextInt();
        changeSign(negativeNumber);
        //Third task
        showBitForPrimitives();
        //Fourth task
        System.out.print("Input first number: ");
        int firsNumber = scan.nextInt();
        System.out.print("Input second number: ");
        int secondNumber = scan.nextInt();
        gcd(firsNumber, secondNumber);
        System.out.println("GSD: " + gcd2(firsNumber, secondNumber));
        //Fifth task
        System.out.print("Input number which bit you want to change: ");
        int numberToChange = scan.nextInt();
        System.out.print("Input position of bit which you want to change: ");
        int bitPosition = scan.nextInt();
        changeBit(numberToChange, bitPosition);

    }

}
