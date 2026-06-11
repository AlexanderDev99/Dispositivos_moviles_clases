package com.example.dispositivos_moviles_clases.application.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivos_moviles_clases.R
import com.example.dispositivos_moviles_clases.application.dto.Empresas
import com.example.dispositivos_moviles_clases.databinding.MySpinnerLayoutBinding
import com.squareup.picasso.Picasso

//funcion de orden superor por que acepta parametros y funciones
class CustomAdapter(var OnClick: (Empresas) -> Unit,
                    var onDelete: (Empresas) -> Unit) :
    RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

     var lista: MutableList<Empresas> = ArrayList<Empresas>()

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

        holder.render(lista[position], OnClick, onDelete)
    }

    //Decimos cuantos elementos va a tener el spinner
    override fun getItemCount() = lista.size

    //configuracion personalizada
    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private var localBinding : MySpinnerLayoutBinding = MySpinnerLayoutBinding.bind(view)
        fun render(item: Empresas, onClick: (Empresas) -> Unit,
                   onDelete: (Empresas) -> Unit){
             //usando el texto
            localBinding.txtEmpresa.setText(item.name)
             //mostramos la imagen de la empresa definida en principal usando picasoo
             Picasso.get()
                 .load(item.image)
                 .into(localBinding.imgEmpresa)

            localBinding.imgEmpresa.setOnClickListener {
                onClick(item)
            }
            localBinding.txtEmpresa.setOnClickListener {
                onDelete(item)
            }
        }
    }
}
