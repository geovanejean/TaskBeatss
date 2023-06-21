package com.comunidadedevspace.taskbeats.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao //Data Access Objects - the meaning of DAO.
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert (task: Task)

    @Query("Select * from task")
    fun getAll (): LiveData<List<Task>>


    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    //Deletando todos
    @Query("DELETE from task")
    fun deleteAll()

    //Deletando pelo ID
    @Query("DELETE from task WHERE id =:id")
    fun deleteById(id: Int)

}