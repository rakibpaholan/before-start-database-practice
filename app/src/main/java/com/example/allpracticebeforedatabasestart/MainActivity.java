package com.example.allpracticebeforedatabasestart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private adapter adapterMain;
    private  List<Model> arrayList = new ArrayList<Model>();
    private String [] strings_for_title;
    private String[] strings_for_description;
    private int[] int_for_icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.search_view_id);

        strings_for_title = new  String[] {"Afghanistan","Bangladesh","Bhutan","China","Germany","India","Myanmar","Nepal","Pakistan","Russia"};

        strings_for_description = new String[] {"Afghanistan...","Bangladesh...","Bhutan...","China...","Germany...","India...","Myanmar...",
                "Nepal...","Pakistan...","Russia..."};

        int_for_icon = new  int[] {R.drawable.afgan_flag,R.drawable.bangladesh_flag,
                R.drawable.bhuta_flag,R.drawable.chinaflag_here,R.drawable.germany_flag,
                R.drawable.india_flag,R.drawable.meyanmar_flag,R.drawable.nepal_flag,
                R.drawable.pakflag_flag,R.drawable.russia_flag};

        for (int i = 0; i<strings_for_title.length;i++){
            Model model = new Model(strings_for_title[i],strings_for_description[i],int_for_icon[i]);
            arrayList.add(model);
        }

        adapterMain = new adapter(MainActivity.this,arrayList);
        listView.setAdapter(adapterMain);

        /*===========================================================*/
        /*this process do well i try different way in adapter view */
        /*=============================================================*/

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = strings_for_description[i].toString();
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.action_menu,menu);
        MenuItem mySearchItem = menu.findItem(R.id.search_menu_id);
        SearchView searchView = (SearchView)mySearchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    adapterMain.find("");
                    listView.clearTextFilter();
                }else {
                    adapterMain.find(s);
                }

                return true;
            }


        });

        return true;
    }


}
