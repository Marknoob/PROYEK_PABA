package petra.ac.id.proyekpaba

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.util.Calendar

class adapterBMI_History(private val listDataBMI_History: ArrayList<BMI_Card>): RecyclerView.Adapter<adapterBMI_History.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    // Untuk Date
    val calendar = Calendar.getInstance()
    val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime())
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var _tvTtanggal: TextView = itemView.findViewById(R.id.tv_tanggal)
        var _tvAge: TextView = itemView.findViewById(R.id.tv_Age)
        var _tvWeigth: TextView = itemView.findViewById(R.id.tv_Weight)
        var _tvHeigth: TextView = itemView.findViewById(R.id.tv_Height)
        var _tvScore: TextView = itemView.findViewById(R.id.tv_scoreBMI)
        var _tvStatus: TextView = itemView.findViewById(R.id.tv_statusBMI)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: BMI_Card)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bmi_history, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listDataBMI_History.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataHistory = listDataBMI_History[position]
        holder._tvTtanggal.text = dataHistory.date
        holder._tvAge.text = dataHistory.age
        holder._tvWeigth.text = dataHistory.weight
        holder._tvHeigth.text = dataHistory.height
        holder._tvScore.text = dataHistory.scoreBMI
        holder._tvStatus.text = dataHistory.statusBMI

        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(dataHistory)
        }
    }

}