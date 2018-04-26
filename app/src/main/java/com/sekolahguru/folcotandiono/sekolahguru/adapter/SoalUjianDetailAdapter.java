package com.sekolahguru.folcotandiono.sekolahguru.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sekolahguru.folcotandiono.sekolahguru.R;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetailGet;

import java.util.List;

/**
 * Created by folcotandiono on 20/04/2018.
 */

public class SoalUjianDetailAdapter extends RecyclerView.Adapter<SoalUjianDetailAdapter.ViewHolder> {
    private List<SoalUjianDetailGet> listSoalUjianDetail;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView id;
        private TextView idSoalUjian;
        private TextView namaSoalUjian;
        private TextView idJenisSoalUjianDetail;
        private TextView namaJenisSoalUjianDetail;
        private TextView soalTulisan;
        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            idSoalUjian = v.findViewById(R.id.id_soal_ujian);
            namaSoalUjian = v.findViewById(R.id.nama_soal_ujian);
            idJenisSoalUjianDetail = v.findViewById(R.id.id_jenis_soal_ujian_detail);
            namaJenisSoalUjianDetail = v.findViewById(R.id.nama_jenis_soal_ujian_detail);
            soalTulisan = v.findViewById(R.id.soal_tulisan);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SOAL_UJIAN_DETAIL_TAMBAH, 0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(ID_SOAL_UJIAN_DETAIL, id.getText().toString());
//                    editor.commit();
//                    ((Activity) v.getContext()).finish();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SoalUjianDetailAdapter(List<SoalUjianDetailGet> listSoalUjianDetail) {
        this.listSoalUjianDetail = listSoalUjianDetail;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SoalUjianDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_soal_ujian_detail, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(listSoalUjianDetail.get(position).getId());
        holder.idSoalUjian.setText(listSoalUjianDetail.get(position).getIdSoalUjian());
        holder.namaSoalUjian.setText(listSoalUjianDetail.get(position).getNamaSoalUjian());
        holder.idJenisSoalUjianDetail.setText(listSoalUjianDetail.get(position).getIdJenisSoalUjianDetail());
        holder.namaJenisSoalUjianDetail.setText(listSoalUjianDetail.get(position).getNamaJenisSoalUjianDetail());
        holder.soalTulisan.setText(listSoalUjianDetail.get(position).getSoalTulisan());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listSoalUjianDetail.size();
    }
}
