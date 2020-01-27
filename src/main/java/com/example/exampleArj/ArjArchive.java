package com.example.exampleArj;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;

public class ArjArchive {

    public static void main(String[] args) throws IOException, ArchiveException {
        new ArjArchive().unzip("c:/5.arj", "C:/Users/1/Desktop/files");
    }

    private static String getDir(ArjArchiveEntry entry) {
        File file = new File(entry.getName());
        return entry.getName().replaceAll(file.getName(), "");
    }


    public void unzip(String src, String dir) throws IOException, ArchiveException {

        InputStream is = new FileInputStream(new File(src));



        try (ArjArchiveInputStream i = new ArjArchiveInputStream(new FileInputStream(new File(src)))) {
            ArchiveEntry entry = null;

            while ((entry = i.getNextEntry()) != null) {
                if (!i.canReadEntryData(entry)) {
                    System.out.println("Error: can't read" + entry.getName());
                    continue;
                }
                String name = dir + entry.getName();
                File f = new File(name);
                if (entry.isDirectory()) {
                    if (!f.isDirectory() && !f.mkdirs()) {
                        throw new IOException("failed to create directory " + f);
                    }
                } else {
                    File parent = f.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs()) {
                        throw new IOException("failed to create directory " + parent);
                    }
                    try (OutputStream o = Files.newOutputStream(f.toPath())) {
                       IOUtils.copy(i, o);

                        }
                    }
                }
            }
        }
    }

