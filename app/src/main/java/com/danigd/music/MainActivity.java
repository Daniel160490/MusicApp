package com.danigd.music;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvNombre;
    private ImageView imageBand;
    private TextView tvGenero;
    TabHost TbH;
    private TextView txt;
    BandsClass bandas;
    private Adaptador adapter;
    private RecyclerView recyclerView;



    //ArrayList<BandsClass> listBandas=new ArrayList<BandsClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //imageBand = (ImageView) findViewById(R.id.imageBand);
       // recyclerView = (RecyclerView)findViewById(R.id.RecView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


            cargarJson();
            initViews();

    }
    //---METODOS DE MENU ---//
    @Override
    public boolean onCreateOptionsMenu(Menu miMenu){

        getMenuInflater().inflate(R.menu.menu_en_activity, miMenu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem opcionMenu){

        int id = opcionMenu.getItemId();
        if(id == R.id.configuracion){
            return true;
        }
        if(id == R.id.info) {
            ejecutarInfo();
        }
        if(id==R.id.salir){
            finish();
        }

        return super.onOptionsItemSelected(opcionMenu);
    }

    public void ejecutarInfo (){
        Intent i = new Intent(this, BandsClass.class);
        startActivity(i);
    }
    //---METODO PARA SALIR DE LA APP---//
    public void salirApp(View view){
        finish();
    }
    //----------------------------------------------//

    BandsClass banda ;
     ArrayList<BandsClass> datos=new ArrayList<BandsClass>();
     void cargarJson(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://em.wemakesites.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Interface request = retrofit.create(Interface.class);
        final Call<BandsClass> call = request.getArtists();

        consulta(call);
         Interface request2 = retrofit.create(Interface.class);
         final Call<BandsClass> call2= request2.getArtists2();
        consulta(call2);
    }

     void initViews(){
        //cargarJson();
        recyclerView = findViewById(R.id.RecView);
        //  initViews();
        // datos.add(banda);

        recyclerView.setHasFixedSize(false);

        Adaptador adaptador = new Adaptador(datos, new Adaptador.OnItemClickListener() {
            @Override
            public void OnItemClick(ArrayList<BandsClass> dat, int pos) {

                Toast toast1 = Toast.makeText(getApplicationContext(), "Mantén pulsada una imagen para guardarla"+dat.get(pos).getData().getBandName(), Toast.LENGTH_LONG);
                toast1.show();
            }
        });

        recyclerView.setAdapter(adaptador);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


    }
    public void consulta(final Call<BandsClass> call){
        call.enqueue(new Callback<BandsClass>() {
            @Override
            public void onResponse(Call<BandsClass> call, Response<BandsClass> response) {

                banda =(BandsClass) response.body();
                //datos = new ArrayList<>(Arrays.asList(banda));
                //  if(datos != null)
                datos.add((BandsClass) banda);
                //else{
                ///datos=new ArrayList<BandsClass>();
                //datos.add(banda);
                //}
                initViews();

            }
            @Override
            public void onFailure(Call<BandsClass> call, Throwable t) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Error", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });

    }
}
