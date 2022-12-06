package es.unex.fulltank.ui.historicoRepostaje;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.R;
import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.roomdb.BD;

public class HistoricoAdapter extends RecyclerView.Adapter<HistoricoAdapter.ViewHolderHistorico> {


    private final Context context;
    private ArrayList<HistorialRepostaje> lHistorial;


    public HistoricoAdapter(ArrayList<HistorialRepostaje> lHistorial, Context context) {
        this.lHistorial = lHistorial;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolderHistorico onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_historial, null, false);
        return new ViewHolderHistorico(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHistorico holder, int position) {
        holder.asignarDatos(lHistorial.get(position));
    }


    @Override
    public int getItemCount() {
        return lHistorial.size();
    }


    public class ViewHolderHistorico extends RecyclerView.ViewHolder {
        private final TextView fecha;
        private final TextView nombre;
        private final TextView cantidad;
        private final TextView precio;
        private final TextView rotulo;

        public ViewHolderHistorico(View v) {
            super(v);
            fecha = v.findViewById(R.id.viewFecha);
            nombre = v.findViewById(R.id.viewNombre);
            cantidad = v.findViewById(R.id.viewCantidad);
            precio = v.findViewById(R.id.viewPrecio);
            rotulo = v.findViewById(R.id.viewRotuloHistorial);

        }

        public void asignarDatos(HistorialRepostaje h) {
            fecha.setText("Fecha de repostaje: " + h.getFecha());
            BD instanceBD = BD.getInstance(context);

            AppExecutors.getInstance().diskIO().execute(() -> {
                Gasolinera g = instanceBD.getGasolineraDao().getByCoords(h.getLatitud(), h.getLongitud());

                AppExecutors.getInstance().mainThread().execute(() -> {
                    rotulo.setText("Rotulo: " + g.getRotulo());
                    nombre.setText("Ubicacion: " + g.getDireccion());
                });
            });
            cantidad.setText("Litros repostados: " + h.getLitros());
            precio.setText("Precio: " + h.getPrecio());
        }
    }
}
