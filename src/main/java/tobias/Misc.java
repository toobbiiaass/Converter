package tobias;

import tobias.Objects.Logger;
import tobias.Objects.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Misc {
    static ArrayList<Name> names = new ArrayList<>();
    public static void renameMiscs(String olddestination){
        try {
            Scanner scanner = new Scanner(new File("misc.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lines = line.split(";");
                names.add(new Name(lines[0], lines[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int j = 0; j < 2; j++) {
            String add = "";
            if (j == 1) {
                add = ".mcmeta";
            }
            for (int i = 0; i < names.size(); i++) {
                File file = new File(olddestination + "\\" + names.get(i).getOldName() + add);
                if (!file.exists()) {
                    break;
                }
                File rename = new File(olddestination + "\\" + names.get(i).getNewName() + add);
                boolean flag = file.renameTo(rename);
                if (flag == true) {
                    Main.addLog(new Logger(names.get(i).getNewName()," renamed to", LocalTime.now()));
                } else {
                   // System.err.println(names.get(i).getNewName() + add + " error!");
                }
            }
        }

    }
}
