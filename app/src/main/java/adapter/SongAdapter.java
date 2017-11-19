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

import com.example.mamorky.socialplayer.data.db.pojo.Song;
import com.example.mamorky.socialplayer.data.db.repositories.SongRepository;

/**
 * Created by mamorky on 30/10/17.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    public SongAdapter(@NonNull Context context) {
        super(context, R.layout.item_song, SongRepository.getInstance().getSongs());
    }

    public SongAdapter(@NonNull Context context,String ordenarPor) {
        super(context, R.layout.item_song, SongRepository.getInstance().getSongs(ordenarPor));
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

        songHolder.song.setText(getItem(position).get_name());
        songHolder.artist.setText(getItem(position).get_artist());
        songHolder.imagen.setImageResource(getItem(position).getImageAlbum());

        return view;
    }

    class SongHolder{
        ImageView imagen;
        TextView song;
        TextView artist;
    }
}
