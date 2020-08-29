package com.bl.webimp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReapplySelectChroma {
    ReadImg readImg = new ReadImg();
    private final String outputPath = "C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgOutput\\img0.png";
    private final BufferedImage imgIn = readImg.read("C:\\Users\\avtea\\Desktop\\repo\\webimp\\src\\main\\resources\\static\\imgInput\\g.png");
    private final BufferedImage imgOut = readImg.read(outputPath);

    public ReapplySelectChroma() throws IOException {
    }

    public void reapply(List<PixelData> pixelData) throws IOException {

        for(int i = 0; i < pixelData.size(); i++){
            int r = pixelData.get(i).getR();
            int g = pixelData.get(i).getG();
            int b = pixelData.get(i).getB();
            int x = pixelData.get(i).getX();
            int y = pixelData.get(i).getY();
            int pixel = (255 << 24)|(r << 16)|(g << 8)|(b);
            imgOut.setRGB(x, y, pixel);
        }

        File file = new File(outputPath);
        ImageIO.write(imgOut, "png", file);
    }

    public static void main(String[] args) throws IOException {
        ReapplySelectChroma rsc = new ReapplySelectChroma();

    }
}
