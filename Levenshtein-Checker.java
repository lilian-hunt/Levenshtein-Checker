import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class SpellChecker {
	 public static boolean isPunctuation(char c) {
        return c == ','
            || c == '.'
            || c == '!'
            || c == '?'
            || c == ':'
            || c == ';'
			|| c == ' '
            ;
    }
    public static int calculate(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
 	public static String checkCase(String s, String ret){
		if (s.equals(s.toUpperCase())){
			return ret.toUpperCase();
		}
		else if (s.equals(s.toLowerCase())){
			return ret.toLowerCase();
		}
		else {
			String rett = "";
			rett += String.valueOf(ret.charAt(0)).toUpperCase();
			rett += ret.substring(1,ret.length());
			return rett;
		}
		
	}
    public static void main(String[] args) {
		try {
			File file1 = new File(args[1]);
			File file2 = new File(args[0]);
		
		ArrayList<String> dict = new ArrayList<String>();
		Scanner read1 = new Scanner(file1);
		Scanner read2 = new Scanner(file2);
		String out = "";
		while (read2.hasNext()){
			dict.add(read2.next());
		}
		while(read1.hasNextLine()){
			String line = read1.nextLine();
			String str = line.replaceAll("[a-zA-Z]", "@");
			String newline = "";
			String[] tokens = line.split("[.!; :?,]+");
			String[] finalArray = new String[tokens.length];
			int idx = 0;
    		for (String word : tokens) {
				String replacement = "";
				int distance = 0;
				for (int i = 0; i<dict.size();i++){
					if (i==0){
						distance = calculate(word,dict.get(i));
						replacement = checkCase(word,dict.get(i));
					}
					else if (calculate(word,dict.get(i)) <distance){
						distance = calculate(word,dict.get(i));
						replacement = checkCase(word,dict.get(i));	
					}
				}
				finalArray[idx] = (replacement);
				idx ++;
			}
			String fToPrint ="";
			boolean newWord = false;
			int wordCount = 0;
			for (int i = 0; i < str.length(); i++){
				if (str.charAt(i)== '@'&&i==0){
					fToPrint += finalArray[wordCount];
					wordCount++;
				}
				else if (str.charAt(i)=='@' && newWord==false){
				}
				else if (str.charAt(i) != '@'){
					newWord = true;
					if (isPunctuation(str.charAt(i))){
						fToPrint += str.charAt(i);
					}
				}
				else if (str.charAt(i) =='@' && newWord==true){
					newWord = false;
					fToPrint += finalArray[wordCount];
					wordCount ++;
				}
			}
			System.out.println(fToPrint);
		}
		}
		catch (FileNotFoundException e){

		}
    }
}
