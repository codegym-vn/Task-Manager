package com.demo.task.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Quy Duc on 12/21/2017.
 */

public class Utils {

    public static String convertDateToStringNoTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str = dateFormat.format(date);

        return str;
    }
    public static Date getDateByPosition(int day) {
        Calendar calendar = Calendar.getInstance();
        if (day == 6){
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }else{
            calendar.set(Calendar.DAY_OF_WEEK, day + 2);
        }
        return calendar.getTime();
    }
}
