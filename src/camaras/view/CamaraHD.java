/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package camaras.view;

import java.awt.Dimension;
import javax.swing.JLabel;

/**
 *
 * @author luis
 */
public class CamaraHD extends javax.swing.JFrame {

    /**
     * Creates new form CamaraHD
     * @param nombreCamara
     */
    public CamaraHD(String nombreCamara) {
        initComponents(nombreCamara);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void initComponents(String nombreCamara) {
        viewSize = new Dimension(800, 600);
                
        setTitle(nombreCamara);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(viewSize);
        setPreferredSize(viewSize);
        setSize(viewSize);
        
        imagenViewHD = new javax.swing.JLabel();
        imagenViewHD.setSize(viewSize);
        imagenViewHD.setBounds(0, 0, (int)viewSize.getWidth(), (int)viewSize.getHeight());
        add(imagenViewHD);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        

    public JLabel getView(){
        return imagenViewHD;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel imagenViewHD;
    private Dimension viewSize;              
}