package com.example.dispositivos_moviles_clases.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivos_moviles_clases.R
import com.example.dispositivos_moviles_clases.databinding.MySpinnerLayoutBinding
import com.example.dispositivos_moviles_clases.dto.Empresas
import com.squareup.picasso.Picasso
class CustomAdapter(var lista: List<Empresas>) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return CustomViewHolder(
            inflater.inflate(
                R.layout.my_spinner_layout,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {

        holder.render(lista[position])
    }

    //Decimos cuantos elementos va a tener el spinner
    override fun getItemCount() = lista.size

    //configuracion personalizada
    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var localBinding : MySpinnerLayoutBinding = MySpinnerLayoutBinding.bind(view)
         fun render(item: Empresas){
             //usando el texto
            localBinding.txtEmpresa.setText(item.name)
             //mostramos la imagen de la empresa definida en principal usando picasoo
             Picasso.get()
                 .load(item.image)
                 .into(localBinding.imgEmpresa)


        }
    }
}
