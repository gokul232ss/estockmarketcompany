package com.fse.estockmarketcompany.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public final class CommonConstant {

    public final static SimpleDateFormat formatterDB = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    public final static SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH);
    private static final Map<String, String> result = new HashMap<>();

    public static Map<String, String> getSuccessMapResponse() {
        result.put("message", "success");
        return result;
    }

    public static Date getSqlDateFormatIfNotNull(String dateVal) {
        try {
            return !ObjectUtils.isEmpty(dateVal) ?
                    new Date(formatterDB.parse(dateVal).getTime()) : null;
        } catch (ParseException e) {
            log.error("Error in Parsing the date" + e.getMessage());
            return null;
        }
    }

    public static String getUIDateFormatIfNotNull(Timestamp dateVal) {
        return formatter.format(dateVal);
    }

}
