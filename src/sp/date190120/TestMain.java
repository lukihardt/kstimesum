package sp.date190120;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.*;


public class TestMain {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\a.html");
        StringBuilder buffer = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file),"gbk");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//		InputStream inputStream = null;

        String str;
//将数据流写入buffer
        while ((str = bufferedReader.readLine())!= null) {
//			System.out.println(str);
            buffer.append(str);
        }
        String ht;
        ht = buffer.toString();

//        System.out.println(ht);

/*//使用正则表达式带标签匹配
        Pattern p = Pattern.compile("<span class=\"f-fr f-thide kstime\">[0-9]{2}:[0-9]{2}");
        Matcher m = p.matcher(ht);

        while( m.find()){
            System.out.println("Group"+ m.group());
        }
*/

///////////////////////////////直接匹配“数字数字:数字数字”
        Pattern p2 = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher m2 = p2.matcher(ht);
        ArrayList<String> strMany = new ArrayList<>();
        int i = 0;
        while( m2.find()){
            System.out.println("Group"+ i +"--"+ m2.group());
//            strMany[i] = m2.group();
            strMany.add(m2.group());//将匹配到的字符串分组送入strMany
            i++;
        }

/*遍历原始结果
        for (String t: strMany) {
            System.out.println(t);
        }
*/

//////////////////////////截取头两位数字
//        String[] strFirstTwo = new String[218];
        ArrayList<String> strFirstTwo = new ArrayList<>();
        for(int t = 0; t < strMany.size(); t++){
            strFirstTwo.add(strMany.get(t).substring( 0, 2));
        }

/*//遍历头两个字符串结果
        for (String t: strFirstTwo) {
            System.out.println(t);
        }
*/

////////////////////////结果转数字
//      Integer[] resultNum= new Integer[218];
        ArrayList<Integer> resultNum = new ArrayList<>();
        for(int t=0; t < strFirstTwo.size();t++){
            resultNum.add(Integer.parseInt(strFirstTwo.get(t)));
            System.out.println("Num"+ t + "--" + resultNum.get(t));//打印头两位数字
        }
      ////////////////////////求分钟和
        Integer rsSumMinute = 0;
        for (int t=0; t<resultNum.size(); t++) {
            rsSumMinute+=resultNum.get(t);
        }
        System.out.println(rsSumMinute);//打印分钟和

    }
}


/*下面代码摘自网络
response.setContentType("multipart/form-data");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			// 2.设置文件头：最后一个参数是设置下载文件名
			response.setHeader("Content-Disposition", "attachment;fileName=" + file_name);
			ServletOutputStream out;
			// 通过文件路径获得File对象
			html_file_path = html_file_path.replace("\\", "/");
			File html_file = new File(html_file_path);
			FileInputStream inputStream = new FileInputStream(html_file);

			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();

			int b = 0;
			byte[] buffer = new byte[1024];
			while ((b = inputStream.read(buffer)) != -1) {
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
			out.flush();
			out.close();
*/