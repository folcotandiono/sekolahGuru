package com.sekolahguru.folcotandiono.sekolahguru.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sekolahguru.folcotandiono.sekolahguru.R;
import com.sekolahguru.folcotandiono.sekolahguru.model.JadwalUjian;

import java.util.List;

/**
 * Created by folcotandiono on 4/25/2018.
 */

public class JadwalUjianPilihAdapter extends RecyclerView.Adapter<JadwalUjianPilihAdapter.ViewHolder> {
    private List<JadwalUjian> listJadwalUjian;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView id;
        private TextView nama;
        private TextView idSoalUjian;
        private TextView namaSoalUjian;
        private TextView tanggal;
        private TextView durasi;
        public ViewHolder(View v) {
            super(v);
            id = v.findViewById(R.id.id);
            nama = v.findViewById(R.id.nama);
            idSoalUjian = v.findViewById(R.id.id_soal_ujian);
            namaSoalUjian = v.findViewById(R.id.nama_soal_ujian);
            tanggal = v.findViewById(R.id.tanggal);
            durasi = v.findViewById(R.id.durasi);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences(SOAL_UJIAN_TAMBAH, 0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString(ID_MATA_PELAJARAN, id.getText().toString());
//                    editor.putString(NAMA_MATA_PELAJARAN, nama.getText().toString());
//                    editor.commit();
                    ((Activity) v.getContext()).finish();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public JadwalUjianPilihAdapter(List<JadwalUjian> listJadwalUjian) {
        this.listJadwalUjian = listJadwalUjian;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public JadwalUjianPilihAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jadwal_ujian, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.id.setText(listJadwalUjian.get(position).getId());
        holder.nama.setText(listJadwalUjian.get(position).getNama());
        holder.idSoalUjian.setText(listJadwalUjian.get(position).getIdSoalUjian());
        holder.namaSoalUjian.setText(listJadwalUjian.get(position).getNamaSoalUjian());
        holder.tanggal.setText(listJadwalUjian.get(position).getTanggal());
        holder.durasi.setText(listJadwalUjian.get(position).getDurasi());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return listJadwalUjian.size();
    }
}

