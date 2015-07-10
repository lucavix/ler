package com.example.listad;

import android.os.AsyncTask;

import java.io.IOException;

import lejos.hardware.BrickFinder;
import lejos.hardware.BrickInfo;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;

public class Control extends AsyncTask<String, Integer, Long> {
    private int MOVE_ANGLE = 945;
    public int STEER_ANGLE = 470;
    private  String NAME = "EV3";
    BrickInfo[] devices;
    public static RemoteRequestEV3 ev3;
    public static RegulatedMotor left, right, MC;

    public BrickInfo[] getDevices() {
        if (devices==null) {
            try {
                devices = BrickFinder.discover();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return devices;
    }

    public RemoteRequestEV3 getEV3() {
        if (ev3==null) {
            try {
                for (BrickInfo bi:getDevices()) {
                    if(bi.getName().equals(NAME)) {
                        ev3 = new RemoteRequestEV3(bi.getIPAddress());
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ev3;
    }

    public RegulatedMotor getLeft() {
        if (left==null) {
            left = getEV3().createRegulatedMotor("A", 'L');
            try {
                left.setSpeed((int)left.getMaxSpeed());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return left;
    }

    public RegulatedMotor getRight() {
        if (right==null) {
            right = getEV3().createRegulatedMotor("B", 'L');
            try {
                right.setSpeed((int)right.getMaxSpeed());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return right;
    }

    public RegulatedMotor getMC() {
        if (MC==null) {
            MC = getEV3().createRegulatedMotor("C", 'L');
            try {
                MC.setSpeed((int) MC.getMaxSpeed());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return MC;
    }



    protected Long doInBackground(String... cmd) {
        try {
            left = getEV3().createRegulatedMotor("A", 'L');
            right = getEV3().createRegulatedMotor("B", 'L');
            MC = getEV3().createRegulatedMotor("C", 'L');
            /*
            if (cmd[0].equals("stop")) {
                left.stop(true);
                right.stop(true);
                MC.stop(true);
            }
            */

            if (cmd[0].equals("forward")) {
                left.rotate(MOVE_ANGLE, true);
                right.rotate(MOVE_ANGLE, true);
                left.waitComplete();
                right.waitComplete();
                while (left.isStalled() && right.isStalled()) {

                }
            } else if (cmd[0].equals("backward")) {
                left.rotate(-MOVE_ANGLE, true);
                right.rotate(-MOVE_ANGLE, true);
                left.waitComplete();
                right.waitComplete();
            } else if (cmd[0].equals("rotate left")) {
                left.rotate(-STEER_ANGLE, true);
                right.rotate(+STEER_ANGLE, true);
                left.waitComplete();
                right.waitComplete();
            } else if (cmd[0].equals("rotate right")) {
                left.rotate(+STEER_ANGLE, true);
                right.rotate(-STEER_ANGLE, true);
                left.waitComplete();
                right.waitComplete();
            } else if (cmd[0].equals("fire")) {
                MC.rotate(1000);
                MC.waitComplete();
            }
            return 5l;
        } catch ( Exception e) {
            return 2l;
        }
    }
    protected void onPostExecute(Long result) {
        // if (result == 1l) Toast.makeText(MainActivity.context, "Non riesco a connettermi all'EV3", Toast.LENGTH_LONG).show();
        // if (result == 2l) Toast.makeText(MainActivity.context, "Errore durante la connessione", Toast.LENGTH_LONG).show();
    }
}