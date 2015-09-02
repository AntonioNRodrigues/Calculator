import java.io.BufferedWriter;
import java.io.IOException;


/**
 * @author antonio
 *
 */
public class Calculator{
	private Stack<Integer> pilha;
	private BufferedWriter out;
	private int x;
	
	public Calculator(BufferedWriter out) throws IOException {
		pilha = new ArrayStack<Integer>();
		this.out = out;
	}

	private void realizaOperacao(int a, int b , String oper) {
		switch (oper) {
		case "+":
			pilha.push(a + b);	
			break;
		case "-":
			if(b<a){
				int valor = (a - b);
				pilha.push(valor);
			}if(b>a){
				pilha.push(a - b);
			}
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
			break;
		}	
	}
	
	public void processLine(String line) throws IOException {
		
		if(line.charAt(0) != '-'){
			String [] aux = line.split(" ");
			
			for(String str: aux){
				if(str.matches("[0-9]*")){
					pilha.push(Integer.parseInt(str));
				}
				else if(str.equals("+") || str.equals("/") || str.equals("-") 
						|| str.equals("*") || str.equals("%")){
					int valor = pilha.peek();
						pilha.pop();
					int valor2 = pilha.peek();
						pilha.pop();
					realizaOperacao(valor2, valor, str);
				}
				else if(str.equals("dup")){
					pilha.push(pilha.peek());
				}
				else if(str.equals("drop")){
					pilha.pop();
				}
				else if(str.equals("swap")){
					int valor = pilha.peek();
						pilha.pop();
					int valor2 = pilha.peek();
						pilha.pop();
					pilha.push(valor);
					pilha.push(valor2);
				}
				else if(str.equals("@x")){
					this.x = pilha.peek();
					pilha.pop();
				}
				else if(str.equals("x")){
					pilha.push(x);
				}
				else if(str.equals(".")){
					out.write(pilha.peek()+"\n");
					//System.out.println(pilha);
					pilha.pop();
				}
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
