package tobias.gui;

import tobias.Main;
import tobias.Objects.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class RenameGui {
    private static String[] oldNames = {"widgets.png"};
    private static String[] newNames = {"widgets.png"};
    public static void renameGuis(String olddestination,String newDestionation){
        for (int i = 0; i < oldNames.length; i++) {
            File file = new File(olddestination+"\\"+oldNames[i]);
            if(!file.exists()){
                break;
            }
            File rename = new File(newDestionation+"\\"+newNames[i]);
            boolean flag = file.renameTo(rename);
            if (flag == true) {
                Main.addLog(new Logger(newNames[i]," renamed to", LocalTime.now()));
            }
            else {
                //System.err.println(newNames[i]+" error!");
            }
        }
        File particlesImage = new File(olddestination+"\\icons.png");
        int multi = 1;
        BufferedImage image = null;
        try {
            image = ImageIO.read(particlesImage);
        } catch (IOException e) {
            e.printStackTrace();

        }
        multi = image.getWidth() / 256;
        try {
            BufferedImage background = ImageIO.read(new File(olddestination+"\\icons.png"));
            BufferedImage overlay = ImageIO.read(new File("sword" + multi + ".png"));
            int x1 = 0 * multi;
            int y1 = 94* multi;
            int mULTIX = 84 * multi;
            int mULTIY = 18 * multi;
            int xOutLine = x1 + overlay.getWidth();
            int yOutLine = y1 + overlay.getHeight();
            for (int x = x1; x < xOutLine; x++) {
                for (int y = y1; y < yOutLine; y++) {

                    Color overlayColor = new Color(overlay.getRGB(x, y - y1), true);

                    if (!(overlayColor.getAlpha() == 0)) {
                        background.setRGB(x, y, overlay.getRGB(x, y-y1));
                    }
                }
            }
            File output = new File(newDestionation+"\\icons.png");
            ImageIO.write(background, "png", output);
            Main.addLog(new Logger("icons.png"," modified", LocalTime.now()));
            System.out.println("icons modified");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
