import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    private String message = "";

    /**
     * @source: StudentArrayDequeLauncher.java
     * */
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        for (int i = 0; i < 200; i += 1) {
            double randNum = StdRandom.uniform();

            if (randNum < 0.25) {
                sad.addLast(i);
                ads.addLast(i);
                message += "addLast(" + Integer.toString(i) + ")\n";
            } else if (randNum >= 0.25 && randNum < 0.5){
                sad.addFirst(i);
                ads.addFirst(i);
                message += "addLast(" + Integer.toString(i) + ")\n";
            } else if (randNum >= 0.5 && randNum <= 0.75){
                message += "removeFirst()\n";
                assertEquals(message, ads.removeFirst(), sad.removeFirst());
            } else {
                message += "removeLast()\n";
                assertEquals(message, ads.removeLast(), sad.removeLast());
            }
        }
    }
}
