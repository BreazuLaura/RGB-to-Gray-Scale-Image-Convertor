package packWork;

import java.awt.image.BufferedImage;

class BufferedImageGrayscaleConverter extends GrayscaleConverter {
    public BufferedImageGrayscaleConverter(BufferedImage image) {
        super(image);
    }
    //aplicarea algoritmului folosit de metoda Weighted
    @Override
    public void processImage() {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = image.getRGB(i, j);
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = color & 0xff;
                int gray = (int)(0.3 * red + 0.59 * green + 0.11 * blue);
                int newPixel = (gray << 16) | (gray << 8) | gray;
                image.setRGB(i, j, newPixel);
            }
        }
    }
}