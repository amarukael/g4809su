package utility;

public class AddFunction {
    public void getDelay(int delay) {
        try {
            System.out.println("Delay for " + delay + "ms");
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
