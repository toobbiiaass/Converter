package tobias;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AddNetherite {

    public static void createItem(String oldItemPath, String newItemPath){
        BufferedImage image;
        File exitsIt = new File(oldItemPath);
        if(!exitsIt.exists()){
            return;
        }
        try {
            image = ImageIO.read(new File(oldItemPath));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int width = image.getWidth();
        int height = image.getHeight();
        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {
                int pixel = image.getRGB(col, row);
                int alpha = (pixel >> 24) & 0xFF;
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;
                if(alpha == 255){
                    double L = 0.4126*red + 0.7152*green + 0.0722*blue; // double L = 0.2126*red + 0.7152*green + 0.0722*blue
                    double newR = 71*L/255; //74 -> 35
                    double newG = 58*L/255; // 41 -> 16
                    double newB = 65*L/255; //64 ->18
                    Color newColor = new Color((int) newR, (int) newG, (int) newB);
                    image.setRGB(col, row, newColor.getRGB());
                }
            }
        }
        try {
            File saveAs = new File(newItemPath);
            ImageIO.write(image, "png", saveAs);
        } catch (IOException ex) {
        }
    }
}
