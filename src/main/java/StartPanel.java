import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    public StartPanel(Window window){
        this.setBackground(Color.cyan);
        this.setLayout(null);
        JButton startButton = new JButton("START");
        startButton.setBounds(500,100,500,200);
        startButton.addActionListener((event)-> window.switchPanelsFromStartToMain());
        this.add(startButton);


    }
}
