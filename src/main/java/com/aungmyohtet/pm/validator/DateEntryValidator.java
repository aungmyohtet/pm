package com.aungmyohtet.pm.validator;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.aungmyohtet.pm.entity.Task;

@Component("dateValidator")
public class DateEntryValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Task task = (Task) target;
        Date scheduleStartDate = task.getScheduledStartDate();
        Date scheduleFinishedDate = task.getScheduledFinishedDate();
        Date actualStartDate = task.getActualStartDate();
        Date actualFinishedDate = task.getActualFinishedDate();
        if (scheduleStartDate == null) {
            errors.rejectValue("scheduledStartDate", "required", "Starting Date is required.");
        }
        if (scheduleFinishedDate == null) {
            errors.rejectValue("scheduledFinishedDate", "required", "Starting Date is required.");
        }
        if (actualStartDate == null) {
            errors.rejectValue("actualStartDate", "required", "Starting Date is required.");
        }
        if (actualFinishedDate == null) {
            errors.rejectValue("actualFinishedDate", "required", "Starting Date is required.");
        }
        if (scheduleStartDate.compareTo(scheduleFinishedDate) > 0) {
            errors.rejectValue("scheduledStartDate", "required", "Schedule Finished Date is greater than Schedule Start Date.");
        }else if (actualStartDate.compareTo(actualFinishedDate) > 0) {
            errors.rejectValue("actualStartDate", "required", "Actual Starting Date is greater than Actual Finished Date.");
        }else if (scheduleStartDate.compareTo(actualStartDate) > 0) {
            errors.rejectValue("scheduledStartDate", "required", "Schedule Starting Date is less than Actual Start Date.");
        }
    }
}