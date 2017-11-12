package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mamorky.socialplayer.R;

import java.util.ArrayList;

import com.example.mamorky.socialplayer.data.db.pojo.Artist;
import com.example.mamorky.socialplayer.data.db.repositories.ArtistRepository;
import com.example.mamorky.socialplayer.util.UtilsImages;

/**
 * Created by mamorky on 6/11/17.
 */

/**Adapter de Artist */
public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>{

    private ArrayList<Artist> artists;

    public ArtistAdapter(ArrayList<Artist> artists){
        this.artists = artists;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_artist,null);
        ArtistViewHolder artistViewHolder = new ArtistViewHolder(view);
        return artistViewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {

        //Esto se ha de pasar a una clase de utilidades
        RoundedBitmapDrawable imageRound = UtilsImages.roundImages(holder.imgArtist,artists.get(position).getArtistImage());

        holder.imgArtist.setImageDrawable(imageRound);
        holder.txvArtistName.setText(artists.get(position).getArtistName());
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgArtist;
        private TextView txvArtistName;

        public ArtistViewHolder(View view){
            super(view);
            imgArtist = (ImageView)view.findViewById(R.id.imgArtist);
            txvArtistName = (TextView)view.findViewById(R.id.txvArtistName);
        }
    }
}
