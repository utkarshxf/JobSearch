package com.flashcall.me.data.local.mapper

import com.flashcall.me.data.local.entity.JobEntity
import com.orion.templete.data.model.Job
import com.orion.templete.data.model.PrimaryDetails

object JobMapper {
    fun Job.toEntity(): JobEntity {
        return JobEntity(
            id = id,
            company_name = company_name,
            salary_max = salary_max,
            salary_min = salary_min,
            title = title,
            city_location = city_location,
            amount = amount,
            button_text = button_text,
            content = content,
            job_type = job_type,
            qualification = qualification,
            whatsapp_no = whatsapp_no,
            primaryDetails_Experience = primary_details?.Experience,
            primaryDetails_Fees_Charged = primary_details?.Fees_Charged,
            primaryDetails_Job_Type = primary_details?.Job_Type,
            primaryDetails_Place = primary_details?.Place,
            primaryDetails_Qualification = primary_details?.Qualification,
            primaryDetails_Salary = primary_details?.Salary
        )
    }

    fun JobEntity.toJob(): Job {
        return Job(
            id = id,
            company_name = company_name,
            salary_max = salary_max,
            salary_min = salary_min,
            title = title,
            city_location = city_location,
            amount = amount,
            button_text = button_text,
            content = content,
            job_type = job_type,
            qualification = qualification,
            whatsapp_no = whatsapp_no,
            primary_details = PrimaryDetails(
                Experience = primaryDetails_Experience,
                Fees_Charged = primaryDetails_Fees_Charged,
                Job_Type = primaryDetails_Job_Type,
                Place = primaryDetails_Place,
                Qualification = primaryDetails_Qualification,
                Salary = primaryDetails_Salary
            )
        )
    }
}