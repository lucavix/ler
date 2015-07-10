package com.example.listad;

import android.content.ClipData;
import android.view.View;
import android.widget.AdapterView;

public class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener{
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ImageL selectedItem = (ImageL) (parent.getItemAtPosition(position));

        selectedItem.setPosition(position);
        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder dragShadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, dragShadowBuilder, selectedItem, 0);
        return true;
    }
}
