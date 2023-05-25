package com.stc.attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.model.ChamCong;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>{

    private Context mContext;



    private List<ChamCong> mListChamCong = new ArrayList<>();

    public AttendanceAdapter(List<ChamCong> mListChamCong) {
        this.mListChamCong = mListChamCong;
    }

    public void setData(List<ChamCong> list)
    {
        this.mListChamCong = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_attendance, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        ChamCong chamCong = mListChamCong.get(position);
        if(chamCong == null)
        {
            return;
        }
        holder.txtTen.setText(chamCong.getTen());
        holder.txtNgay.setText(chamCong.getNgayChamCong().toString());
        holder.txtMa.setText(chamCong.getMaNhanVien());

    }

    @Override
    public int getItemCount() {
        if(mListChamCong != null)
        {
            return mListChamCong.size();
        }
        return 0;
    }
    public class AttendanceViewHolder extends RecyclerView.ViewHolder{
        private TextView txtMa, txtTen, txtNgay;
        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMa = itemView.findViewById(R.id.rvMa);
            txtTen = itemView.findViewById(R.id.rvTen);
            txtNgay = itemView.findViewById(R.id.rvNgay);
        }
    }




}
