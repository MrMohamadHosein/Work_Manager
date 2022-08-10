package ir.MrMohamadHosein.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import java.lang.Exception

class UserInfoWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {

        return try {

            val result = doMyJob()
            Result.success( workDataOf(
                "resultOfWork" to result
            ) )

        } catch (ex: Exception) {

            Result.failure()

        }

    }

    private fun doMyJob() :String {

        val firstName = inputData.getString("name")
        val lastName = inputData.getString("familyName")
        val myPage = inputData.getString("pageAddress")
        val telegramId = inputData.getString("telegram")
        val age = inputData.getInt("age" , 0)

        val result = firstName + " " + lastName + " " + myPage + " " + telegramId + " " + age

        return result
    }


}