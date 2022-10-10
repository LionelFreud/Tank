package com.yyedu.TankGame03;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Recorder {
    private static int Tanks = 0;
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "C:\\Users\\11910\\IdeaProjects\\chapter20\\record.txt";

    public static int getTanks() {
        return Tanks;
    }

    public static void setTanks(int tanks) {
        Tanks = tanks;
    }

    public static void add() {
        Tanks++;
    }

    public static void save() throws Exception {
        bw = new BufferedWriter(new FileWriter(recordFile));
        bw.write(Tanks);
        bw.newLine();
        bw.close();
    }
}
