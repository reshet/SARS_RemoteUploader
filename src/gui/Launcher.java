package gui;

import java.awt.EventQueue;
import java.nio.charset.Charset;

import javax.swing.JFrame;


public class Launcher extends JFrame{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {		
			@Override
			public void run() {
				System.out.println(System.getProperty("file.encoding"));
				System.setProperty("file.encoding","UTF-8");
				System.out.println(System.getProperty("file.encoding"));
				UploadFrame frame = new UploadFrame();
				frame.setVisible(true);
				//RootBackgroundFrame RBFrame = new RootBackgroundFrame();
				//RBFrame.setUndecorated(true);
				//RBFrame.setPreferredSize(new Dimension(1024,896));
				/*
				String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
				for (String font:fonts)
				{
					System.out.println(font);			
				}
				*/
				//this.setVisible(true);
				
				//RBFrame.setVisible(true);
			}
		});
	}

}
