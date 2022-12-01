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
import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.TipoCombustible;

public class CombustiblesAdapter extends RecyclerView.Adapter<CombustiblesAdapter.ViewHolderCombustible> {

    private final Context context;
    private ArrayList<TipoCombustible> lTipoComb;
    private ArrayList<CombustibleGasolinera> lComb;

    public CombustiblesAdapter(ArrayList<TipoCombustible> lTipoComb, ArrayList<CombustibleGasolinera> lComb, Context context) {
        this.lTipoComb = lTipoComb;
        this.lComb = lComb;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderCombustible onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_combustible, null, false);
        return new ViewHolderCombustible(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCombustible holder, int position) {
        holder.asignarDatos(lTipoComb.get(position), lComb.get(position));
    }

    @Override
    public int getItemCount() {
        return lTipoComb.size();
    }

    public class ViewHolderCombustible extends RecyclerView.ViewHolder {

        private final TextView tipoCombustible;
        private final TextView precio;

        public ViewHolderCombustible(@NonNull View itemView) {
            super(itemView);
            tipoCombustible = itemView.findViewById(R.id.tipo_combustible_tv);
            precio = itemView.findViewById(R.id.precio_combustible_tv);
        }

        public void asignarDatos(TipoCombustible tc, CombustibleGasolinera comb) {
            tipoCombustible.setText(tc.getNombre());
            precio.setText("" + comb.getPrecio());
        }
    }
}
