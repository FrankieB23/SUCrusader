package edu.susqu.math.kubota.xmltester;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by kubota on 3/19/2015.
 */
public class CrusaderFetcher {

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    String getUrl(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    void parseItems(ArrayList<RssItem> items, XmlPullParser parser) throws XmlPullParserException, IOException {
        RssItem item = null;
        String tag = null;
        String text = null;
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_TAG) {
                String name = parser.getName();
                if(name.equals(RssItem.ITEM_TAG)) {
                    item = new RssItem();
                } else {
                    tag = name;
                }
            }
            else if(eventType == XmlPullParser.TEXT) {
                text = parser.getText();
            }
            else if (eventType == XmlPullParser.END_TAG) {
                String name = parser.getName();
                if(name.equals(RssItem.ITEM_TAG)) {
                    items.add(item);
                    item = null;
                } else if(item != null && name.equals(RssItem.LINK_TAG)) {
                    item.setLink(text);
                } else if(item != null && name.equals(RssItem.CATEGORY_TAG)) {
                    item.setCategory(text);
                } else if(item != null && name.equals(RssItem.CONTENT_TAG)) {
                    item.setContent(text);
                } else if(item != null && name.equals(RssItem.TITLE_TAG)) {
                    item.setTitle(text);
                } else if(item != null && name.equals(RssItem.DATE_TAG)) {
                    item.setPubDate(text);
                }
            }

            eventType = parser.next();
        }
    }

    ArrayList<RssItem> fetchLinks(String url) {
        final String TAG = "XmlTesterMain";
        ArrayList<RssItem> items = new ArrayList<RssItem>();
        try {
            String xmlString = getUrl(url);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            parseItems(items, parser);
        } catch (IOException ex) {
            Log.e(TAG, "Failed to fetch items", ex);
        } catch (XmlPullParserException ex) {
            Log.e(TAG, "Failed to parse items", ex);
        }
        return items;
    }
}
