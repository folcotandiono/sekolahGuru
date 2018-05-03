package com.sekolahguru.folcotandiono.sekolahguru.adapter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sekolahguru.folcotandiono.sekolahguru.R;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaran;
import com.sekolahguru.folcotandiono.sekolahguru.model.SoalUjian;

import java.util.List;

import static com.sekolahguru.folcotandiono.sekolahguru.JadwalUjianTambahActivity.JADWAL_UJIAN_TAMBAH;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.ID_SOAL_UJIAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.NAMA_SOAL_UJIAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianDetailTambahActivity.SOAL_UJIAN_DETAIL_TAMBAH;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.ID_MATA_PELAJARAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.NAMA_MATA_PELAJARAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.SOAL_UJIAN_TAMBAH;

/**
 * Created by folcotandiono on 20/04/2018.
 */

public class SoalUjianAdapter extends RecyclerView.Adapter<SoalUjianAdapter.ViewHolder> {
    private List<SoalUjian> listSoalUjian;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView id;
        private TextView nama;
        private TextView idMataPelajaran;
        private TextView namaMataPelajaran;
        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            nama = v.findViewById(R.id.nama);
            idMataPelajaran = v.findViewById(R.id.id_mata_pelajaran);
            namaMataPelajaran = v.findViewById(R.id.nama_mata_pelajaran);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SOAL_UJIAN_DETAIL_TAMBAH, 0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(ID_SOAL_UJIAN, id.getText().toString());
//                    editor.putString(NAMA_SOAL_UJIAN, nama.getText().toString());
//                    editor.commit();
//                    sharedPreferences = v.getContext().getSharedPreferences(JADWAL_UJIAN_TAMBAH, 0);
//                    editor = sharedPreferences.edit();
//                    editor.putString(ID_SOAL_UJIAN, id.getText().toString());
//                    editor.putString(NAMA_SOAL_UJIAN, nama.getText().toString());
//                    editor.commit();
//                    ((Activity) v.getContext()).finish();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SoalUjianAdapter(List<SoalUjian> listSoalUjian) {
        this.listSoalUjian = listSoalUjian;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SoalUjianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                              int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_soal_ujian, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(listSoalUjian.get(position).getId());
        holder.nama.setText(listSoalUjian.get(position).getNama());
        holder.idMataPelajaran.setText(listSoalUjian.get(position).getIdMataPelajaran());
        holder.namaMataPelajaran.setText(listSoalUjian.get(position).getNamaMataPelajaran());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listSoalUjian.size();
    }
}