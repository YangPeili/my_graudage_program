package IO;

import java.io.*;

/**
 * Created by yang on 2017/12/22.
 */
public class FileOperation {
    public static void readFiles(String path) {
        File file = new File(path);

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedWriter wirteToFile(String path, String fileName) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        BufferedWriter bufferedWriter = null;
        try {
            System.out.println(path + fileName);
            File file1 = new File(path + fileName);
            bufferedWriter = new BufferedWriter(new FileWriter(file1));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bufferedWriter;
    }
}
