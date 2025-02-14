package com.example.probandoasynctask

import android.os.AsyncTask
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivityAsyncTask : AppCompatActivity() {

    private lateinit var btn: Button
    private var statusText: TextView?=null;
    private lateinit var  statusProgress: ProgressBar
    private var stringBuilder:StringBuilder?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btn= findViewById<Button>(R.id.btn_async);
        statusText=findViewById<TextView>(R.id.statusText)
        statusText?.movementMethod= ScrollingMovementMethod()
        statusProgress=findViewById<ProgressBar>(R.id.progressBar)
        statusProgress.max = 2
        statusProgress.progress = 0
        stringBuilder = StringBuilder("Async task Demo\n---------------------")

        btn.setOnClickListener {
            MyAsyncTask().execute("Primer paso","segundo paso")
        }
        }

    // inner class can use main class properties
    inner class MyAsyncTask : AsyncTask<String, Int, Int>() {
        override fun onPreExecute() {

            super.onPreExecute()

            stringBuilder?.append("Async task started... \n In PreExecute Method \n")
            statusText?.text = "${stringBuilder.toString()}"
            statusProgress.visibility= View.VISIBLE
            statusProgress.progress = 0
            Log.d("Kotlin","On PreExecute Method")

        }

        // Update the final status by overriding the OnPostExecute method.
        override fun onPostExecute(result: Int?) {
            super.onPostExecute(result)
            stringBuilder?.append("Task Completed.\n")
            statusText?.text = "${stringBuilder.toString()}"
            Log.d("Kotlin","On Post Execute and size of String is:$result")
        }

        override fun doInBackground(vararg params: String?): Int {
            val count:Int=params.size
            var index=0
            while (index<count){

                Log.d("Kotlin","In doInBackground Method and Total parameter passed is :$count " +
                        "and processing $index with value: ${params[index]}")
//Publish the post so that it can be posted to onProgressUpdate method
//to update the main thread
                publishProgress(index+1)
                //Sleeping for 1 seconds
                Thread.sleep(1000)
                index++
            }

            return count;

        }

        // Override the onProgressUpdate method to post the update on main thread
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            if(values[0]!=null) {
                statusProgress?.progress = values[0] as Int
                stringBuilder?.append("Publish post called with ${values[0]}\n")
                statusText?.text = stringBuilder.toString()
            }

        }

    } // Fin de la inner class

}

