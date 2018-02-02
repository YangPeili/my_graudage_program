package data_preprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yang on 2018/1/18.
 */
public class LogAnalyzer {
    public static void executor(String sourcePath, String desPath, String start, String end) {
        File file = new File(sourcePath);
        File fileDes = new File(desPath);
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedWriter = new BufferedWriter(new FileWriter(fileDes));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                if (s.contains(start)) {
                    s = bufferedReader.readLine();
                    while (!s.contains(end)) {
                        System.out.println(s);
                        bufferedWriter.write(s);
                        bufferedWriter.flush();
                        bufferedWriter.newLine();
                        s = bufferedReader.readLine();
                    }
                    break;
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> getSubUtil(String soap, String rgex) {
        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while (m.find()) {
            int i = 0;
            list.add(m.group(i));
            i++;
        }
        return list;
    }


    public static void getIterationResult(String sourcePath, String desPath) {
        File file = new File(sourcePath);
        File fileDes = new File(desPath);
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedWriter = new BufferedWriter(new FileWriter(fileDes));
            String s;
            String regex = "(\\b(accuracy|loss|lr))(.+?)(([0-9]\\.[0-9]{1,})\\b)";

//            String regex1="\\b(Iteration)[0-9]\\b";

            while ((s = bufferedReader.readLine()) != null) {
                if ((s.contains("Test net output"))) {
                    s = "test_" + getSubUtil(s, regex).get(0);
                    System.out.println(s);
                    bufferedWriter.write(s);
                    bufferedWriter.flush();
                    bufferedWriter.newLine();

                } else if (s.contains("Train net output")) {
                    s = "train_" + getSubUtil(s, regex).get(0) ;
                    System.out.println(s);
                    bufferedWriter.write(s);
                    bufferedWriter.flush();
                    bufferedWriter.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertToJson(String sourcePath, String desPath) {
        File file = new File(sourcePath);
        File fileDes = new File(desPath);
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            bufferedWriter = new BufferedWriter(new FileWriter(fileDes));
            String s;

            String result="\"value\":"+"[";
            int count=2;
            int iter=0;
            String temp="";
            while ((s = bufferedReader.readLine()) != null){
                count++;
                if(((count%5)!=1)&&((count%5)!=2)){
                    String[] str=s.split("=");
                    if(count%5==0){
                        temp="{\"model\":\"AlexNet\",\"version\":\"1.0.000.1\",\"iter\":"+(iter/5)*200+","+temp+"\""+str[0]+"\""+":"+str[1]+"}";
                        result=result+temp+",\n";
                        temp="";
                        count=0;
                    }else{
                        temp=temp+"\""+str[0].trim()+"\""+":"+str[1].trim()+",";
                    }
                }
                iter++;
            }
            result=result.substring(0,result.length()-1)+"]";
           // System.out.println(result);
            bufferedWriter.write(result);
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        executor("C:\\Users\\yang\\Downloads\\d1enumerations\\auto-modeler-cifar10-models\\model-0\\logging.txt"
//                , "E:\\model\\model0\\solver.prototxt","\"Initializing solver from parameters\"","Creating training net from net file");

//        executor("C:\\Users\\yang\\Downloads\\d1enumerations\\auto-modeler-cifar10-models\\model-0\\logging.txt","E:\\model\\model0\\train.prototxt",
//                "Initializing net from parameters","Creating layer");
        getIterationResult("C:\\Users\\yang\\Downloads\\d1enumerations\\auto-modeler-cifar10-models\\model-50\\logging.txt",
                "E:\\model\\model0\\result2.txt");

        convertToJson("E:\\model\\model0\\result2.txt",
                "E:\\model\\model0\\resultJson2.txt");


    }
}
