package com.example.exampleArj;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;

import java.io.*;

import static org.apache.commons.compress.utils.IOUtils.copy;

public class ArjArchive {

    public static void main(String[] args) throws IOException, ArchiveException {
        new ArjArchive().unzip("c:/1.arj", "c:/exampleOne1/");
    }

    private static String getDir(ArjArchiveEntry entry) {
        File file = new File(entry.getName());
        return entry.getName().replaceAll(file.getName(), "");
    }


    public void unzip(String src, String dir) throws IOException, ArchiveException {

        ArjArchiveEntry entry = new ArjArchiveEntry();
        InputStream is = new FileInputStream(new File(src));
        ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream(ArchiveStreamFactory.ARJ, is);
        OutputStream out = null;
        System.out.println(entry.getSize());
        System.out.println(entry.getMode());

        while (true) {

            try {
                entry = (ArjArchiveEntry) in.getNextEntry();


                if (entry != null) {


                    System.out.println("Extraction  " + entry.getName());

                    File filedir = new File(dir + getDir(entry));
                    //if (!filedir.exists()) {
                            filedir.mkdirs();
                    //}
                    out = new FileOutputStream(new File(dir + entry.getName()));

                    //FileUtils.copyFile(filedir, out);
                    try {
                        copy(in, out);
                    } catch (IOException e) {
                        e.getStackTrace();
                    }


                } else {
                    System.out.println("Checkout complete");
                    //in.close();
                }
            } catch (IOException e) {
                e.getStackTrace();
            }
        }

    }
}
