package tobias;

import tobias.Objects.Logger;
import tobias.Objects.NameParticles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Particle {
   private static ArrayList<NameParticles> names = new ArrayList<>();
    public static void particlesConverter(String destinationFolder, String to, String entityFishingHookPath) {

        File particlesImage = new File(destinationFolder + "\\particles.png");
        if(!particlesImage.exists()) return;
        int multi = 1;
        BufferedImage image = null;
        try {
            image = ImageIO.read(particlesImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        multi = image.getWidth() / 128;

            //read ->
        try {
            Scanner scanner = new Scanner(new File("particl.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lines = line.split(";");
                names.add(new NameParticles(lines[0], Integer.parseInt(lines[1]),Integer.parseInt(lines[2])));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < names.size(); i++) {
            try {
                BufferedImage background = ImageIO.read(new File("overlay" + multi * 8 + ".png"));
                BufferedImage overlay = ImageIO.read(particlesImage);
                BufferedImage result = new BufferedImage(background.getWidth(), background.getHeight(), BufferedImage.TYPE_INT_ARGB);

                int x1 = names.get(i).getX() * multi;
                int y1 = names.get(i).getY() * multi;
                int mULTIXY = 8 * multi;
                int xOutLine = x1 + mULTIXY;
                int yOutLine = y1 + mULTIXY;
                for (int x = x1; x < xOutLine; x++) {
                    for (int y = y1; y < yOutLine; y++) {
                        Color overlayColor = new Color(overlay.getRGB(x, y), true);

                        if (!(overlayColor.getAlpha() == 0)) {
                            result.setRGB(x - x1, y - y1, overlay.getRGB(x, y));
                        }
                    }
                }
                File output = null;
                if(names.get(i).getName().equals("fishing_hook.png")){
                     output = new File(entityFishingHookPath+"\\"+names.get(i).getName());
                }else{
                     output = new File(to + "\\"+names.get(i).getName());
                }

                ImageIO.write(result, "png", output);
                Main.addLog(new Logger(names.get(i).getName()," added particle", LocalTime.now()));
               // TODO System.out.println("added critical hit particle"); LOGGER
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
