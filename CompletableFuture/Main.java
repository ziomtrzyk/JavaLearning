import java.util.concurrent.CompletableFuture;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] konteksty = {"A", "B", "C"};
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (String kon : konteksty) {
            Calc c = new Calc(kon);
            CompletableFuture<Void> future = CompletableFuture.supplyAsync(c::x)
                    .thenApply(c::y)
                    .thenAccept(c::z)
                    .thenRun(() -> System.out.println("Praca zakończona dla kontekstu " + kon));
            futures.add(future);
        }


        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("Wszystkie operacje zakończone w main");
    }
}