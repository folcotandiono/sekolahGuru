package com.sekolahguru.folcotandiono.sekolahguru.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sekolahguru.folcotandiono.sekolahguru.R;
import com.sekolahguru.folcotandiono.sekolahguru.model.MataPelajaran;

import java.util.List;

/**
 * Created by folcotandiono on 19/04/2018.
 */

public class MataPelajaranAdapter extends RecyclerView.Adapter<MataPelajaranAdapter.ViewHolder> {
    private List<MataPelajaran> listMataPelajaran;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView nama;
        private TextView kelas;
        private TextView guru;
        public ViewHolder(View v) {
            super(v);
            nama = v.findViewById(R.id.mata_pelajaran_nama);
            kelas = v.findViewById(R.id.mata_pelajaran_kelas);
            guru = v.findViewById(R.id.mata_pelajaran_guru);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MataPelajaranAdapter(List<MataPelajaran> listMataPelajaran) {
        this.listMataPelajaran = listMataPelajaran;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MataPelajaranAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
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
        holder.nama.setText(listMataPelajaran.get(position).getNama());
        holder.guru.setText(listMataPelajaran.get(position).getNamaGuru());
        holder.kelas.setText(listMataPelajaran.get(position).getNamaKelas());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listMataPelajaran.size();
    }
}
