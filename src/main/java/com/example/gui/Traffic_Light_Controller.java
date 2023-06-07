package com.example.gui;

import java.awt.*;
import java.util.Scanner;

public class Traffic_Light_Controller {
    AI ai = new AI();
    Traffic_Light traffic_light = new Traffic_Light(new Traffic_Light_time());
    Traffic_Light_time traffic_light_time = new Traffic_Light_time();
    private int light_time;
    private String lane;
    private boolean emergency;
    public int getLight_time() {
        return light_time;
    }



    public Traffic_Light_Controller() {
//        boolean emergency;
        traffic_light_time.restTime();
        emergency = ai.getEmergencyVehicle(); //模擬緊急車輛通過
        lane = ai.compareVerticalTrafficFlow();

        if(emergency == true){
            //無緊急車輛通過就正常
            demermineState("Vertical", emergency, "Red");
        }else{
            determineTime(Traffic_Light_time.LaneType.valueOf(lane));
        }
        if(emergency == false){
            determineTime(Traffic_Light_time.LaneType.valueOf(lane));
        }
    }
    public void demermineState(String lane,boolean bool,String color){
        traffic_light.changeLight(lane,bool,color);
    }
    //第二段貼這邊
    public void determineTime(Traffic_Light_time.LaneType lane){
        traffic_light_time.changeTime(lane);
    }

    public void saveData(){
        System.out.println("SaveData is worked.");
    }
}
