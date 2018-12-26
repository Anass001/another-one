package com.zeneo.turkeydramaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Adapter.MyAdapter;
import Item.ListItem;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> titles;
    private ArrayList<String> linkslist;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List <ListItem> listItems;
    private ArrayList arrayList;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWebsite();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle("");
    }

    private void getWebsite(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://33sk.tv/vb/").get();

                    String title = doc.title();

                    Elements links = doc.select("td[class=alt1Active]").select("a[href]");

                    titles = new ArrayList<>();
                    linkslist = new ArrayList<>();

                    for(Element link : links){
                        titles.add(link.text());
                        linkslist.add(link.attr("href"));
                    }


                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "DIFO AKHAY TABI3A",
                            Toast.LENGTH_LONG).show();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        titles.remove(0);
                        linkslist.remove(0);
                        titles.remove(titles.size()-1);
                        linkslist.remove(linkslist.size()-1);
                        titles.remove(titles.size()-1);
                        linkslist.remove(linkslist.size()-1);

                        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                        listItems = new ArrayList<>();

                        for(int i=0; i<titles.size() ;i++){
                            if(titles!= null && titles.size() !=0) {
                                ListItem item = new ListItem(titles.get(i),linkslist.get(i),R.drawable.poster1);
                                listItems.add(item);
                            }
                        }

                        adapter = new MyAdapter(MainActivity.this, listItems);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
}