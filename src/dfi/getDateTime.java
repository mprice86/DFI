/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dfi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Matthew
 */
public class getDateTime {
    public String get() {
        //Gets the current date and time and formats it        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
