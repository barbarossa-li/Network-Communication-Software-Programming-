import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.util.regex.Matcher;

import java.util.regex.Pattern;

/**

* java实现统计D:/aa.txt文件中出现的字母个数、数字个数、汉字个数、空格个数及行数

*

*/

public class WordCount {
/**

* @param args

* @throws FileNotFoundException

*/

public static void main(String[] args) throws FileNotFoundException {
String name = "D:/aa.txt"; //文件名

int num = 0; //数字数

int letter = 0; //字母数

int line = 0; //行数

int space = 0; //空格数

int word= 0; //汉字数

try{
File file=new File(name);

BufferedReader br= new BufferedReader(new FileReader(file));

String str = null;

while((str=br.readLine())!=null){
System.out.println(str);

line++;

num += countNumber(str);

letter += countLetter(str);

word += countChinese(str);

space += countSpace(str);

}

}catch(Exception e){
e.printStackTrace();

}

System.out.println("数字数："+num);

System.out.println("字母数"+letter);

System.out.println("汉字数"+word);

System.out.println("空格数"+space);

System.out.println("行数"+line);

}

/**

* 统计数字数

* @param str

* @return

*/

public static int countNumber(String str) {
int count = 0;

Pattern p = Pattern.compile("\\d");

Matcher m = p.matcher(str);

while(m.find()){
count++;

}

return count;

}

/**

* 统计字母数

* @param str

* @return

*/

public static int countLetter(String str) {
int count = 0;

Pattern p = Pattern.compile("[a-zA-Z]");

Matcher m = p.matcher(str);

while(m.find()){
count++;

}

return count;

}

/**

* 统计汉字数

* @param str

* @return

*/

public static int countChinese(String str) {
int count = 0;

Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");

Matcher m = p.matcher(str);

while(m.find()){
count++;

}

return count;

}

/**

* 统计空格数

* @param str

* @return

*/

public static int countSpace(String str) {
int count = 0;

Pattern p = Pattern.compile("\\s");

Matcher m = p.matcher(str);

while(m.find()){
count++;

}

return count;

}

}
————————————————
版权声明：本文为CSDN博主「weixin_39990029」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/weixin_39990029/article/details/114516716
