package packWork;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import javax.imageio.ImageIO;

public class ConsumerThread extends ProducerConsumer{

	
	public ConsumerThread(BufferedImage image, int h, int w, int[][] buffer) {
		super(image, h, w, buffer);
	}
	
	public void start() {
        thread.start();
    }

	@Override
	public void run() {
		int hAux = h/4;
        int k = 1;
        int[][] matrix = new int [h][w];
        
        //preia imaginea de la Producer cate un sfert pe rand
		for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	matrix[i][j] = buffer[i][j];
            }
            if (i == hAux * k && k != 4) {
                try {
                    System.out.println("Consumer received " + k + "/4 from image");
                    k++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
		
		try {
            System.out.println("Consumer received 4/4 from image");
            k++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        try {
            System.out.println("Consumer received the image");
            Thread.sleep(1000);
            k = 1;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        
        //se aplica metoda Weighted
        BufferedImageGrayscaleConverter newImage = new BufferedImageGrayscaleConverter(image);
        newImage.processImage();
        System.out.println("\nThe image finished converting.\n");
       

        
        //se trasmite imaginea prelucrata catre WriterResult prin intermediul unui Pipe
        try {
            PipedOutputStream outPipe = new PipedOutputStream();
            WriterResult writer = new WriterResult(outPipe);
            writer.start();

            ImageIO.write(image, "bmp", outPipe);
            System.out.println("\nConsumer sent the image to WriterResult!\n");
            outPipe.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

        System.out.println("\nConsumer finished execution!\n");
		
	}

}


	
	