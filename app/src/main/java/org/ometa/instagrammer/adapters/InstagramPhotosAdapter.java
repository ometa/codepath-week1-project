package org.ometa.instagrammer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.ometa.instagrammer.R;
import org.ometa.instagrammer.models.Photo;

import java.util.List;

/**
 * Created by devin on 10/22/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<Photo> {

    public InstagramPhotosAdapter(Context context, List<Photo> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get data item for this position
        Photo photo = getItem(position);

        // inflate unless we have recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        tvCaption.setText(photo.caption);

        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);

        // clear out in case we recycled
        ivPhoto.setImageResource(android.R.color.transparent);

        // download using picasso
        Picasso.with(getContext()).load(photo.imageUrl).into(ivPhoto);

        return convertView;
    }
}
