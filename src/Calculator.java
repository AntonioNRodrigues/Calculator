import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A small postfix (Reverse Polish Notation) calculator backed by a stack.
 *
 * Supported tokens: integers, +, -, *, /, %, dup, drop, swap, @x, x and .
 * Lines starting with '-' are treated as comments.
 *
 * @author antonio
 */
public class Calculator {
	private Stack<Integer> pilha;
	private BufferedWriter out;
	private int x;

	public Calculator(BufferedWriter out) throws IOException {
		pilha = new ArrayStack<Integer>();
		this.out = out;
	}

	private void requireOperands(int needed, String oper) {
		if (pilha.size() < needed) {
			throw new IllegalArgumentException(
					"Operator '" + oper + "' requires " + needed + " operand(s), but the stack has "
							+ pilha.size() + ".");
		}
	}

	private int popValue() {
		int value = pilha.peek();
		pilha.pop();
		return value;
	}

	private void realizaOperacao(int a, int b, String oper) {
		if ((oper.equals("/") || oper.equals("%")) && b == 0) {
			throw new ArithmeticException("Division by zero in operator '" + oper + "'.");
		}

		switch (oper) {
		case "+":
			pilha.push(a + b);
			break;
		case "-":
			pilha.push(a - b);
			break;
		case "%":
			pilha.push(a % b);
			break;
		case "/":
			pilha.push(a / b);
			break;
		case "*":
			pilha.push(a * b);
			break;
		default:
			throw new IllegalArgumentException("Unknown operator: " + oper);
		}
	}

	public void processLine(String line) throws IOException {
		if (line == null || line.trim().isEmpty() || line.trim().startsWith("-")) {
			return;
		}

		String[] aux = line.trim().split("\\s+");
		for (String str : aux) {
			if (str.matches("[0-9]+")) {
				pilha.push(Integer.parseInt(str));
			} else if (str.equals("+") || str.equals("/") || str.equals("-")
					|| str.equals("*") || str.equals("%")) {
				requireOperands(2, str);
				int b = popValue();
				int a = popValue();
				realizaOperacao(a, b, str);
			} else if (str.equals("dup")) {
				requireOperands(1, str);
				pilha.push(pilha.peek());
			} else if (str.equals("drop")) {
				requireOperands(1, str);
				pilha.pop();
			} else if (str.equals("swap")) {
				requireOperands(2, str);
				int b = popValue();
				int a = popValue();
				pilha.push(b);
				pilha.push(a);
			} else if (str.equals("@x")) {
				requireOperands(1, str);
				x = popValue();
			} else if (str.equals("x")) {
				pilha.push(x);
			} else if (str.equals(".")) {
				requireOperands(1, str);
				out.write(pilha.peek() + "\n");
				pilha.pop();
			} else {
				throw new IllegalArgumentException("Unknown token: " + str);
			}
		}
	}

	@Override
	public String toString() {
		return "Calculator [pilha=" + pilha + "]";
	}

	public Stack<Integer> getPilha() {
		return pilha;
	}

	public void setPilha(Stack<Integer> pilha) {
		this.pilha = pilha;
	}
}
