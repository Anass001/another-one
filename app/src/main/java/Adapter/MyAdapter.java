package Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zeneo.turkeydramaapp.R;
import java.util.List;

import Item.ListItem;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private Context context;
    private List<ListItem> listItems;

    public MyAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        ListItem item = listItems.get(position);
        holder.Title.setText(item.getTitle());
        holder.Description.setText(item.getDescription());
        holder.Preview.setImageResource(item.getImage());
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Title;
        TextView Description;
        ImageView Preview;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Title = (TextView)itemView.findViewById(R.id.title);
            Description = (TextView)itemView.findViewById(R.id.dscrp);
            Preview = (ImageView)itemView.findViewById(R.id.preview_image_view);
        }

        @Override
        public void onClick(View view) {
            /*int position = getAdapterPosition();
            ListItem item = listItems.get(position);
//            Toast.makeText(context, "Message "+item.getTitle()+" "+position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Main2Activity.class);
            intent.putExtra("title", item.getTitle());
            intent.putExtra("description",item.getDescription());
            intent.putExtra("image",item.getImage());
            context.startActivity(intent);*/
        }
    }
}
