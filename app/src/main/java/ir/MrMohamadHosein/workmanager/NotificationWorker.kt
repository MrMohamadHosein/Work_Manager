package ir.MrMohamadHosein.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class NotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            doMyJob()
            Result.success()

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob() {

        for (i in 0..100000) {
            Log.v("testWorker", i.toString())
        }

    }


}