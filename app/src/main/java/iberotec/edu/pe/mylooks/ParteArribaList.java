package iberotec.edu.pe.mylooks;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import iberotec.edu.pe.mylooks.Item.ParteArriba;

/**
 * Created by Margot on 18/12/2017.
 */

public class ParteArribaList extends AppCompatActivity {

    GridView gridView;
    ArrayList<ParteArriba> list;
    ParteArribaListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_parte_arriba);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ParteArribaListAdapter(this, R.layout.parte_arriba_fragment_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = AgregarActivity.sqLiteHelper.getData("SELECT * FROM P");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String tipo = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new ParteArriba(name, tipo, image, id));
        }
        adapter.notifyDataSetChanged();
    }
}
