package id.ac.ui.cs.mobileprogramming.aulia_rosyida.dietskuy;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    //konstruktor
    public MainAdapter(Activity context, List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inisialisasi view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //inisialisasi main data
        MainData data = dataList.get(position);
        //inisialisasi database
        database = RoomDB.getInstance(context);
        //set text on text view
        holder.textView.setText(data.getText());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi Main data
                MainData d = dataList.get(holder.getAdapterPosition());

                //ambil id
                int sID = d.getID();
                //get text
                String sText = d.getText();

                //membuat dialog
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);

                //inisialisai width dan height
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.WRAP_CONTENT;

                //set layout
                dialog.getWindow().setLayout(width, height);
                dialog.show();

                //inisialisasi dan assign variable
                EditText editText = dialog.findViewById(R.id.edit_text2);
                Button btUpdate = dialog.findViewById(R.id.bt_update);

                //melakukan set text pada edit text
                editText.setText(sText);

                btUpdate.setOnClickListener(v -> {
                    dialog.dismiss();

                    //get teks terupdate dari edit text
                    String uText = editText.getText().toString().trim();

                    //update text in database
                    database.mainDao().update(sID, uText);

                    //memberi notifikasi ketika data terupdate
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    notifyDataSetChanged();
                });
            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //inisialisasi main data
                MainData d = dataList.get(holder.getAdapterPosition());

                //hapus teks dari database
                database.mainDao().delete(d);

                //memberi notifikasi ketika data sudah dihapus
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //assign variable
            textView = itemView.findViewById(R.id.text_view);
            btEdit = itemView.findViewById(R.id.bt_edit);
            btDelete = itemView.findViewById(R.id.bt_delete);

        }
    }
}
