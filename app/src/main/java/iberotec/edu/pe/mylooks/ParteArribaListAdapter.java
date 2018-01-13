package iberotec.edu.pe.mylooks;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iberotec.edu.pe.mylooks.Item.ParteArriba;

/**
 * Created by Margot on 18/12/2017.
 */

public class ParteArribaListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ParteArriba> parteArribaArrayList;

    public ParteArribaListAdapter(Context context, int layout, ArrayList<ParteArriba> parteArribaArrayList){
        this.context = context;
        this.layout = layout;
        this.parteArribaArrayList = parteArribaArrayList;

    }

    @Override
    public int getCount() {
        return parteArribaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return parteArribaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtName, txtTipo;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtTipo = (TextView) row.findViewById(R.id.txtTipo);
            holder.imageView = (ImageView) row.findViewById(R.id.imgParteArriba);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        ParteArriba parteArriba = parteArribaArrayList.get(position);

        holder.txtName.setText(parteArriba.getName());
        holder.txtTipo.setText(parteArriba.getTipo());
        byte[] foodImage = parteArriba.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);

        holder.imageView.setImageBitmap(bitmap);

        return row;
    }

}
