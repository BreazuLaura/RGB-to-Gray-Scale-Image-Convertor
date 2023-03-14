package packWork;

import java.awt.image.BufferedImage;

//clasa care mosteneste ImageProcessor
class GrayscaleConverter extends ImageProcessor {
    public void processImage() {
    }
    protected BufferedImage image;
    
    public GrayscaleConverter(BufferedImage image) {
        this.image = image;
    }
}