package edu.susqu.math.kubota.xmltester;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {
    private final String TAG = "XmlTesterMainActivity";
    private final String CrusaderURL = "http://thesucrusader.com/?feed=rss2";
    ArrayList<RssItem> items = new ArrayList<RssItem>();
    ArrayAdapter<RssItem> adapter;

    @Override //adapter  (example on 181)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<RssItem>(
                this, android.R.layout.simple_list_item_1,
                items);
        setListAdapter(adapter);
        this.
        new FetchItemsTask().execute();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        RssItem r = (RssItem) getListAdapter().getItem(position);
       // if (onListItemClick();)
       //Log.d(TAG, r.toString());


    }


    private class FetchItemsTask extends AsyncTask<Void, Void, ArrayList<RssItem>> {
        @Override
        protected ArrayList<RssItem> doInBackground(Void... params) {
            Activity activity = MainActivity.this;
            if (activity == null) {
                return new ArrayList<RssItem>();
            } else {
                return new CrusaderFetcher().fetchLinks(CrusaderURL);
            }
        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> items0) {
            for(RssItem item:  items0){
                items.add(item);
            }
            adapter.notifyDataSetChanged();
        }
    }
}