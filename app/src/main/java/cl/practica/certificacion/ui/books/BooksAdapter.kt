package cl.practica.certificacion.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.databinding.ItemBookBinding
import java.util.ArrayList

class BooksAdapter(
    private val listener: BooksItemListener
) : RecyclerView.Adapter<BooksViewHolder>() {

    private val items = ArrayList<Book>()

    fun setItems(items: ArrayList<Book>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding: ItemBookBinding =
            ItemBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return BooksViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}