package com.peakfinn.assignment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var questions = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questions.add(Question("A 'val' and 'var' are the same", false))
        questions.add(Question("Mobile Application Development grants 12 ECTS", false))
        questions.add(Question("A Unit in Kotlin corresponds to a void in Java", false))
        questions.add(Question("In Kotlin, 'when' replaces the 'switch' operator from Java", true))

        initViews()
    }

    private fun initViews() {
        rvQuestion.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestion.adapter = QuestionAdapter(this, questions)
        rvQuestion.addItemDecoration(
            DividerItemDecoration(
                this@MainActivity,
                DividerItemDecoration.VERTICAL
            )
        )

        createItemTouchHelper()
    }

    private fun createItemTouchHelper() {
        val onSwipe = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, position: Int) =
                (rvQuestion.adapter as QuestionAdapter)
                    .verifyAnswer(
                        viewHolder,
                        position == ItemTouchHelper.RIGHT
                    )
        }

        ItemTouchHelper(onSwipe).attachToRecyclerView(rvQuestion)
    }
}
