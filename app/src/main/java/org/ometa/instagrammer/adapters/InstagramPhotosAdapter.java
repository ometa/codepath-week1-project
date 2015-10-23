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

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

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
        tvCaption.setText(photo.getCaption());

        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        tvUsername.setText(photo.getUsername());

        TextView tvLikeCount = (TextView) convertView.findViewById(R.id.tvLikeCount);
        tvLikeCount.setText(String.valueOf(photo.getLikesString()));

        loadLocation(photo, convertView);
        loadImages(photo, convertView);

        return convertView;
    }

    private void loadLocation(Photo photo, View convertView) {
        TextView tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
        if (photo.getLocationName() != null) {
            tvLocation.setText(photo.getLocationName());
            // todo: lat and long
            tvLocation.setVisibility(View.VISIBLE);
        } else {
            tvLocation.setVisibility(View.GONE);
        }
    }

    private void loadImages(Photo photo, View convertView) {
        // Initialize photos
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        ImageView ivUserPicture = (ImageView) convertView.findViewById(R.id.ivUserPicture);
        // clear out in case we recycled
        ivPhoto.setImageResource(android.R.color.transparent);
        ivUserPicture.setImageResource(android.R.color.transparent);
        // download using picasso
        Picasso.with(getContext()).load(photo.getImageUrl())
                .placeholder(R.drawable.keep_calm_and_please_wait)
                .into(ivPhoto);

//      http://square.github.io/picasso/

//      .error(R.drawable.user_placeholder_error)

        Picasso.with(getContext()).load(photo.getUserPictureUrl())
                .transform(new CropCircleTransformation())
                .into(ivUserPicture);
    }
}
