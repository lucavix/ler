package com.example.listad;

import java.util.List;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ImageAdapter extends ArrayAdapter{

    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public ImageAdapter(Context context, int resource, List objects) {
        super ( context, resource, objects );
        this.resource = resource;
        // Ottine il LayoutInflater dal contesto dato.
        this.inflater = LayoutInflater.from( context );
        this.context = context;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {
        convertView = (LinearLayout) inflater.inflate(resource, null);
        ImageL imagel = (ImageL) getItem(position);
        ImageView imageButton = (ImageView) convertView.findViewById(R.id.Image);
        String uri = "drawable/" + imagel.getImage();
        Drawable im = context.getResources().getDrawable(context.getResources().getIdentifier(uri, null, context.getPackageName()));
        imageButton.setImageDrawable(im);
        if(imagel.getBorder() == true) {
            imageButton.setBackgroundResource(R.drawable.border);
        }
        imageButton.setTag("Logo android");
        return convertView;

    }

}