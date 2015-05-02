package edu.susqu.math.kubota.xmltester;

/**
 * Created by kubota on 3/20/2015.
 */
public class RssItem {
    final static String ITEM_TAG = "item";
    final static String TITLE_TAG = "title";
    final static String LINK_TAG = "link";
    final static String CONTENT_TAG = "content:encoded";
    final static String CATEGORY_TAG = "category";
    final static String DATE_TAG = "pubDate";

    private String title;
    private String link;
    private String pubDate;
    private String content;
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    //@Override
    public String toString() {
        return category + "\n" + title + "\n" + pubDate +
                "\n"+
        //"\nLink=" + link
         "\npubDate="  +  pubDate +"\n";   // content == article ...
        /** "\nCategory=" +  category + "\"  + "Title="    title +  "\npubDate="  +  pubDate +
         "\n"; */
          }
  //  }
}
