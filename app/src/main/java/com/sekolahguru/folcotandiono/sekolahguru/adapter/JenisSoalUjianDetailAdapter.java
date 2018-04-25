package com.sekolahguru.folcotandiono.sekolahguru.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sekolahguru.folcotandiono.sekolahguru.R;
import com.sekolahguru.folcotandiono.sekolahguru.model.JenisSoalUjianDetail;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjianDetail;

import java.util.List;

import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.ID_JENIS_SOAL_UJIAN_DETAIL;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.NAMA_JENIS_SOAL_UJIAN_DETAIL;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.SOAL_UJIAN_DETAIL_TAMBAH;

/**
 * Created by folcotandiono on 23/04/2018.
 */

public class JenisSoalUjianDetailAdapter extends RecyclerView.Adapter<JenisSoalUjianDetailAdapter.ViewHolder> {
    private List<JenisSoalUjianDetail> listJenisSoalUjianDetail;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView id;
        private TextView nama;
        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            nama = v.findViewById(R.id.nama);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SOAL_UJIAN_DETAIL_TAMBAH, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ID_JENIS_SOAL_UJIAN_DETAIL, id.getText().toString());
                    editor.putString(NAMA_JENIS_SOAL_UJIAN_DETAIL, nama.getText().toString());
                    editor.commit();
                    ((Activity) v.getContext()).finish();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public JenisSoalUjianDetailAdapter(List<JenisSoalUjianDetail> listJenisSoalUjianDetail) {
        this.listJenisSoalUjianDetail = listJenisSoalUjianDetail;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public JenisSoalUjianDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jenis_soal_ujian_detail, parent, false);
        JenisSoalUjianDetailAdapter.ViewHolder vh = new JenisSoalUjianDetailAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(JenisSoalUjianDetailAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(listJenisSoalUjianDetail.get(position).getId());
        holder.nama.setText(listJenisSoalUjianDetail.get(position).getNama());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listJenisSoalUjianDetail.size();
    }
}
