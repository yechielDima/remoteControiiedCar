import javax.swing.*;
import java.awt.*;

public class Car {
    private int x;
    private int y;
    private ImageIcon carIcon;
    private String directionStatus;
    private boolean changStatus;

    public Car(int x,int y){
        this.x = x;
        this.y = y;
        this.directionStatus = "u";
        this.changStatus = true;
    }
    public void paint(Graphics graphics){
        if (this.changStatus){
            switch (this.directionStatus){
                case "u"->{
                    this.carIcon = new ImageIcon("src/main/resources/carU.png");
                    carIcon.paintIcon(null,graphics,this.x,this.y);
                }
                case "d"->{
                    this.carIcon = new ImageIcon("src/main/resources/carD.png");
                    carIcon.paintIcon(null,graphics,this.x,this.y);
                }
                case "r"->{
                    this.carIcon = new ImageIcon("src/main/resources/carR.png");
                    carIcon.paintIcon(null,graphics,this.x,this.y);
                }
                case "l"->{
                    this.carIcon = new ImageIcon("src/main/resources/carL.png");
                    carIcon.paintIcon(null,graphics,this.x,this.y);
                }
            }
        }else {
            this.carIcon = new ImageIcon("src/main/resources/carU.png");
            carIcon.paintIcon(null,graphics,this.x,this.y);
        }
    }
    public int getX () {
        return this.x;
    }

    public int getY() {
        return y;
    }
    public int getHeight(){
        return this.carIcon.getIconHeight();
    }
    public int getWidth(){
        return this.carIcon.getIconWidth();
    }
    public void move (int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setDirectionStatus(String directionStatus) {
        this.directionStatus = directionStatus;
    }

    public void setChangStatus(boolean changStatus) {
        this.changStatus = changStatus;
    }
}

