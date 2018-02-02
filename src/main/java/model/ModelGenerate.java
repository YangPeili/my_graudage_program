package model;

import IO.FileOperation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yang on 2017/12/22.
 */
public class ModelGenerate {

    static Random random = new Random();
    static String str[] = {"A", "B", "C", "D", "E", "F", "G", "H", "Y", "Z", "X"};
    static int num[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    static String year[] = {"2013", "2014", "2015"};
    static String month[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
    static String day[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
            "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"};
    static String path = "E://Files//models//";

    public static String generateInputdata() {
        int srtIndex1 = random.nextInt(str.length);
        int srtIndex2 = random.nextInt(str.length);
        int num1;
        String str1 = str[srtIndex1] + str[srtIndex2] + "";
        for (int i = 0; i < 6; i++) {
            num1 = random.nextInt(10);
            str1 = str1 + num1;
        }
        return str1;
    }

    public static String getPath(int i) {
        String newPath = path + "model" + i;
        return newPath;
    }

    public static String generateDate() {

        int y = random.nextInt(3);
        int m = random.nextInt(12);
        int d = random.nextInt(28);
        String date = "" + year[y] + month[m] + day[d];
        return date;
    }

    public static void doGenerateInputData() throws IOException {
        for (int k = 0; k < 10; k++) {
            String newPath = getPath(k);
            BufferedWriter bufferedWriter = FileOperation.wirteToFile(newPath, "input.txt");
            for (int i = 0; i < 2000; i++) {
                int checkCount = random.nextInt(2) + 1;
                String record;
                String pid = generateInputdata();
                for (int j = 0; j < checkCount; j++) {
                    record = "{" + pid + "," + generateDate() + "}";
                    System.out.println(record);
                    bufferedWriter.append(record);
                    bufferedWriter.flush();
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        }
    }

    static String getSubDoc() {
        String subDoc = " \"hyperParameter\": [\n" +
                "        {\n" +
                "            \"type\": \"test_iter\",\n" +
                "            \"value\": 1000.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"test_interval\",\n" +
                "            \"value\": 1000.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"base_lr\",\n" +
                "            \"value\": 0.01\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"gamma\",\n" +
                "            \"value\": 0.1\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"stepsize\",\n" +
                "            \"value\": 100000.0\n" +
                "        },\n" +
                "        {\n" +
                "            \"type\": \"display\",\n" +
                "            \"value\": 20.0\n" +
                "        }\n" +
                "    ],\n" +
                "    \"layers\": [\n" +
                "        {\n" +
                "            \"layerName\": \"data\",\n" +
                "            \"from\": \"null\",\n" +
                "            \"to\": \"data\",\n" +
                "            \"paramters\": [\n" +
                "                {\n" +
                "                    \"parameterType\": \"input_param\",\n" +
                "                    \"value\": \"[5,5,1,4]\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"layerName\": \"conv1\",\n" +
                "            \"from\": \"data\",\n" +
                "            \"to\": \"conv1\",\n" +
                "            \"paramters\": [\n" +
                "                {\n" +
                "                    \"parameterType\": \"input_param\",\n" +
                "                    \"value\": \"[5,5,1,4]\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"layerName\": \"conv2\",\n" +
                "            \"from\": \"conv1\",\n" +
                "            \"to\": \"conv2\",\n" +
                "            \"paramters\": [\n" +
                "                {\n" +
                "                    \"parameterType\": \"input_param\",\n" +
                "                    \"value\": \"[5,5,1,4]\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"layerName\": \"ip1\",\n" +
                "            \"from\": \"conv2\",\n" +
                "            \"to\": \"ip1\",\n" +
                "            \"paramters\": [\n" +
                "                {\n" +
                "                    \"parameterType\": \"input_param\",\n" +
                "                    \"value\": \"[5,5,1,4]\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"modelRelatedFiles\": {\n" +
                "        \"inputData\": {\n" +
                "            \"fileType\": \"CT\",\n" +
                "            \"patientId\": [\n" +
                "                \"fx2369\",\n" +
                "                \"fi1668\",\n" +
                "                \"yw1289\",\n" +
                "                \"xu7868\",\n" +
                "                \"jf2368\",\n" +
                "                \"ki1769\"\n" +
                "            ]\n" +
                "        },\n" +
                "        \"scriptFiles\": [\n" +
                "            \"tran.py\",\n" +
                "            \"draw_net.py\",\n" +
                "            \"detect.py\"\n" +
                "        ],\n" +
                "        \"snapshot\": [\n" +
                "            \"snapshot_iter_2500.caffemodel.h5\",\n" +
                "            \"snapshot_iter_5000.caffemodel.h5\",\n" +
                "            \"snapshot_iter_7500.caffemodel.h5\"\n" +
                "        ]\n" +
                "    }";
        return subDoc;
    }


    static String[] updateType = {"001", "010", "100", "011", "101", "110", "111"};

    public static List<String> generateSimpleVersionInfo(String modelName) throws IOException {
        String newPath = path;
        BufferedWriter bufferedWriter = FileOperation.wirteToFile(newPath, "simple_version_info.txt");
        List<String> list = new ArrayList<String>();
        String init = "1.0.000.1";
        int mainNum = 2;
        String p = ".0.000.1";
        float accuracy = 0;
        int update = random.nextInt(updateType.length);
        int newUpdate = update;
        int count = 1;
        for (int i = 0; i < 1000; i++) {
            // generate version information
            String version = mainNum + p + "." + updateType[newUpdate] + "." + count;
            //generate accuracy information
            accuracy = random.nextFloat();
            if (accuracy < 0.5) {
                accuracy = accuracy + 0.5f;
            }
            String subDoc = getSubDoc();
          // String json0 = "{" + "\"modelName\":\"" + modelName + "\"," + "\"version\":\"" + version + "\"," + "\"accuracy\":" + accuracy + "}";
            String json = "{" + "\"modelName\":\"" + modelName + "\"," + "\"version\":\"" + version + "\"," + "\"accuracy\":" + accuracy + "," + subDoc + "}";
          // list.add(json0);
            list.add(json);
            //写文件
            bufferedWriter.write(json);
        bufferedWriter.flush();
        bufferedWriter.newLine();

        // 是否基于当前主版本再做实验
        boolean ifRepeat = random.nextBoolean();
        if (!ifRepeat) {
            mainNum++;
            p= p + "." + updateType[newUpdate] + "." + count;
        } else {
            update = newUpdate;
            newUpdate = random.nextInt(updateType.length);
            while (update == newUpdate) {//不要重复已经更新的类型
                newUpdate = random.nextInt(updateType.length);
            }
        }

    }
        bufferedWriter.close();
        return list;
    }

   public  static List<String> generateModelsOnlyKonwParent(String modelName){
        List<String> list = new ArrayList<String>();
        String subDoc = getSubDoc();
        int count=1;
        for(int i=0;i<500;i++){
            int pVersion=i;
//            int subVersionNum=random.nextInt(2)+1;
//            for(int j=0;j<subVersionNum;j++){
                double  accuracy = random.nextFloat();
                if (accuracy < 0.5) {
                    accuracy = accuracy + 0.5f;
                }
                String json = "{" + "\"modelName\":\"" + modelName + "\"," + "\"version\":\"" + count+ "\"," +  "\"pVersion\":\"" + pVersion + "\"," +"\"accuracy\":" + accuracy + "," + subDoc + "}";
                count++;
                System.out.println(count);
                list.add(json);
//            }
            System.out.println("i="+i);
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
      // doGenerateInputData();
    generateModelsOnlyKonwParent("LetNet");


    }
}
