package es.unex.fulltank.ui.resenha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.R;
import es.unex.fulltank.datos.modelo.GasolineraResenha;
import es.unex.fulltank.datos.modelo.Usuario;
import es.unex.fulltank.datos.roomdb.BD;
import es.unex.fulltank.datos.roomdb.UsuarioDao;

public class ResenhaAdapter extends RecyclerView.Adapter<ResenhaAdapter.ViewHolderResenha> {

    private final Context context;
    private List<GasolineraResenha> lResenha;

    public ResenhaAdapter(List<GasolineraResenha> lResenha, Context context) {
        this.lResenha = lResenha;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderResenha onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_list_resenha, null, false);
        return new ViewHolderResenha(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderResenha holder, int position) {
        holder.asignarDatos(lResenha.get(position));
    }


    @Override
    public int getItemCount() {
        return lResenha.size();
    }


    public class ViewHolderResenha extends RecyclerView.ViewHolder {

        public TextView usuario;
        public TextView comentario;

        public ViewHolderResenha(@NonNull View itemView) {
            super(itemView);
            usuario = itemView.findViewById(R.id.Rusuario);
            comentario = itemView.findViewById(R.id.Rcomentario);
        }

        public void asignarDatos(GasolineraResenha r) {
            BD instanceBD = BD.getInstance(context);
            UsuarioDao uDao = instanceBD.getUsuarioDao();
            AppExecutors.getInstance().diskIO().execute(() -> {
                Usuario u = uDao.getById(r.getUid());
                AppExecutors.getInstance().mainThread().execute(() -> {
                    usuario.setText(u.getUsuario());
                });
            });
            comentario.setText(r.getComentario());
        }
    }

    public void swap(List<GasolineraResenha> dataset) {
        lResenha = dataset;
        notifyDataSetChanged();
    }

    public void clear() {
        lResenha.clear();
        notifyDataSetChanged();
    }
}
