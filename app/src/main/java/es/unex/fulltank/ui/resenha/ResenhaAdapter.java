package es.unex.fulltank.ui.resenha;

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
import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

public class ResenhaAdapter extends RecyclerView.Adapter<ResenhaAdapter.ViewHolderResenha> {

    private final Context context;
    ArrayList<GasolineraResenha> lResenha;

    public ResenhaAdapter(ArrayList<GasolineraResenha> lResenha, Context context) {
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

        private final TextView usuario;
        private final TextView comentario;

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
}
