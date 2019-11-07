/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficadora;

import ulima.formularios.JDGrafica;
import ulima.formularios.JFrameCalculadora;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class Ventana extends JFrame implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JDGrafica.plano.paintSQRFunc(JDGrafica.plano.getGraphics(), Integer.parseInt(JDGrafica.jTxtCuad.getText().trim()), Integer.parseInt(JDGrafica.jTxtLin.getText().trim()), Integer.parseInt(JDGrafica.jTxtCont.getText().trim()), -200, 200);
    }

    
}
