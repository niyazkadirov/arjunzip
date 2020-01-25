package com.example.exampleArj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Евгений
 */
public class CopyFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String dir1 = "D://A1 Test//In";
        String dir2 = "D://A1 Test//Out";
        boolean del = true;
        boolean scan = true;


        CopyFiles.scannerDir(dir1, dir2, del, scan);
    }
    public static class CopyFiles {

        public static void copyFile(File in, File out) throws IOException {

            byte buffer[] = new byte[100000000];
            try {
                FileInputStream fileIn = new FileInputStream(in);
                int bytes = fileIn.read(buffer,0,100000000);
                fileIn.close();

                FileOutputStream fileOut = new FileOutputStream(out);
                fileOut.write(buffer,0,bytes);
                fileOut.close();
            }
            catch (Exception e) {

            }
        }

        public static void scannerDir(String dir1, String dir2, boolean del, boolean scan) throws IOException {
            int i = 0;
            File[] list = new File(dir1).listFiles();
            for (i = 0; i < list.length; i++)
            {
                File f = new File (dir1 +"//"+ list[i].getName());
                File f2 = new File (dir2 +"//"+ list[i].getName());
                if (!f.isDirectory()) {
                    try {
                        CopyFiles.copyFile(f, f2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File " + list[i] + " has been copy in " +dir2);
                }
                if (scan == true){
                    if (f.isDirectory()) {
                        CopyFiles.scannerDir(f.getAbsolutePath(), dir2, del, scan);
                    }
                }
                System.gc();
                if ((del == true) && f.isFile()) {
                    if (f.delete()){
                        System.out.println("File " + dir1 +"//"+ list[i] + " is delete from "+dir2);
                    }
                }
            }
        }
    }
}