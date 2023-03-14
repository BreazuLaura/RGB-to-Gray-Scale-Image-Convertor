package packWork;

import java.awt.image.BufferedImage;
public class ProducerThread extends ProducerConsumer{

	
	public ProducerThread(BufferedImage image, int h, int w, int[][] buffer) {
		super(image, h, w, buffer);
	}

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        int hAux = h/4;
        int k = 1;
        int q = 0;

        //citeste imaginea cate un sfert pe rand si o stocheaza intr-o matrice
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
            	buffer[i][j] = (image.getRGB(j, i));
            }
            if (i == hAux * k && k != 4) {//se termina cate un sfert din imagine
                try {
                    System.out.println("Producer sent " + k + "/4 from image");
                    k++;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            System.out.println("Producer sent 4/4 from image");
            k++;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        try {
            System.out.println("Producer sent the image");
            Thread.sleep(1000);
            k = 1;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        


        System.out.println("\nProducer finished execution!\n");
    }
}