import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetIpDemo {
/**
* 从网页上获取数据
* @param myUrl URL地址
* @return 网页上的数据，string类型
*/
public static String getContentFromUrl(String myUrl,String charset) {
StringBuffer sb = new StringBuffer();
URL url;
try {
url = new URL(myUrl);
URLConnection conn = url.openConnection();
InputStream is = conn.getInputStream();
Scanner sc = new Scanner(is,charset);
while(sc.hasNextLine()){
sb.append(sc.nextLine()).append("\r\n");
}
sc.close();
is.close();
} catch (MalformedURLException e) {
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return sb.toString();
}
/**
* 功能：保存数据到文件中
* @param content 要保存的内容
* @param fileName 目标文件名（路径）
*/
public static boolean writeContentToFile(String content,String fileName) {
boolean sign = false;
//把数据存入在文件中
try {
PrintWriter pw = new PrintWriter(fileName);
pw.println(content);
pw.flush();
pw.close();
sign = true;
} catch (FileNotFoundException e) {
e.printStackTrace();
sign = false;
}
return sign;
}
/**
* 功能：从文件里读取数据
* @param fileName 文件名（路径）
* @return 文件的文本内容
*/
public static String getContentFromFile(String fileName) {
StringBuffer content = new StringBuffer();
try {
Scanner sc = new Scanner(new FileInputStream(fileName));
while(sc.hasNextLine()){
content.append(sc.nextLine()).append("\r\n");
}
} catch (FileNotFoundException e) {
e.printStackTrace();
}
return content.toString();
}
/**
* 功能：获取ip地址，从文本内容里
* @param content
*/
public static String getIpFromContent(String content) {
int beginIndex = content.indexOf("[");
int endIndex = content.indexOf("]");
String ip = content.substring(beginIndex+1,endIndex);
return ip;
}
/**
* 功能：获取运营商名称，从文本内容里
* @param content
*/
public static String getSpFromContent(String content) {
int beginIndex = content.indexOf("来自：");
int endIndex = content.indexOf("</center>");
String ip = content.substring(beginIndex+3,endIndex);
return ip;
}
public static void test() {
String name = "wangzongxing";
int beginIndex = name.indexOf("w");
int endIndex = name.indexOf("g");
String subName = name.substring(beginIndex, endIndex+1);
System.out.println(subName);
}
public static void main(String[] args) {
//String content = getContentFromUrl("http://1212.ip138.com/ic.asp", "gb2312");
//writeContentToFile(content, "c.html");
String content = getContentFromFile("c.html");
System.out.println(getSpFromContent(content));
  		File file = new File("C:/robots.txt");
		FileInputStream fis = null;
 
		try {
			fis = new FileInputStream(file);
 
			System.out.println("Total file size to read (in bytes) : "
					+ fis.available());
 
			int content;
			while ((content = fis.read()) != -1) {
				// convert to char and display it
				System.out.print((char) content);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
}
} 
