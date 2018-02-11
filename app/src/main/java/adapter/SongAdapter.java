package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.SongRepository;
import com.example.mamorky.socialplayer.ui.base.BaseContext;
import com.example.mamorky.socialplayer.util.UtilsImages;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mamorky on 30/10/17.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context,String ordenarPor) {
        super(context, R.layout.item_song, new ArrayList<Song>());
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SongHolder songHolder;
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_song,null);
            songHolder = new SongHolder();

            songHolder.imagen = (ImageView)view.findViewById(R.id.imgAlbum);
            songHolder.artist = (TextView)view.findViewById(R.id.txvArtist);
            songHolder.song = (TextView)view.findViewById(R.id.txvSong);

            songHolder.song.setSelected(true);
            songHolder.artist.setSelected(true);

            view.setTag(songHolder);
        }
        else{
            songHolder = (SongHolder)view.getTag();
        }

        Song song = getItem(position);

        songHolder.song.setText(song.get_name());
        if(song.getArtist_name() != null)
            songHolder.artist.setText(song.getArtist_name());
        else
            songHolder.artist.setText("------------");

        songHolder.imagen.setImageResource(R.drawable.shape_album);


        //UtilsImages.putImageCover(song.get_name(),songHolder.imagen);

        return view;
    }

    class SongHolder{
        ImageView imagen;
        TextView song;
        TextView artist;
    }
}
