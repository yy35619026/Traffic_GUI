package com.example.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulation {
    private int STATE = 0;
    private int EastAndWestState = 1;
    private int CURRENT_STATE = 5;  //四種狀態, 0:由南向北 1:由北向南 2:由西向東 3:由東向西
    private int LINE_1 = 110;
    private int LINE_2 = 460;


    private Frame frame = new Frame("Traffic Light");
    private final int TABALE_WIDTH = 1000;   //畫面寬度
    private final int TABALE_HEIGHT = 600;  //畫面高度
    private final int REC_WIDTH = 50;      //燈寬度
    private final int REC_HEIGHT = 50;      //燈高度
    private final int C_SIZE = 30;          //燈的直徑

    Button start = new Button("Start");
    Button simulate = new Button("Simulate");
    Button stop = new Button("Stop");
    Button exit = new Button("Exit");

    private int Greentime = 30;
    private int Yellowtime = 5;
    private int Redtime = Greentime + Yellowtime;
    // Text Timer
    int timeNorthAndSouth = Greentime;
    int timeEastAndWest = Redtime;
    //Rectangle Position

    // Light Position
    private final int lightNorthYPosition = 360;

    private final int lightNorthXPosition = 110;

    private Timer timer;    //燈變化的時間監聽器


    private Label labelEast,labelWest,labelNorth,labelSouth;
    private int CountdownVal = 30;
    int delay = 1000;

    private class MyCanvas extends Canvas {
        @Override
        public void paint(Graphics g){
            //線條
            g.drawLine(150, 0, 150, 600);
            g.drawLine(300, 50, 300, 550);
            g.drawLine(450, 0,450, 600 );

            g.drawLine(0, 150, 600, 150);
            g.drawLine(50, 300, 550, 300);
            g.drawLine(0, 450, 600, 450);
            //繪制黑色矩形框
            g.fillRect(150, 100, REC_WIDTH, REC_HEIGHT);
            g.fillRect(300, 450, REC_WIDTH, REC_HEIGHT);
            g.fillRect(100, 300, REC_HEIGHT, REC_WIDTH);
            g.fillRect(450, 150, REC_HEIGHT, REC_WIDTH);

            //燈的狀態,0:由南向北行駛 1:由北向南行駛 2:由西向東行駛 3:由東向西行駛
            if (CURRENT_STATE == 0){//南北綠燈 東西紅燈
                g.setColor(Color.GREEN);//綠燈
                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1-->燈號(北)
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9-->燈號(南)
                g.setColor(Color.RED);
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4-->燈號(東)
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12--燈號(西)
            }
            if (CURRENT_STATE == 1){//南北黃燈 東西紅燈
                g.setColor(Color.YELLOW);//綠燈
                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1-->燈號(北)
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9-->燈號(南)
                g.setColor(Color.RED);
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4-->燈號(東)
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12--燈號(西)
            }
            if (CURRENT_STATE == 2){//南北紅燈 東西綠燈
                g.setColor(Color.RED);//紅燈
                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1-->燈號(北)
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9-->燈號(南)
                g.setColor(Color.GREEN);
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4-->燈號(東)
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12--燈號(西)
            }
            if (CURRENT_STATE == 3){//南北綠燈 東西黃燈
                g.setColor(Color.RED);//綠燈
                g.fillOval(160, LINE_1, C_SIZE, C_SIZE); // light 1-->燈號(北)
                g.fillOval(310, LINE_2, C_SIZE, C_SIZE); // light 9-->燈號(南)
                g.setColor(Color.YELLOW);
                g.fillOval(LINE_2, 160, C_SIZE, C_SIZE); // light 4-->燈號(東)
                g.fillOval(LINE_1, 310, C_SIZE, C_SIZE); // light 12--燈號(西)
            }
        }
    }
    MyCanvas drawArea = new MyCanvas();
    public void init() {
        final ActionListener LIGHT_task = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //North and South
                timeNorthAndSouth--;
                timeEastAndWest--;
                // East and West
                if (timeEastAndWest > 0) {
                    labelEast.setText(Integer.toString(timeEastAndWest));
                    labelWest.setText(Integer.toString(timeEastAndWest));
                }
                if (timeNorthAndSouth > 0) {
                    labelNorth.setText(Integer.toString(timeNorthAndSouth));
                    labelSouth.setText(Integer.toString(timeNorthAndSouth));
                } if (timeEastAndWest == 0 || timeNorthAndSouth == 0) {
                    STATE += 1;
                    if(STATE > 3){
                        STATE-=3;
                    }
                    CURRENT_STATE=STATE;
                    if (CURRENT_STATE == 0){//南北綠燈 東西紅燈
                        timeNorthAndSouth = Greentime;
                        timeEastAndWest = Redtime;
                    }else if(CURRENT_STATE == 1){//南北黃燈 東西紅燈
                        timeNorthAndSouth = Yellowtime;
//                        timeEastAndWest = 3;
                    }else if (CURRENT_STATE == 2){//南北紅燈 東西綠燈
                        timeNorthAndSouth = Redtime;
                        timeEastAndWest = Greentime;
                    }else if(CURRENT_STATE == 3){//南北綠燈 東西黃燈
//                        timeNorthAndSouth =0;
                        timeEastAndWest = Yellowtime;
                    }

                }
                labelEast.setText(Integer.toString(timeEastAndWest));
                labelWest.setText(Integer.toString(timeEastAndWest));
                labelNorth.setText(Integer.toString(timeNorthAndSouth));
                labelSouth.setText(Integer.toString(timeNorthAndSouth));
                drawArea.repaint();

            }
        };
        //Start按键
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CURRENT_STATE = 0;
                drawArea.repaint();
            }
        });
        //Simulate按键
        simulate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer = new Timer(1000, LIGHT_task);//1000ms執行一次LIGHT_task任務
                timer.start();

                Traffic_Light_Controller tlc = new Traffic_Light_Controller();

                drawArea.repaint();
            }
        });
        //Stop按键
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();       //紅綠燈停止閃爍
                drawArea.repaint();
            }
        });


        //Exit按键響應事件
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });


        Panel panel = new Panel();
        panel.add(start);
        panel.add(simulate);
        panel.add(stop);
        panel.add(exit);


        labelEast = new Label("TEST");
        labelWest = new Label("TEST");
        labelNorth = new Label("TEST");
        labelSouth = new Label("TEST");

        labelEast.setBackground(Color.WHITE);
        labelWest.setBackground(Color.WHITE);
        labelNorth.setBackground(Color.WHITE);
        labelSouth.setBackground(Color.WHITE);
        labelEast.setForeground(Color.BLACK);
        labelWest.setForeground(Color.BLACK);
        labelNorth.setForeground(Color.BLACK);
        labelSouth.setForeground(Color.BLACK);

        // 设置标签的位置和大小
        labelEast.setBounds(LINE_2, 160, 40, 20);
        labelWest.setBounds(LINE_1, 310, 40, 20);
        labelNorth.setBounds(160, LINE_1, 40, 20);
        labelSouth.setBounds(310, LINE_2, 40, 20);


        frame.add(labelEast);
        frame.add(labelWest);
        frame.add(labelNorth);
        frame.add(labelSouth);



        frame.add(panel, BorderLayout.SOUTH);
        drawArea.setPreferredSize(new Dimension(TABALE_WIDTH, TABALE_HEIGHT));
        frame.add(drawArea);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
    public static void main(String[] args) {
        new Simulation().init();
    }
}
