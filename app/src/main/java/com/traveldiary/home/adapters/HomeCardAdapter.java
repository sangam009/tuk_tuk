package com.traveldiary.home.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.traveldiary.R;
import com.traveldiary.home.MainActivity;
import com.traveldiary.home.model.PackageOverViewModel;
import com.traveldiary.utils.TextViewPlus;

import java.util.ArrayList;

/**
 * Created by mohit on 18/09/17.
 */

public class HomeCardAdapter extends RecyclerView.Adapter<HomeCardAdapter.MyViewHolder> {
    private final Activity activity;
    private final ArrayList<PackageOverViewModel> list;
    private final CardClickListener cardClickListener;

    public HomeCardAdapter(Activity activity, ArrayList<PackageOverViewModel> list, CardClickListener cardClickListener) {
        this.activity = activity;
        this.list = list;
        this.cardClickListener = cardClickListener;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.package_card, parent, false);
        return new HomeCardAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        PackageOverViewModel packageOverViewModel = list.get(position);
        Picasso.with(activity).load(packageOverViewModel.getPackageDestinationImage()).into(holder.cardImage);
        holder.destinationDuration.setText(packageOverViewModel.getEstimatedTravelTime());
        holder.destinationName.setText(packageOverViewModel.getPackageNamePrimary());
        holder.destinationNameSec.setText(packageOverViewModel.getPackageNameSecondary());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends ViewHolder {
        private final ImageView cardImage;
        private final TextViewPlus destinationName;
        private final TextViewPlus destinationDuration;
        private final TextViewPlus destinationNameSec;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardImage = (ImageView) itemView.findViewById(R.id.destination_image);
            destinationName = (TextViewPlus) itemView.findViewById(R.id.destination_name);
            destinationDuration = (TextViewPlus) itemView.findViewById(R.id.destination_duration);
            destinationNameSec = (TextViewPlus) itemView.findViewById(R.id.destination_name_sec);


        }
    }

    public interface CardClickListener {
        void onCardClick();
    }
}
