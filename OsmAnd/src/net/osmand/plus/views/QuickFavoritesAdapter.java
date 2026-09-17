package net.osmand.plus.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.osmand.FavouritePoint;
import net.osmand.plus.R;
import java.util.List;

public class QuickFavoritesAdapter extends RecyclerView.Adapter<QuickFavoritesAdapter.ViewHolder> {

    public interface OnFavoriteClickListener {
        void onFavoriteClick(FavouritePoint favorite);
    }

    private final List<FavouritePoint> favorites;
    private final OnFavoriteClickListener listener;

    public QuickFavoritesAdapter(List<FavouritePoint> favorites, OnFavoriteClickListener listener) {
        this.favorites = favorites;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quick_favorite, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FavouritePoint favorite = favorites.get(position);
        holder.favName.setText(favorite.getName());
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFavoriteClick(favorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView favName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            favName = itemView.findViewById(R.id.favName);
        }
    }
}
