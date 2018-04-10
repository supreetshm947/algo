import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AlphaEncryption {

	public static char getDecodedChar(char a) {
		int decoded = (int) a + 3;
		if (decoded > 'z')
			return (char) (decoded - 26);
		return (char) decoded;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		StringBuilder out = new StringBuilder();
		List<Character> charList = in.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
		charList.forEach(li -> out.append(getDecodedChar(li)));
		System.out.println(out);
		scan.close();
	}

}
