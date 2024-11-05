package com.example.android_sy3;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_sy3.R;

import java.util.List;
class ListItem {
    String text;
    int imageResource;

    ListItem(String text, int imageResource) {
        this.text = text;
        this.imageResource = imageResource;
    }
}

class CustomAdapter extends ArrayAdapter<ListItem> {
    public CustomAdapter(Context context, List<ListItem> items) {
        super(context, 0, items);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }

        ListItem item = getItem(position);
        ImageView imageView = convertView.findViewById(R.id.imageViewInItem);
        imageView.setImageResource(item.imageResource);
        TextView textView = convertView.findViewById(R.id.textViewInItem);
        textView.setText(item.text);

        return convertView;
    }
}
