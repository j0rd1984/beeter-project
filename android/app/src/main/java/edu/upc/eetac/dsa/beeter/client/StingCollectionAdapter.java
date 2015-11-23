package edu.upc.eetac.dsa.beeter.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import edu.upc.eetac.dsa.beeter.R;
import edu.upc.eetac.dsa.beeter.client.entity.StingCollection;


/**
 * Created by Jordi on 16/11/2015.
 */
public class StingCollectionAdapter extends BaseAdapter {
    private StingCollection stingCollection;
    private LayoutInflater layoutInflater;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public StingCollectionAdapter(Context context, StingCollection stingCollection){
        layoutInflater = LayoutInflater.from(context);
        this.stingCollection = stingCollection;
    }

    @Override
    public int getCount() {
        return stingCollection.getStings().size();
    }

    @Override
    public Object getItem(int position) {
        return stingCollection.getStings().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String textname = stingCollection.getStings().get(position).getId();
        String textsubject = stingCollection.getStings().get(position).getSubject();
        long textcreationtimestamp = stingCollection.getStings().get(position).getCreationTimestamp();


        viewHolder.textname.setText(textname);
        viewHolder.textsubject.setText(textsubject);
        viewHolder.textcreationtimestamp.setText(sdf.format(textcreationtimestamp));
        return convertView;
    }

    class ViewHolder{
        TextView textname;
        TextView textsubject;
        TextView textcreationtimestamp;

        ViewHolder(View row){
            this.textname = (TextView) row
                    .findViewById(R.id.textname);
            this.textsubject = (TextView) row
                    .findViewById(R.id.textsubject);
            this.textcreationtimestamp = (TextView) row
                    .findViewById(R.id.textcreationtimestamp);
        }
    }
}