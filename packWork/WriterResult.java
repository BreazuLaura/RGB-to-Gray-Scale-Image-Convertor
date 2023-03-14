package packWork;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
//import javax.imageio.ImageIO;

import javax.imageio.ImageIO;


public class WriterResult extends Thread {
    private PipedInputStream inPipe;

    public WriterResult(PipedOutputStream in) throws IOException {
        inPipe = new PipedInputStream(in);
    }
    

    public void run() {
    	BufferedImage image = null;
    	//preia imaginea prelucrata de la ConsumerThread prin intermediul unui Pipe
    	System.out.println("\nWriterResult received the image!\n");
        try {
        	image = ImageIO.read(inPipe);
            File outputFile = new File("result.bmp");
            ImageIO.write(image, "bmp", outputFile);
            inPipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
	
