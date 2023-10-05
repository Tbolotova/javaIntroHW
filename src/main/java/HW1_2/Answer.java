package HW1_2;

public class Answer {
    public void printPrimeNums(){
        // Напишите свое решение ниже
        for (int i = 1; i < 1000; i++){
            boolean isPrime = true;
            for (int j = 2; j < i; j++){
                isPrime = i % j != 0;
                if (!isPrime) {
                    break;
                }
            }
            if (isPrime){
                System.out.println(i);
            }
        }
    }
}
