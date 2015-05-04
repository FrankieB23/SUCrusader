package edu.susqu.math.kubota.xmltester;

import android.os.Bundle;

/**
 * Created by Benjamin on 4/21/2015.
 */
public class GetIntent extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Bundle extras = getIntent().getExtras();
    }
}

                 //.get("Content")