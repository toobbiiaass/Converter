package tobias;

import tobias.Objects.Logger;
import tobias.Objects.Name;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Items {
    static ArrayList<Name> names = new ArrayList<>();

    public void createItems(String destinationFolder, String oldPath) {
        try {
            Scanner scanner = new Scanner(new File("items.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lines = line.split(";");
                names.add(new Name(lines[0], lines[1]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String filePath = destinationFolder + "\\assets\\minecraft\\textures\\item";
            for (int j = 0; j < 2; j++) {
                String add = "";
                if (j == 1) {
                    add = ".mcmeta";
                }
                for (int i = 0; i < names.size(); i++) {
                    File redstoneOldy = new File(oldPath + "\\" + names.get(i).getOldName() + add);
                    if (redstoneOldy.exists()) {
                        try {
                            if (redstoneOldy.renameTo(new File(filePath + "\\" + names.get(i).getNewName() + add))) {
                                Main.addLog(new Logger(names.get(i).getNewName()," renamed to", LocalTime.now()));
                            } else {
                               // System.out.println("File is failed to move! " + names.get(i).getNewName() + add);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                      //  System.err.println(names.get(i).getNewName() + add + " does not exist");
                   }
              }
        }
    }
}
