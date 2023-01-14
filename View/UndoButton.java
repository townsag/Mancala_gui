package View;

import Model.DataModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UndoButton extends JButton {
    private DataModel myModel;

    public UndoButton(DataModel myDM){
        myModel = myDM;
        this.setBackground(Color.LIGHT_GRAY);
        this.setText("Undo");
    }

    public void setIsActive(boolean flag){
        removeAllActionListeners();
        if(flag){
            this.setBackground(Color.RED);
            this.addActionListener(new UndoButtonListener());
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

    private class UndoButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            myModel.undoTurn();
        }
    }
}
