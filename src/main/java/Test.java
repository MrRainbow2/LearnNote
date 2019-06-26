import java.io.*;

/**
 * @author renxinlei
 * @version V1.0
 * @Description:
 * @date 2019/6/16 16:31
 */
public class Test {
    public static void main(String args[]) {
        try {
            FileReader read = new FileReader("D:/xptest/all.txt");
            BufferedReader br = new BufferedReader(read);
            String row;
            int rownum = 1;
            int fileNo = 1;
            FileWriter fw = new FileWriter("D:/xptest/text" + fileNo + ".txt");
            while ((row = br.readLine()) != null) {
                rownum++;
                fw.append(row + "\r\n");
                if ((rownum / (getRowTotal(br)/6)) > (fileNo - 1)) {
                    fw.close();
                    fileNo++;
                    fw = new FileWriter("D:/xptest/text" + fileNo + ".txt");
                }
            }
            fw.close();
            System.out.println("rownum=" + rownum + ";fileNo=" + fileNo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getRowTotal(BufferedReader fr) {
        int total = 0;
        try {
            fr.mark(1);
            while(fr.readLine()!=null) {
                total++;
            }
            fr.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total;
    }
}