package packTest;

import java.awt.image.BufferedImage;
import java.util.StringJoiner;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.*;
import java.io.*;

import packWork.ConsumerThread;
import packWork.ProducerThread;



public class MyMain {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		String input;
		BufferedImage image = null;

		while (image == null) {
			//se citeste numele fisierului care contine imaginea sursa
			System.out.print("Please insert the name of the image: ");
		      input = keyboard.next();

		      try {
		    	 //se citeste imaginea
		      	image = ImageIO.read(new File(input));
		      } catch (IOException e) {
		      	System.out.println("Name incorrect! Please try again.");
		      }
		}
		
		
		int h = image.getHeight();
		int w = image.getWidth();
		
		int[][] buffer = new int[h][w];
		
		//se initializeaza ProducerThread si ConsumerThread si se porneste executia thread-urilor
		
		ProducerThread producer = new ProducerThread(image, h, w, buffer);
		
		producer.start();
		ConsumerThread consumer = new ConsumerThread(image, h, w, buffer);
		consumer.start();
		
		/*try {
			consumer.join(); 
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}

}
