package my.masdico.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ListMatchAdapter(private val list: ArrayList<BasketballTeam>) : RecyclerView.Adapter<ListMatchAdapter.ListViewHolder>() {

    private lateinit var itemListener: OnItemClickListener
    private lateinit var buttonListener: OnItemClickListener

    interface OnItemClickListener{
        fun onItemClicked(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        itemListener = listener
    }

    fun setOnButtonClickListener(listener: OnItemClickListener){
        buttonListener = listener
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_team_name)
        var tvDetail: TextView = itemView.findViewById(R.id.tv_team_details)
        var imgLogo: ImageView = itemView.findViewById(R.id.img_list)
        var btnCount: Button = itemView.findViewById(R.id.btn_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.list_match, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val basketballTeam = list[position]

        Glide.with(holder.itemView.context)
            .load(basketballTeam.imgLogo)
            .apply(RequestOptions().override(55,55))
            .into(holder.imgLogo)
        holder.tvName.text = basketballTeam.name
        holder.tvDetail.text = basketballTeam.details

        holder.btnCount.setOnClickListener { buttonListener.onItemClicked(position) }
        holder.itemView.setOnClickListener { itemListener.onItemClicked(position) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}