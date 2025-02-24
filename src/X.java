import java.util.concurrent.CountedCompleter;
import java.util.function.UnaryOperator;

class X extends CountedCompleter<Integer> {
    private UnaryOperator<Integer> operator;
    private Integer sum = 0;
    private Integer rawResult = 0;
    private Y y;

    public X(CountedCompleter<?> parent, UnaryOperator<Integer> operator) {
        super(parent);
        this.operator = operator;
    }

    @Override
    public void compute() {
        System.out.println("X started in " + Thread.currentThread().getName());

        y = new Y(this);
        setPendingCount(1);
        y.fork();

        for (int i = 0; i < 100; i++) {
            sum += operator.apply(i);
        }

        tryComplete();
        System.out.println("X finished in " + Thread.currentThread().getName());
    }

    @Override
    public void onCompletion(CountedCompleter<?> caller) {
        System.out.println("X completed in " + Thread.currentThread().getName());
        rawResult = sum + y.getRawResult();
    }

    @Override
    public Integer getRawResult() {
        return rawResult;
    }
}