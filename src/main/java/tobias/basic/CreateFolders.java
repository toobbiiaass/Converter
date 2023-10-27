package tobias.basic;

import tobias.Main;
import tobias.Objects.Logger;

import java.io.File;
import java.time.LocalTime;

public class CreateFolders {
    //Darin werden alle folder erstellt
    String[] foldersInTextures = {"gui","item","models","particle","entity"};
    public void createAllFolders(String destinationFolder){
        File newAssets = new File(destinationFolder+"\\assets");
        newAssets.mkdir();
        Main.addLog(new Logger("assets folder"," created", LocalTime.now()));

        File newMine = new File(destinationFolder+"\\assets\\minecraft");
        newMine.mkdir();
        Main.addLog(new Logger("minecraft folder"," created", LocalTime.now()));

        File newTextures = new File(destinationFolder+"\\assets\\minecraft\\textures");
        newTextures.mkdir();
        Main.addLog(new Logger("textures folder"," created", LocalTime.now()));
        String filePath = destinationFolder+"\\assets\\minecraft\\textures";
        for (int i = 0; i < foldersInTextures.length; i++) {
            File newFolderInTextures = new File(filePath+"\\"+foldersInTextures[i]);
            newFolderInTextures.mkdir();
            Main.addLog(new Logger(foldersInTextures[i]," created", LocalTime.now()));

        }
        File newModelsArmor = new File(filePath+"\\models\\armor");
        newModelsArmor.mkdir();
        Main.addLog(new Logger("armor folder"," created", LocalTime.now()));

        File newGuiAdvance = new File(filePath+"\\gui\\advancements");
        newGuiAdvance.mkdir();
        Main.addLog(new Logger("advancements folder"," created", LocalTime.now()));
    }
}
