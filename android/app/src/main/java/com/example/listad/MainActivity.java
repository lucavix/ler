package com.example.listad;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView listView1;
    private ListView listView2;
    private ListView listView3;
    private ListView listView4;
    private List<ImageL> listImage1;
    public static List<ImageL> listImage2 = new ArrayList<>();
    public static List<ImageL> listImage3 = new ArrayList<>();
    private List<ImageL> listImage4;

    public static Context context;

    public static Activity activity;

    public static MyDragListener myDragListener = new MyDragListener();
    public static MyOnItemLongClickListener myOnItemLongClickListener = new MyOnItemLongClickListener();
    public static MyOnItemClickListener myOnItemClickListener = new MyOnItemClickListener();

    ImageL ImageForward = new ImageL("forward", (byte)1, false);
    ImageL ImageBackward = new ImageL("backward", (byte)1, false);
    ImageL ImageLeft = new ImageL("left", (byte)1, false);
    ImageL ImageRight = new ImageL("right", (byte)1, false);
    ImageL ImageFire = new ImageL("fire", (byte)1, false);
    ImageL ImageFunction = new ImageL("function", (byte)1, false);
    ImageL ImageStart = new ImageL("start", (byte)4, false);
    ImageL ImageTrash = new ImageL("trash", (byte)4, false);
    //ImageL ImageStop = new ImageL("stop", (byte)4, false);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
        context = this;
        listImage1 = new ArrayList<>();   // Lista della classe ImageL
        // Aggiungo alla lista ImageL con i relativi parametri (nome identificativo, nome immagine presente in drawable)
        listImage1.add(ImageForward);
        listImage1.add(ImageBackward);
        listImage1.add(ImageLeft);
        listImage1.add(ImageRight);
        listImage1.add(ImageFire);
        listImage1.add(ImageFunction);

        listImage4 = new ArrayList<>();
        listImage4.add(ImageStart);
        //listImage4.add(ImageStop);
        listImage4.add(ImageTrash);

        // Creo la ListView utilizzando quellla presente nel file xml (image_row_item)
        listView1 = (ListView) findViewById(R.id.image_list1);
        listView1.setAdapter(new ImageAdapter(context, R.layout.image_row_item, listImage1));
        listView2 = (ListView) findViewById(R.id.image_list2);
        listView2.setAdapter(new ImageAdapter(context, R.layout.image_row_item, listImage2));
        listView3 = (ListView) findViewById(R.id.image_list3);
        listView3.setAdapter(new ImageAdapter(context, R.layout.image_row_item, listImage3));
        listView4 = (ListView) findViewById(R.id.image_list4);
        listView4.setAdapter(new ImageAdapter(context, R.layout.image_row_item, listImage4));

        listView1.setOnItemLongClickListener(myOnItemLongClickListener);
        listView2.setOnItemLongClickListener(myOnItemLongClickListener);
        listView3.setOnItemLongClickListener(myOnItemLongClickListener);

        listView1.setOnItemClickListener(myOnItemClickListener);
        listView2.setOnItemClickListener(myOnItemClickListener);
        listView3.setOnItemClickListener(myOnItemClickListener);
        listView4.setOnItemClickListener(myOnItemClickListener);

        findViewById(R.id.image_list1).setOnDragListener(myDragListener);
        findViewById(R.id.image_list2).setOnDragListener(myDragListener);
        findViewById(R.id.image_list3).setOnDragListener(myDragListener);

/*
        findViewById(R.id.menu).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "Menu / Menu", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        findViewById(R.id.toolkit).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "Toolkit / Strumenti", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        findViewById(R.id.program).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "Program / Programma", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        findViewById(R.id.function).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this, "Function / Funzione", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
*/
    }

}
