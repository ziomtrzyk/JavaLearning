import java.util.concurrent.CountedCompleter;

class Y extends CountedCompleter<Integer> {
    private Integer result = 0;

    public Y(CountedCompleter<?> parent) {
        super(parent);
    }

    @Override
    public void compute() {
        System.out.println("Y started in " + Thread.currentThread().getName());

        for (int i = 0; i < 100; i++) {
            result += i;
        }

        tryComplete();
        System.out.println("Y finished in " + Thread.currentThread().getName());
    }

    @Override
    public Integer getRawResult() {
        return result;
    }
}
