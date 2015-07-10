package com.example.listad;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOnItemClickListener implements AdapterView.OnItemClickListener{
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageL selectedItem = (ImageL) (parent.getItemAtPosition(position));
        if (selectedItem.getImage().equals("start")) {
            // Codice da eseguire quando viene schiacciato il pulsante start;
            Start s = Start.getInstance();
            if(s.getRunning() == false) {
                s.setRunning(true);
                s.setStopRunning(false);
                s.Avvia();
            }

        } else if (selectedItem.getImage().equals("trash")) {
            // Codice da eseguire quando viene schiacciato il pulsante elimina;
            Start s = Start.getInstance();
            if (s.getRunning() == false) {
                MainActivity.listImage2 = new ArrayList<>();
                ListView lv = (ListView) MainActivity.activity.findViewById(R.id.image_list2);
                lv.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage2));
                MainActivity.listImage3 = new ArrayList<>();
                ListView lv2 = (ListView) MainActivity.activity.findViewById(R.id.image_list3);
                lv2.setAdapter(new ImageAdapter(MainActivity.context, R.layout.image_row_item, MainActivity.listImage3));
            } else {
                Toast.makeText(MainActivity.context, "Non puoi eliminare elementi mentre l'applicazione sta eseguendo i comandi", Toast.LENGTH_SHORT).show();
            }
        } else if (selectedItem.getImage().equals("stop")) {
            Start s = Start.getInstance();
            if (s.getRunning() == false) {
                s.setStopRunning(true);
            }
        }
        Toast.makeText(MainActivity.context, selectedItem.getImage(), Toast.LENGTH_SHORT).show();
    }
}
