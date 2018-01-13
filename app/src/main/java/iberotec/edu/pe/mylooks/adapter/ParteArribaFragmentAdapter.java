package iberotec.edu.pe.mylooks.adapter;

import android.app.Activity;
import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import iberotec.edu.pe.mylooks.Item.ParteArriba;
import iberotec.edu.pe.mylooks.Item.SetViewHolder;
import iberotec.edu.pe.mylooks.R;


public class ParteArribaFragmentAdapter extends RecyclerView.Adapter<SetViewHolder> {

    private Activity activity;
    List<ParteArriba> parteArribas = Collections.emptyList();

    public ParteArribaFragmentAdapter(Activity activity, List<ParteArriba> parteArribas){
        this.activity = activity;
        this.parteArribas= parteArribas;

    }

    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_parte_arriba,parent,false );
        return  new SetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SetViewHolder holder, int position) {
        

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
