package com.example.audioplayer.function.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audioplayer.R;
import com.example.audioplayer.function.main.model.ChooseModelMain;

import java.util.List;

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ViewHolder> {
    private List<ChooseModelMain> folderList;
    private Context context;

    public AdapterMain(Context context, List<ChooseModelMain> folderList) {
        this.folderList = folderList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_adapter_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChooseModelMain chooseModelMain = folderList.get(position);
        holder.imgFolder.setImageResource(chooseModelMain.getImageFolder());
        holder.nameFolder.setText(chooseModelMain.getNameFolder());
        holder.sizeFolder.setText(chooseModelMain.getSizeFolder());
    }

    @Override
    public int getItemCount() {
        return folderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFolder;
        private TextView nameFolder, sizeFolder;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFolder = itemView.findViewById(R.id.main_item_img);
            nameFolder = itemView.findViewById(R.id.main_item_name);
            sizeFolder = itemView.findViewById(R.id.main_item_size);
        }
    }
}
