package es.unex.fulltank.ui.gasolineras;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.fulltank.R;
import es.unex.fulltank.bd.elembd.Gasolinera;

public class GasolinerasAdapter extends RecyclerView.Adapter<GasolinerasAdapter.ViewHolderGasolineras> {

    private static RecyclerViewClickListener itemListener;
    private final Context context;
    ArrayList<Gasolinera> lGasolineras;

    public GasolinerasAdapter(ArrayList<Gasolinera> lGasolineras, RecyclerViewClickListener itemListener,
                              Context context) {
        this.lGasolineras = lGasolineras;
        GasolinerasAdapter.itemListener = itemListener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderGasolineras onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_gasolinera, null, false);
        return new ViewHolderGasolineras(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGasolineras holder, int position) {
        holder.asignarDatos(lGasolineras.get(position));
    }

    @Override
    public int getItemCount() {
        return lGasolineras.size();
    }

    public interface RecyclerViewClickListener {
        void recyclerViewListClicked(View v, int position);
    }

    public class ViewHolderGasolineras extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView rotulo;
        private final TextView calle;
        private final TextView municipio;

        public ViewHolderGasolineras(@NonNull View itemView) {
            super(itemView);
            rotulo = itemView.findViewById(R.id.viewRotulo);
            calle = itemView.findViewById(R.id.viewCalle);
            municipio = itemView.findViewById(R.id.viewMunicipio);
            itemView.setOnClickListener(this);
        }

        public void asignarDatos(Gasolinera g) {
            rotulo.setText(g.getRotulo());
            calle.setText(g.getDireccion());
            municipio.setText(g.getMunicipio());
        }

        @Override
        public void onClick(View v) {
            itemListener.recyclerViewListClicked(v, this.getLayoutPosition());
        }
    }
}
