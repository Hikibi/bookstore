package com.lanou.bookstore.admin.service;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 * 功能 :调整图片大小
 */
public class ChoosePicture {

    public void resizeImage(String srcImgPath, String distImgPath,
                                   int width, int height) throws IOException {

        File srcFile = new File(srcImgPath);
        Image srcImg = ImageIO.read(srcFile);
        BufferedImage buffImg = null;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        buffImg.getGraphics().drawImage(
                srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0,
                0, null);

        ImageIO.write(buffImg, "JPEG", new File(distImgPath));

    }

}

