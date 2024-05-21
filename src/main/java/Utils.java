public class Utils {
    public static void sleep(int mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
