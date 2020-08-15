package com.project.editor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChromaKey {

    public void process(String inputPath, String outputPath, int selectedX, int selectedY) throws IOException{

        ReadImg readImg = new ReadImg();
        BufferedImage img = readImg.read(inputPath);
        File file;

        int width = img.getWidth();
        int height = img.getHeight();

        int selectedPixel = img.getRGB(selectedX, selectedY);
        int sR = (selectedPixel >> 16)&0xff;
        int sG = (selectedPixel >> 8)&0xff;
        int sB = selectedPixel&0xff;

        BufferedImage outputImg = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){

                int pixel = img.getRGB(x, y);
                int a = (pixel >> 24)&0xff;
                int r = (pixel >> 16)&0xff;
                int g = (pixel >> 8)&0xff;
                int b = pixel&0xff;

                if (a != 0 && (sR - 15) <= r && r <= (sR + 15) && (sG - 15) <= g && g <= (sG + 15) && (sB - 15) <= b && b <= (sB + 15)){
                    pixel = 0;
                }

                outputImg.setRGB(x, y, pixel);
            }
        }

        file = new File(outputPath);
        ImageIO.write(outputImg, "png", file);

    }

    public void multiProcess(String inputDirectory, String outputDirectory, int selectedX, int selectedY) throws Exception{
        File folder = new File(inputDirectory);
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++){
            process(files[i].getPath(), outputDirectory + i + ".png", selectedX, selectedY);
        }
    }

}
