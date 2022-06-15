/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camaras.back;

import camaras.view.CamaraHD;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author luis
 */
public class CapturaHD implements Runnable{
    
    public boolean runnable = true;
    private final String nombreCamara;
    private final String urlVideo;
    private VideoCapture videoCapture;

    public CapturaHD(String nombreCamara, String urlHD) {
        this.nombreCamara = nombreCamara;
        this.urlVideo = urlHD;
    }
    
    @Override
    public void run() {
        System.out.println("Inicia Hilo");

        CamaraHD camaraHD = new CamaraHD(this.nombreCamara);
        camaraHD.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        camaraHD.setVisible(true);
        camaraHD.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                runnable = false;
                e.getWindow().dispose();
            }
        });

        videoCapture = new VideoCapture(urlVideo);

        if (videoCapture.isOpened()) {
            while (runnable) {
                try {
                    Mat frame = new Mat();
                    if (videoCapture.read(frame)) {

                        camaraHD.getView().setIcon(new ImageIcon(Mat2bufferedImage(frame, camaraHD.getView().getSize())));
                        camaraHD.getView().repaint();

                    } else {
                        System.out.println("no red frame");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Video capture no is open");
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
