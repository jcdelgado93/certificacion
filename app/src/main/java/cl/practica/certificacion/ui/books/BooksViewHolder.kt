package cl.practica.certificacion.ui.books

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cl.practica.certificacion.data.entities.Book
import cl.practica.certificacion.databinding.ItemBookBinding
import com.bumptech.glide.Glide

class BooksViewHolder(
    private val itemBinding: ItemBookBinding,
    private val listener: BooksItemListener
) : RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private lateinit var book: Book

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: Book) {
        this.book = item
        itemBinding.tvTitulo.text = item.title
        itemBinding.tvAutor.text = item.author
        Glide.with(itemBinding.ivCaratula)
            .load(item.imageLink)
            .into(itemBinding.ivCaratula)
    }

    override fun onClick(v: View?) {
        listener.onClickedBook(book.id)
    }
}