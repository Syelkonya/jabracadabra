package su.syelkonya.concurrency.array;

public class Main {

    void main(){
        int k = 3;
        int[] firstArray  = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] secondArray = {10, 20, 30, 40, 50, 60};

        ParallelArraySum summer = new ParallelArraySum(k);

        summer.calculate(firstArray);
        summer.calculate(secondArray);

        summer.shutdown();
    }
}
