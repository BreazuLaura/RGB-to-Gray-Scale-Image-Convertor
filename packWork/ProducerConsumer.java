package packWork;

import java.awt.image.BufferedImage;
//clasa abstracta implementata de interfata ProducerConsumerInterface si Runnable si contine metoda start().

public abstract class ProducerConsumer implements ProducerConsumerInterface, Runnable{
    
	protected Thread thread;
	protected BufferedImage image;
	protected int h;
	protected int w;
	public int [][] buffer;
	
    public ProducerConsumer(BufferedImage image, int h, int w, int [][] buffer) {
		this.thread = new Thread(this);
	    this.image = image;
	    this.h = h;
	    this.w = w;
	    this.buffer = buffer;
    }
    
    
    public abstract void start() throws InterruptedException;
}
