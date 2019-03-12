package com.mivas.samplemvvm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*

class AddEditNoteActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "com.mivas.samplemvvm.EXTRA_ID"
        const val EXTRA_TITLE = "com.mivas.samplemvvm.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.mivas.samplemvvm.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.mivas.samplemvvm.EXTRA_PRIORITY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        priorityNumberPicker.minValue = 1
        priorityNumberPicker.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            titleField.setText(intent.getStringExtra(EXTRA_TITLE))
            descriptionField.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            priorityNumberPicker.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        } else {
            title = "Add Note"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveNote() {
        if (titleField.text.toString().trim().isBlank() || descriptionField.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Can not insert empty note!", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent().apply {
            putExtra(EXTRA_TITLE, titleField.text.toString())
            putExtra(EXTRA_DESCRIPTION, descriptionField.text.toString())
            putExtra(EXTRA_PRIORITY, priorityNumberPicker.value)
            putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
        }

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}