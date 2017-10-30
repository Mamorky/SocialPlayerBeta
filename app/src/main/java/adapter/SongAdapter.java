package adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import org.w3c.dom.Text;

import java.util.List;

import pojo.Song;
import repositories.SongRepository;

/**
 * Created by mamorky on 30/10/17.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context) {
        super(context, R.layout.item_song, SongRepository.getInstance().getSongs());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SongHolder songHolder;
        View view = convertView;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.item_song,null);
            songHolder = new SongHolder();

            songHolder.imagen = (ImageView)view.findViewById(R.id.imgAlbum);
            songHolder.artist = (TextView)view.findViewById(R.id.txvArtist);
            songHolder.song = (TextView)view.findViewById(R.id.txvSong);

            view.setTag(songHolder);
        }
        else{
            songHolder = (SongHolder)view.getTag();
        }

        songHolder.song.setText(getItem(position).getName());
        songHolder.artist.setText(getItem(position).getArtists());
        songHolder.imagen.setImageResource(R.drawable.shape_album);

        return view;
    }

    class SongHolder{
        ImageView imagen;
        TextView song;
        TextView artist;
    }
}
