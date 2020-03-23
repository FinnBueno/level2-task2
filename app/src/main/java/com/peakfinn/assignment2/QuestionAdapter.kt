package com.peakfinn.assignment2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_question.view.*

/**
 * Created by Finn Bon on 23/03/2020.
 */
class QuestionAdapter(private val activity: MainActivity, private val questions: MutableList<Question>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            itemView.tvQuestion.text = question.text;
            itemView.setOnClickListener {
                showSnack("The answer for this question is ${if (question.isCorrect) "correct" else "incorrect"}")
            }
        }
    }

    private fun createSnack(content: String) = Snackbar.make(activity.window.decorView.findViewById(android.R.id.content), content, Snackbar.LENGTH_LONG)

    private fun showSnack(content: String) = createSnack(content).show()

    fun verifyAnswer(swipedQuestion: RecyclerView.ViewHolder, givenAnswer: Boolean) {
        val question = questions[swipedQuestion.adapterPosition]

        if (question.isCorrect == givenAnswer) {
            questions.removeAt(swipedQuestion.adapterPosition)
            notifyItemRemoved(swipedQuestion.adapterPosition)
        } else {
            notifyItemChanged(swipedQuestion.adapterPosition)
        }

        showSnack("Your answer is ${if (question.isCorrect == givenAnswer) "correct" else "wrong"}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

}