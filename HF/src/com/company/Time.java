package com.company;

import java.util.Timer;
import java.util.TimerTask;

public class Time
{
    int time;
    Time()
    {
        time=20;
    }
    public int getTime()
    {
        return time;
    }
    public void incrementTime()
    {
        time=time+10;
    }
    public void start()
    {
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if(time>0)
                {
                    time--;
                    System.out.println(time);
                }
                else
                {
                    System.out.println("Game Over");
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1000);
    }

}
