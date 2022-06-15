/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camaras.back;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author luis
 */
public class Captura implements Runnable{
    
    public volatile boolean runnable = true;
    private final VideoCapture capture;
    private final JLabel imagenView;

    public Captura(VideoCapture capture, JLabel imagenView) {
        this.capture = capture;
        this.imagenView = imagenView;
    }
    
    @Override
    public void run() {
        System.out.println("Inicia Hilo");
        
        while (runnable) {
            try {
                Mat frame = new Mat();
                if(capture.read(frame)) {
                    
                    imagenView.setIcon(new ImageIcon(Mat2bufferedImage(frame, this.imagenView.getSize())));
                    imagenView.repaint();
                }else{
                    System.out.println("no red capture");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Fin del hilo");
    }
    
    
    private BufferedImage Mat2bufferedImage(Mat frame, Dimension sizeView) {
        
        int type = frame.channels() == 1 ? BufferedImage.TYPE_BYTE_GRAY : BufferedImage.TYPE_3BYTE_BGR;
        int bufferSize = frame.channels() * frame.cols() * frame.rows();
        byte[] buffer = new byte[bufferSize];
        frame.get(0, 0, buffer);
        
        BufferedImage image = new BufferedImage(frame.cols(), frame.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer, 0, targetPixels, 0, buffer.length);
        
        BufferedImage resizedImg = Scalr.resize(image, Method.QUALITY, Scalr.Mode.FIT_EXACT, sizeView.width, sizeView.height);
        
        return resizedImg;
    }
    
}
