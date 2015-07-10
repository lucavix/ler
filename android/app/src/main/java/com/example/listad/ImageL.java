package com.example.listad;

public class ImageL {

    private String image;
    private int position;   // Posizione nell'array di cui appartiene. Viene settata solo quando si attiva il metodo OnLongClickListener
    /* l'immagine non viene 'bordata' in questo programma */
    private boolean border; // true --> crea un bordo all'immagine
    private byte view;  // Numero della vista

    public ImageL(String image, byte view, boolean border){
        setImage(image);
        setView(view);
        setBorder(border);
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }


    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setView(byte view) {
        this.view = view;
    }

    public byte getView(){
        return view;
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public boolean getBorder() {
        return border;
    }
}
