/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phystest;



/**
 * 
 */

public class Tick_Helper {


    private Tick_Helper(Builder b){

        delta = b.delta;
        targetFPS = b.targetFPS;
        fpsUpdateDelay = b.fpsUpdateDelay;

    }

    /**
     * The hidden_fps that is desired
     */
    private long targetFPS=0;

    /**
     * The system time before work in milliseconds
     *
     * used to calculate the delta
     */
    private long oldTime=0;

    /**
     * The amount of time to put the thread to sleep for
     */
    private long sleep=0;

    /**
     * The systems current time in milliseconds
     *
     * used to calculate the delta
     */
    private long currentTime=0;

    /**
     * the amount of time that the work took
     * in other words
     *currentTime-oldTime
     */
    private long delta=0;

    /**
     * The current fps... updated each tick
     */
    private long hidden_fps=0;


    /**
     * The amount of time before the fps is updated
     */
    private long fpsUpdateDelay=0;


    /**
     * the system time when the fps was last updated
     */
    private long lastFpsUpdate=0;

    /**
     * the fps with fpsUpdateDelay milliseconds of lag
     */
    private long fps;



    public static class Builder{

        public Builder(){
            // don't need nothing
        }

        private long targetFPS=24; //it's cinematic
        private long delta=33;
        private long fpsUpdateDelay=0;


        public Builder setTargetFPS(long targetFPS) {
            this.targetFPS = targetFPS;
            return this;
        }

        public Builder setDelta(long delta) {
            this.delta = delta;
            return this;
        }

        public Builder setFpsUpdateDelay(long fpsUpdateDelay) {
            this.fpsUpdateDelay = fpsUpdateDelay;
            return this;
        }

        public Tick_Helper build(){
            return new Tick_Helper(this);
        }


    }


    public void TickTock(){

        if(System.currentTimeMillis()-lastFpsUpdate>fpsUpdateDelay){
            fps = hidden_fps;
            lastFpsUpdate = System.currentTimeMillis();
        }

        try {
            currentTime = System.currentTimeMillis();
            delta = currentTime - oldTime;
            hidden_fps = 1000 / delta;
            sleep = (1000 / targetFPS) - delta;
            Thread.sleep(sleep);
            currentTime = System.currentTimeMillis();
            delta = currentTime - oldTime;
            hidden_fps = 1000 / delta;

        }catch (Exception e){

        }

        oldTime = System.currentTimeMillis();

        if(delta>1000) {
            delta = 33;
        }

    }


    public long getTargetFPS() {
        return targetFPS;
    }

    public long getOldTime() {
        return oldTime;
    }

    public long getSleep() {
        return sleep;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public long getDelta() {
        return delta;
    }

    public long getFps() {
        return fps;
    }

    public long getFpsUpdateDelay() {
        return fpsUpdateDelay;
    }



}
