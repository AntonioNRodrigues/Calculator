import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Command line runner for the postfix calculator.
 *
 * Usage: RunCalculator [input-file] [output-file]
 * Defaults: input-file=data.txt, output-file=result2.txt
 *
 * @author docentes de labp
 */
public class RunCalculator {

	public static void main(String[] args) throws IOException {
		String input = args.length > 0 ? args[0] : "data.txt";
		String output = args.length > 1 ? args[1] : "result2.txt";

		try (BufferedReader data = new BufferedReader(new FileReader(input));
				BufferedWriter out = new BufferedWriter(new FileWriter(output))) {

			Calculator ex = new Calculator(out);
			String line;
			while ((line = data.readLine()) != null) {
				ex.processLine(line);
			}
		}
	}
}
