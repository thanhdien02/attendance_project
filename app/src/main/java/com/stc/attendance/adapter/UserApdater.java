package com.stc.attendance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class UserApdater  extends RecyclerView.Adapter<UserApdater.UserViewHolder>{

    private Context mContext;

    private List<TaiKhoan> mListUser = new ArrayList<>();

    public UserApdater(List<TaiKhoan> mListUser) {
        this.mListUser = mListUser;
    }
    public void setData(List<TaiKhoan> list)
    {
        this.mListUser = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        TaiKhoan taiKhoan = mListUser.get(position);
        if(taiKhoan == null)
        {
            return;
        }
        holder.uTen.setText(taiKhoan.getName().trim());
        holder.uMa.setText(taiKhoan.getMaNhanVien().toString());
        holder.uMail.setText(taiKhoan.getEmail().trim());
    }

    @Override
    public int getItemCount() {
        if(mListUser != null)
        {
            return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView uMa, uTen, uMail;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            uMa = itemView.findViewById(R.id.us_ma);
            uTen = itemView.findViewById(R.id.us_ten);
            uMail = itemView.findViewById(R.id.us_mail);
        }
    }
}
