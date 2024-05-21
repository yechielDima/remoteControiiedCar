import javax.swing.*;

public class Window extends JFrame {
    public static final int WINDOW_WIDTH = 1500;
    public static final int WINDOW_HEIGHT = 1000;
    private final StartPanel startPanel;
    private final MainPanel mainPanel;
    public Window(){
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.mainPanel = new MainPanel();

        this.startPanel = new StartPanel(this);
        this.add(this.startPanel);


    }
    public void showWindow(){
        this.setVisible(true);
    }
    public void switchPanelsFromStartToMain(){
        this.startPanel.setVisible(false);
        this.add(this.mainPanel);
    }

}
