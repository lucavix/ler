package com.example.listad;

import android.view.DragEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class MyDragListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {

        switch (event.getAction()) {

            case DragEvent.ACTION_DRAG_STARTED:
                break;

            case DragEvent.ACTION_DRAG_ENTERED:
                break;

            case DragEvent.ACTION_DRAG_EXITED:
                break;

            case DragEvent.ACTION_DROP:
                // L'immagine viene trascinata sul layout 2
                if (v == MainActivity.activity.findViewById(R.id.image_list2)) {
                    DropOnLayout2(v, event);
                }
                // immagine viene trascinata sul layout 3
                else if (v == MainActivity.activity.findViewById(R.id.image_list3)) {
                    DropOnLayout3(v, event);
                }
                // immagine viene trascinata sul layout 1
                else if (v == MainActivity.activity.findViewById(R.id.image_list1)) {
                    DropOnLayout1(v, event);
                }
                // In tutti gli altri casi
                else {
                    Toast.makeText(MainActivity.context, "Non puoi spostare l'immagine qui!", Toast.LENGTH_LONG).show();
                }
                break;

            case DragEvent.ACTION_DRAG_ENDED:
                break;

            default:
                break;
        }
        return true;
    }

    public void DropOnLayout2 (View v, DragEvent event) {
        ImageL view = (ImageL) event.getLocalState();
        // Controlla se l'immagine non si trovava inizialmente sul layout 2 o 3
        if (view.getView() != (byte)2 && view.getView() != (byte)3) {
            // aggiunge un nuovo oggeto alla lista
            MainActivity.listImage2.add(new ImageL(view.getImage(), (byte) 2, false));
            ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list2);
            lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage2));
        }
    }

    public void DropOnLayout3 (View v, DragEvent event) {
        ImageL view = (ImageL) event.getLocalState();
        // Controlla se l'immagine non si trovava inizialmente sul layout 2 o 3
        if (view.getView() != (byte)2 && view.getView() != (byte)3) {
            // Per non mandare in loop il programma controllo che l'immagine non sia il comando "funzione"
            if (view.getImage().equals("function")) {
                Toast.makeText(MainActivity.context, "Non puoi spostare questa immagine nella colonna funzioni!", Toast.LENGTH_LONG).show();
            } else {
                MainActivity.listImage3.add(new ImageL(view.getImage(), (byte) 3, false));
                ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list3);
                lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage3));
            }
        }
    }

    public void DropOnLayout1 (View v, DragEvent event) {
        ImageL view = (ImageL) event.getLocalState();
        // Controlla se l'immagine si trovava inizialmente sul layout 2
        if(view.getView() == (byte)2) {
            MainActivity.listImage2.remove(view.getPosition());
            ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list2);
            lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage2));
        }
        // Controlla se l'immagine si trovava inizialmente sul layout 2
        else if (view.getView() == (byte)3){
            MainActivity.listImage3.remove(view.getPosition());
            ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list3);
            lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage3));
        } else {
            Toast.makeText(MainActivity.context, "Non puoi spostare l'immagine qui!", Toast.LENGTH_LONG).show();
        }
    }
}
