package com.danigd.music;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ImageView;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel on 22/11/17.
 */

public class Adaptador extends RecyclerView.Adapter<Adaptador.BandViewHolder> {

    //private View.OnClickListener listener;
    public ArrayList<BandsClass> datos=new ArrayList<BandsClass>();

    public final OnItemClickListener listener;
    //--CONSTRUCTOR POR DEFECTO---//
    public Adaptador(ArrayList<BandsClass> datos, OnItemClickListener listener) {
        this.datos = datos;
        this.listener = listener;
    }

    interface OnItemClickListener {
        void OnItemClick(ArrayList<BandsClass> dat, int pos);
    }

    //Declara los elementos de la vista
    public static class BandViewHolder
            extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_genre;
        private ImageView imageView;

        //Asigna los elementos declarados a la vista
        public BandViewHolder(View itemView) {
            super(itemView);

            tv_name = (TextView)itemView.findViewById(R.id.tvNombre2);
            tv_genre = (TextView)itemView.findViewById(R.id.tvGenero);
            imageView = (ImageView) itemView.findViewById(R.id.imageBand);

        }

        /*Muestra los datos de la banda en el item*/
        public void bindBand(final ArrayList<BandsClass> t, final OnItemClickListener listener) {

            tv_name.setText(t.get(getAdapterPosition()).getData().getBandName());
            tv_genre.setText(t.get(getAdapterPosition()).getData().getDetails().getGenre());
            new ImageDownloader (imageView).execute(t.get(getAdapterPosition()).getData().getPhoto());
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    listener.OnItemClick(t,getAdapterPosition());
                }
            });
        }
    }
    @Override
    public BandViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item, viewGroup, false);

        BandViewHolder tvh = new BandViewHolder(itemView);
        //Devuelve la vista//
        return tvh;
    }

    @Override
    public void onBindViewHolder(BandViewHolder viewHolder, int pos) {

        /* Llama a bindBand, para que "pinte" los datos de la banda que se le pasa como parámetro*/
        viewHolder.bindBand(datos, (OnItemClickListener) listener);
    }

    @Override
    public int getItemCount() {return datos.size();}

}
