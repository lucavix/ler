package com.example.listad;

public class Start {

    private static Start instance;

    private Start () {}

    public static synchronized Start getInstance() {
        if (instance==null) {
            instance = new Start();
        }
        return instance;
    }

    public boolean running = false;
    public boolean stopRunning = false;

    public synchronized void  setStopRunning(boolean stopRunning) {
        this.stopRunning = stopRunning;
    }

    public synchronized void setRunning(boolean running) {
        this.running = running;
    }

    public synchronized boolean getRunning() {
        return running;
    }

    public void Avvia() {
        //Toast.makeText(MainActivity.this, listImage2.size(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < MainActivity.listImage2.size() && !stopRunning; i++) {
            switch (MainActivity.listImage2.get(i).getImage()) {
                case "forward":
                    //MainActivity.listImage2.set(i, new ImageL(MainActivity.listImage2.get(i).getName(), MainActivity.listImage2.get(i).getImage(), (byte) 2, true));
                    //ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list2);
                    //lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage2));
                    new Control().execute("forward");
                    //MainActivity.listImage2.set(i, new ImageL(MainActivity.listImage2.get(i).getName(), MainActivity.listImage2.get(i).getImage(), (byte) 2, false));
                    //lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage2));
                    break;
                case "backward":
                    new Control().execute("backward");
                    break;
                case "left":
                    new Control().execute("rotate left");
                    break;
                case "right":
                    new Control().execute("rotate right");
                    break;
                case "fire":
                    new Control().execute("fire");
                    break;
                case "function":
                    AvviaFunzione();
                    break;
            }
        }
        setRunning(false);
    }

    public void AvviaFunzione() {
        for (int i = 0; i < MainActivity.listImage3.size() && !stopRunning; i++) {
            switch (MainActivity.listImage3.get(i).getImage()) {
                case "forward":
                    new Control().execute("forward");
                    break;
                case "backward":
                    new Control().execute("backward");
                    break;
                case "left":
                    new Control().execute("rotate left");
                    break;
                case "right":
                    new Control().execute("rotate right");
                    break;
                case "fire":
                    new Control().execute("fire");
                    break;
            }
        }
    }
}
