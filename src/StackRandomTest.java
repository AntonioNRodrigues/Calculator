import java.util.Random;

/**
 * A class to test a Stack
 *
 * @author Luis Reis
 * @version $Id: StackRandomTest.java 478 2009-03-27 18:44:43Z vv $
 */
public class StackRandomTest {

	public static void main(String[] args) {
		new StackRandomTest (new ArrayStack<Integer> (), 10111).run();
	}
	
    private Random rand;

    private Stack<Integer> stack;

    private int howMany;

    private static int MAX_OPERATIONS=5;
    
    private static int MAX_INTEGER=11;

    public StackRandomTest (Stack<Integer> stack, int howMany) {
        this.stack = stack;
        rand = new Random ();
        this.howMany = howMany;
    }

    private Integer newInteger() {
        if (rand.nextInt(MAX_INTEGER) == 0)
            return null;
        return newNonNullObject();
    }

    private Integer newNonNullObject() {
        return new Integer(rand.nextInt(MAX_INTEGER));
    }

    public void run() {
        for (int i = 0; i < howMany; i++) {
            switch (rand.nextInt(MAX_OPERATIONS)) {
            case 0:
                System.out.print ("push ");
                stack.push(newInteger());
                break;
            case 1:
                if (!stack.isEmpty()) {
                    System.out.print ("top ");
                    stack.peek();
                }
                break;
            case 2:
                if (!stack.isEmpty()) {
                    System.out.print ("pop ");
                    stack.pop();
                }
                break;
            case 3:
                System.out.print ("isEmpty ");
                stack.isEmpty();
                break;
            case 4:
                System.out.print ("make ");
                stack = new ArrayStack<Integer>();
                break;
            }
        }
    }
}
