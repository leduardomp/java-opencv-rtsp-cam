/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package camaras.view;

import camaras.back.Captura;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author luis
 */
public class Camara extends javax.swing.JPanel {

    private String urlVideo;
    private final String nombreCamara;
    private Thread thread;
    private VideoCapture videoCapture;
    private Captura captura;

    /**
     * Creates new form Camara
     *
     * @param nombreCamara
     * @param urlVideo
     */
    public Camara(String nombreCamara, String urlVideo) {
        initComponents();
        this.urlVideo = urlVideo;
        this.nombreCamara = nombreCamara;
        setBorder(javax.swing.BorderFactory.createTitledBorder(nombreCamara));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagenView = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        imagenView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imagenView.setSize(getPreferredSize());
        imagenView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visualizarEnGrande(evt);
            }
        });

        jButton1.setText("Iniciar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 160, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(0, 148, Short.MAX_VALUE))
                    .addComponent(imagenView, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imagenView, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        videoCapture = new VideoCapture(urlVideo);
        // If camera is opened
        if (videoCapture.isOpened()) {

            if (thread != null) {
                captura.runnable = false;
            }

            captura = new Captura(videoCapture, imagenView);
            thread = new Thread(captura);
            thread.start();

        } else
            System.out.println("No es open");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void visualizarEnGrande(MouseEvent evt) {//GEN-FIRST:event_visualizarEnGrande
        if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1) {
            System.out.println("double clicked");
            String urlHD = this.urlVideo.replace("stream=1", "stream=0");

            CamaraHD camaraHD = new CamaraHD(this.nombreCamara);
            camaraHD.setVisible(true);

            VideoCapture videoCaptureHD = new VideoCapture(urlHD);
            // If camera is opened
            if (videoCaptureHD.isOpened()) {

                Captura capturaHD = new Captura(videoCaptureHD, camaraHD.getView());
                Thread threadHD = new Thread(capturaHD);
                threadHD.start();
                
                camaraHD.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    camaraHD.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            capturaHD.runnable = false;
                            e.getWindow().dispose();
                        }
                    });

            } else {
                System.out.println("No es open en HD");
            }
        }
    }//GEN-LAST:event_visualizarEnGrande

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imagenView;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}