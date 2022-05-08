package com.example.ambrosia.Forums;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.ambrosia.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class ChatAdapter extends BaseAdapter {
    private List<ChatMessage> messages;
    private LayoutInflater layoutInflater;
    private Context context;
    // Create a storage reference from our app
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private Uri monImage;


    public ChatAdapter(Context aContext,List<ChatMessage> messages){
        this.context=aContext;
        this.messages=messages;
        layoutInflater = LayoutInflater.from(aContext);
        //Log.d("IMAGES",storageRef.child("coffee.png"));
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        int imageId=0;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.chat_test_frag, null);
            holder = new ViewHolder();
            holder.imageMessage = (ImageView) convertView.findViewById(R.id.photo);
            holder.textMessage = (TextView) convertView.findViewById(R.id.message_text);
            holder.imageLink =(TextView) convertView.findViewById(R.id.imageLink);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        View finalConvertView = convertView;
        ChatMessage message = this.messages.get(position);
        holder.textMessage.setText(message.getMessageText());
        if(message.getPhoto()!=null) {
            StorageReference mImageRef = storageRef.child("Images/" + message.getPhoto());
            mImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    monImage = uri;
                    Glide.with(finalConvertView)
                            .load(monImage)
                            .into(holder.imageMessage);
                }
            });
        }

        //setBitmapFromURL("test",holder.imageMessage);
        holder.imageLink.setText(storageRef.child("Images/test").getPath());

        return finalConvertView;
    }

    static class ViewHolder {
        ImageView imageMessage;
        TextView textMessage,imageLink;
    }
    private void setBitmapFromURL(String imageString, ImageView imageView) {
        StorageReference mImageRef = storageRef.child("Images/"+imageString);
        final long ONE_MEGABYTE = 1024 * 1024;
        mImageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bm);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.d("IMAGES",exception.toString());
            }
        });

    }
}
