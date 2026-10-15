package com.example.day2

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AiChatBot : AppCompatActivity() {
    private val l = mutableListOf<M>()
    private lateinit var a: Adp
    private lateinit var r: RecyclerView
    private lateinit var e: EditText
    private lateinit var b: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ai_chat_bot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, i ->
            val s = i.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(s.left, s.top, s.right, s.bottom)
            i
        }

        r = findViewById(R.id.rv)
        e = findViewById(R.id.et)
        b = findViewById(R.id.btn)

        a = Adp(l)
        r.layoutManager = LinearLayoutManager(this)
        r.adapter = a

        val g = GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = "YOUR_API_KEY"
        )

        b.setOnClickListener {
            val txt = e.text.toString()
            if (txt.isNotBlank()) {
                l.add(M(txt, true))
                a.notifyItemInserted(l.size - 1)
                r.scrollToPosition(l.size - 1)
                e.text.clear()

                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val res = g.generateContent(txt)
                        withContext(Dispatchers.Main) {
                            l.add(M(res.text ?: "", false))
                            a.notifyItemInserted(l.size - 1)
                            r.scrollToPosition(l.size - 1)
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            l.add(M(ex.message ?: "", false))
                            a.notifyItemInserted(l.size - 1)
                            r.scrollToPosition(l.size - 1)
                        }
                    }
                }
            }
        }
    }
}