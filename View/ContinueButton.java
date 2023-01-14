package View;

import Model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContinueButton extends JButton {
    private DataModel myModel;

    public ContinueButton(DataModel myDM){
        myModel = myDM;
        this.setBackground(Color.LIGHT_GRAY);
        this.setText("Continue");
    }

    public void setIsActive(boolean flag){
        removeAllActionListeners();
        if(flag){
            this.setBackground(Color.GREEN);
            this.addActionListener(new ContinueButtonListener());
        } else {
            this.setBackground(Color.LIGHT_GRAY);
        }
    }

    private void removeAllActionListeners(){
        ActionListener[] tempArray = this.getActionListeners();
        if(tempArray != null) {
            for (int i = 0; i < tempArray.length; i++) {
                this.removeActionListener(tempArray[i]);
            }
        }
    }

    private class ContinueButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            myModel.continueTurn();
        }
    }
}
