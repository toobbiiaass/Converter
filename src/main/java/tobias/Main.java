package tobias;

import tobias.Objects.Logger;
import tobias.basic.CreateFolders;
import tobias.gui.RenameGui;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Logger> logs = new ArrayList<>();

   private static String outputPathTP = "";

    private static String destinationFolder = "";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a path where the converted tp should be saved!");
        String oup = scanner.nextLine();
        outputPathTP = oup+"\\";

        System.out.println("\nSpecify the folder that should be converter!\n Must be 1.8 Tp and unpacked (directory where pack.mcmeta file is \n...");
        String tpPath = scanner.nextLine();
      while (true){
             System.out.println("Specify the name of the future tps: ");
            String ordnerName = scanner.nextLine();
            File newOrdner = new File(outputPathTP+ordnerName);
            if(newOrdner.exists()){
                System.out.println("This name already exists!");
            }else {
                newOrdner.mkdir();
                System.out.println(ordnerName+" folder was created!");
                destinationFolder = outputPathTP+ordnerName;
                break;
            }
        }
        System.out.println("Should Netherite Stuff be created (template: Diamond) -> y/n");
        String netherite = scanner.nextLine();
        boolean netheriteBol=false;
        if(netherite.equals("y")){
            netheriteBol = true;
        }

        makeConvert(tpPath, netheriteBol);
        for(Logger l : logs){
            System.out.println(l.getInfo()+": "+l.getName());
        }
        logs = new ArrayList<>();
    }
    private static void makeConvert(String tpPath, boolean netheriteGenerate){
        File packMCMeta = new File(destinationFolder+"\\pack.mcmeta");
        try {
            packMCMeta.createNewFile();
            try {
                FileWriter myWriter = new FileWriter(destinationFolder+"\\pack.mcmeta");
                myWriter.write("{\n");
                myWriter.write("  \"pack\": {\n");
                myWriter.write(" \"pack_format\": 15,\n");
                myWriter.write("\"description\": \"converted by vuacy bot\"\n");
                myWriter.write("  }\n");
                myWriter.write("}\n");
                myWriter.close();
                System.out.println("pack.mcmeta wurde was created und edited!");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File packPNG = new File(tpPath+"\\pack.png");
        if(packPNG.exists()){
            try {
                if(packPNG.renameTo(new File(destinationFolder+"\\"+ packPNG.getName()))) {
                    System.out.println("pack.png was moved!");
                } else {
                    System.out.println("File is failed to move!");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        //Alle Folder erstellen
        CreateFolders newFolders = new CreateFolders();
        newFolders.createAllFolders(destinationFolder);

        //Alle Items
        Items items = new Items();
        items.createItems(destinationFolder,tpPath+"\\assets\\minecraft\\textures\\items");

        //Add all from environment
        moveDirc(new File(tpPath+"\\assets\\minecraft\\textures\\environment"),new File(destinationFolder+"\\assets\\minecraft\\textures\\environment"),"environment");
        //Add all from font
        moveDirc(new File(tpPath+"\\assets\\minecraft\\textures\\font"),new File(destinationFolder+"\\assets\\minecraft\\textures\\font"),"font");
        //Add all from misc
        moveDirc(new File(tpPath+"\\assets\\minecraft\\textures\\misc"),new File(destinationFolder+"\\assets\\minecraft\\textures\\misc"),"misc");
        //Add all from armor
        moveDirc(new File(tpPath+"\\assets\\minecraft\\textures\\models\\armor"),new File(destinationFolder+"\\assets\\minecraft\\textures\\models\\armor"),"armor");
        //Add all from optifine
        moveDirc(new File(tpPath+"\\assets\\minecraft\\mcpatcher"),new File(destinationFolder+"\\assets\\minecraft\\optifine"),"optifine");
        //Add all from blocks
        moveDirc(new File(tpPath+"\\assets\\minecraft\\textures\\blocks"),new File(destinationFolder+"\\assets\\minecraft\\textures\\block"),"blocks");


        //Alle Blöcke
        Blocks blocks = new Blocks();
        blocks.renameBlocks(destinationFolder+"\\assets\\minecraft\\textures\\block");
        //Alle Misc ändern
        Misc misc = new Misc();
        misc.renameMiscs(destinationFolder+"\\assets\\minecraft\\textures\\misc");

        //Rename Guis
        RenameGui renameGui=new RenameGui();
        renameGui.renameGuis(tpPath+"\\assets\\minecraft\\textures\\gui",destinationFolder+"\\assets\\minecraft\\textures\\gui");

        Particle particle = new Particle();
        particle.particlesConverter(tpPath+"\\assets\\minecraft\\textures\\particle",destinationFolder+"\\assets\\minecraft\\textures\\particle",destinationFolder+"\\assets\\minecraft\\textures\\entity");

        copyFile(Paths.get("fire_0.png.mcmeta"),Paths.get(destinationFolder+"\\assets\\minecraft\\textures\\block\\fire_0.png.mcmeta"),"fire_0.png.mcmeta");
        copyFile(Paths.get("fire_1.png.mcmeta"),Paths.get(destinationFolder+"\\assets\\minecraft\\textures\\block\\fire_1.png.mcmeta"),"fire_1.png.mcmeta");

        if(netheriteGenerate){
            AddNetherite netherite = new AddNetherite();
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_sword.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_sword.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_axe.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_axe.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_hoe.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_hoe.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_shovel.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_shovel.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_pickaxe.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_pickaxe.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_boots.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_boots.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_chestplate.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_chestplate.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_helmet.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_helmet.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\item\\diamond_leggings.png",destinationFolder+"\\assets\\minecraft\\textures\\item\\netherite_leggings.png");

            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\models\\armor\\diamond_layer_1.png",destinationFolder+"\\assets\\minecraft\\textures\\models\\armor\\netherite_layer_1.png");
            netherite.createItem(destinationFolder+"\\assets\\minecraft\\textures\\models\\armor\\diamond_layer_2.png",destinationFolder+"\\assets\\minecraft\\textures\\models\\armor\\netherite_layer_2.png");
        }


    }

    private static void copyFile(Path source, Path target, String name){
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
            System.out.println("Copied "+name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void moveDirc(File from,File to,String name){
        if(from.exists()){
            try {
                Files.move(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println( name+" directory moved successfully.");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }else{
            System.err.println(name+" dont exist");
        }

    }
    public static void addLog(Logger log){
        logs.add(log);
    }

}