package ir.MrMohamadHosein.workmanager

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. create work manager =>
        val workManager = WorkManager.getInstance(this)

        // 2. create our work =>
        val notificationWorker = OneTimeWorkRequest.from(NotificationWorker::class.java)

        val userInfoWorker = OneTimeWorkRequestBuilder<UserInfoWorker>()
            .setInputData( workDataOf(
                "name" to "Mohamad Hosein" ,
                "familyName" to "Kazemi" ,
                "pageAddress" to "kazemi_m.h" ,
                "telegram" to ":)" ,
                "age" to 24
            ) )
            .addTag("userWorker")
            .build()

        // 3. work manager should do works =>
        workManager.enqueue( userInfoWorker )


        workManager
            .getWorkInfosByTagLiveData("userWorker")
            .observe(this) {

                val userWorkerInfo = it[0]!!
                if(userWorkerInfo.state == WorkInfo.State.SUCCEEDED  ) {
                    Log.v("testOutput" , userWorkerInfo.outputData.getString("resultOfWork") ?: "null"  )
                }


            }


    }
}