package iberotec.edu.pe.mylooks.fragment;


import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import iberotec.edu.pe.mylooks.Item.ParteArriba;
import iberotec.edu.pe.mylooks.R;
import iberotec.edu.pe.mylooks.SQLiteHelper;
import iberotec.edu.pe.mylooks.adapter.ParteArribaFragmentAdapter;


public class ParteArribaFragment extends Fragment {

    private RecyclerView recyclerView;
    private SQLiteHelper sqLiteHelper;
    private ArrayList<ParteArriba> arrayList = new ArrayList<ParteArriba>();
    private Cursor cursor;
    private ParteArribaFragmentAdapter parteArribaFragmentAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
                             Bundle savedInstanceState) {
        ViewGroup  viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_recyclerview, container, false);
        recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);


        // Inflate the layout for this fragment
        return viewGroup;
    }


}
