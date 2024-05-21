import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final Car car;
    private final boolean [] moves;
    public static final int DOWN = 0;
    public static final int UP = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    private int speed;



    public MainPanel(){
        this.setBackground(Color.GREEN);
        this.car = new Car(0,0);
        this.moves = new boolean[4];
        this.speed = 15;

        try {
            TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new RemoteBot(this));
        }catch (TelegramApiException e){
            throw new RuntimeException();
        }

        this.mainLoop();
    }
    public void mainLoop(){
        new Thread(()->{
            while (true){
                int dx = 0;
                int dy = 0;

                if (this.moves[DOWN]) {
                    if (this.car.getY() + this.car.getHeight() < Window.WINDOW_HEIGHT - 50) {
                        dy++;
                    }
                }

                if (this.moves[UP]) {
                    if (car.getY() > 0) {
                        dy--;
                    }
                }

                if (this.moves[RIGHT]) {
                    if (car.getX() + car.getWidth() < Window.WINDOW_WIDTH) {
                        dx++;
                    }
                }

                if (this.moves[LEFT]) {
                    if (car.getX() > 0) {
                        dx--;
                    }
                }

                this.car.move(dx, dy);
                repaint();
                Utils.sleep(speed);
            }
        }).start();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        car.paint(graphics);
    }
    public void moveCar(String direction){
        switch (direction){
            case "d"-> {
                this.moves[0] = true;
                car.setDirectionStatus("d");
            }
            case "u"->{
                this.moves[1]=true;
                car.setDirectionStatus("u");
            }
            case "l"->{
                this.moves[2]=true;
                car.setDirectionStatus("l");
            }
            case "r"->{
                this.moves[3]=true;
                car.setDirectionStatus("r");
            }
            case "s-"->{
                if (this.speed<24){
                    this.speed+=2;
                }
            }
            case "s+"->{
                if (this.speed>1){
                    this.speed-=2;
                }
            }
            case "s"->{
                for (int i = 0; i < 4; i++) {
                    this.moves[i]=false;
                }
            }
        }
    }
    public void park(){
        car.setChangStatus(false);
        while (car.getX()!=0||car.getY()!=0){

            moveCar("l");
            moveCar("u");
        }
        moveCar("s");
        car.setChangStatus(true);
    }
}
