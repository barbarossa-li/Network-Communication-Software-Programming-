package ch4;
import java.io.*;
import java.util.regex.*;
public class test{
	
	public static void main(String[] args) {
		BufferedReader in;
		Pattern pattern = Pattern.compile("//(//d{3}//)//s//d{3}-//d{4}");
		try {
			in = new BufferedReader(new FileReader("phone"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s;
		try {
			while ((s = in.readLine()) != null){
				Matcher matcher = pattern.matcher(s);
			   if (matcher.find()) {
			       System.out.println(matcher.group());
			      }
			 in.close();
    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}

	
	
	
	
   
