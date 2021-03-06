package id.arieridwan.mombaking.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.arieridwan.mombaking.R;
import id.arieridwan.mombaking.data.api.response.RecipeResponse;
import id.arieridwan.mombaking.features.recipedetail.RecipeDetailActivity;

import static id.arieridwan.mombaking.utils.Constants.RECIPE;

/**
 * Created by arieridwan on 27/08/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private RecipeResponse mData;
    private List<RecipeResponse> mList = new ArrayList<>();

    public RecipeAdapter(List<RecipeResponse> mList) {
        this.mList = mList;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        mData = mList.get(position);
        if (!TextUtils.isEmpty(mData.getImage())) {
            Picasso.with(holder.itemView.getContext())
                    .load(mData.getImage())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .into(holder.mIvBackground);
            holder.mIvOverlay.setVisibility(View.VISIBLE);
        } else {
            holder.mIvOverlay.setVisibility(View.GONE);
        }
        holder.mTvTitle.setText(mData.getName());
        holder.mTvServing.setText("Servings " + mData.getServings());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_background)
        ImageView mIvBackground;

        @BindView(R.id.iv_overlay)
        ImageView mIvOverlay;

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @BindView(R.id.tv_serving)
        TextView mTvServing;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> {
                Intent i = new Intent(itemView.getContext(), RecipeDetailActivity.class);
                i.putExtra(RECIPE, Parcels.wrap(mList.get(getAdapterPosition())));
                itemView.getContext().startActivity(i);
            });
        }

    }

}