package com.tayhantechnologies.ipace.Entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ExternalCommands {

    public String runCommand() throws IOException {
        Process process = new ProcessBuilder("C:\\development\\ipace\\routegrab.cmd").start();
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String output ="";

        System.out.printf("Output of running is:");

        while ((line = br.readLine()) != null) {
            System.out.println(line);
            output = output + line;
        }
        return output;
    }
}
