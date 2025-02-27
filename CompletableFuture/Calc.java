import java.time.LocalTime;
import java.util.Random;

public class Calc {
    private String s;
    Random random;
    public Calc(String s) {
        this.s = s;
        random = new Random();
    }
    public Integer x(){
        System.out.println(getContext() + "X start");
        int randomInt = random.nextInt(1500)+500;
        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getContext() + "X stop");
        return randomInt;
    }

    public int y(int x){
        System.out.println(getContext() + "Y start");
        int randomInt = random.nextInt(1000)+500;
        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getContext() + "Y stop");
        return 1;
    }

    public void z(int x){
        System.out.println(getContext() + "Z start");
        int randomInt = random.nextInt(2000)+300;
        try {
            Thread.sleep(randomInt);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getContext() + "Z stop");
    }

    private String getContext(){
        return " [Thread: " + Thread.currentThread().getName() + " at " + LocalTime.now() +" ]" + " - context " + s + " - ";
    }
}
