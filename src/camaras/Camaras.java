/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package camaras;

import camaras.view.VentanaPrincipal;
import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;
import org.opencv.core.Core;

/**
 *
 * @author luis
 */
public class Camaras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                new VentanaPrincipal();
            }
        });
    }
}
