package com.example.exampleArj;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Objects;

public class Copy {

    public static void main(String[] args) throws IOException, ArchiveException {

            File original = new File("C:/example/to/apache-activemq-5.15.11/bin/activemq.jar");

            File copied = new File(
                    "C:/out/to/apache-activemq-5.15.11/bin/activemq.jar");
            FileUtils.copyFile(original, copied);

    }


    }


