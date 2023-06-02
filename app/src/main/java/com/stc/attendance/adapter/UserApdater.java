package com.stc.attendance.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.atharvakale.facerecognition.R;
import com.stc.attendance.HomeActivity;
import com.stc.attendance.LoginActivity;
import com.stc.attendance.MainActivity;
import com.stc.attendance.ProfileActivity;
import com.stc.attendance.SalaryCalculationActivity;
import com.stc.attendance.model.ChamCong;
import com.stc.attendance.model.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public class UserApdater  extends RecyclerView.Adapter<UserApdater.UserViewHolder>{

    private Context mContext;

    public UserApdater(Context mContext) {
        this.mContext = mContext;
    }

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

        ImageView popupbutton = holder.itemView.findViewById(R.id.menupopbutton);
        popupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Lựa chọn:");
                String[] names= {"Tính lương","Lịch sử chấm công"};

                builder.setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which)
                        {
                            case 0:
                                Toast.makeText(view.getContext(), "Mot", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(view.getContext(), SalaryCalculationActivity.class);
                                intent.putExtra("taikhoan", taiKhoan);
                                ((Activity) view.getContext()).startActivityForResult(intent, 1);
                                break;
                            case 1:
                                Toast.makeText(view.getContext(), "Hai", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
                Toast.makeText(view.getContext(), "Da duoc", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(view.getContext(), LoginActivity.class);
//                ((Activity) view.getContext()).startActivity(intent);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
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
        private ImageView imgv;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            uMa = itemView.findViewById(R.id.us_ma);
            uTen = itemView.findViewById(R.id.us_ten);
            uMail = itemView.findViewById(R.id.us_mail);
            imgv = itemView.findViewById(R.id.menupopbutton);
        }
    }
}
