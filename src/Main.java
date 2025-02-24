import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        X x = new X(null, a -> a * 3);
        ForkJoinPool pool = new ForkJoinPool();
        Integer result = pool.invoke(x);
        System.out.println("Final result: " + result);
    }
}