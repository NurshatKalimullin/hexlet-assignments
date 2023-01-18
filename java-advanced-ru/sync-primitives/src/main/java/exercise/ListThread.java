package exercise;

import java.util.concurrent.ThreadLocalRandom;

// BEGIN
public class ListThread extends Thread {

    SafetyList list;
    public ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < 1000) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 10);
            list.add(randomNum);
            i++;
        }
    }

}
// END
