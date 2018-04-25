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

import java.util.List;

import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.ID_MATA_PELAJARAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.NAMA_MATA_PELAJARAN;
import static com.sekolahguru.folcotandiono.sekolahguru.SoalUjianTambahActivity.SOAL_UJIAN_TAMBAH;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class MataPelajaranPilihAdapter extends RecyclerView.Adapter<MataPelajaranPilihAdapter.ViewHolder> {
    private List<MataPelajaran> listMataPelajaran;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView id;
        private TextView nama;
        private TextView idKelas;
        private TextView namaKelas;
        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            nama = v.findViewById(R.id.nama);
            idKelas = v.findViewById(R.id.id_kelas);
            namaKelas = v.findViewById(R.id.nama_kelas);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SOAL_UJIAN_TAMBAH, 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(ID_MATA_PELAJARAN, id.getText().toString());
                    editor.putString(NAMA_MATA_PELAJARAN, nama.getText().toString());
                    editor.commit();
                    ((Activity) v.getContext()).finish();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MataPelajaranPilihAdapter(List<MataPelajaran> listMataPelajaran) {
        this.listMataPelajaran = listMataPelajaran;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MataPelajaranPilihAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mata_pelajaran, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(listMataPelajaran.get(position).getId());
        holder.nama.setText(listMataPelajaran.get(position).getNama());
        holder.idKelas.setText(listMataPelajaran.get(position).getIdKelas());
        holder.namaKelas.setText(listMataPelajaran.get(position).getNamaKelas());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listMataPelajaran.size();
    }
}
