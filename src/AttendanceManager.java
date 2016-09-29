import org.json.simple.JSONObject;
import java.io.*;

/**
 * Created by pronoymukherjee on 29/09/16.
 */
public class AttendanceManager {
    String fileName="Info.txt";
    String fileName1="Details.txt";
    public void getAttendanceRecord(JSONObject jsObject){
        String name=(String) jsObject.get("Name");
        String age=(String)jsObject.get("Age");
        String semester=(String)jsObject.get("Semester");
        String rollNumber=(String) jsObject.get("Roll");
        String subjects[]=(String [])jsObject.get("Subjects");
        File f= new File(fileName);
        if(!f.exists()){
            fileWrite(name,age,semester,rollNumber);
        }
        else {
            f.delete();
            fileWrite(name, age, semester, rollNumber);
        }
        try{
            FileWriter obj1= new FileWriter(fileName1,false);
            BufferedWriter obj2=new BufferedWriter(obj1);
            PrintWriter obj3=new PrintWriter(obj2);
            JSONObject attendance= (JSONObject)jsObject.get("attendance");
            String attendanceRecord[]= new String[subjects.length];
            for(int i=0;i<subjects.length;i++) {
                attendanceRecord[i] = (String) attendance.get(subjects[i]);
            }
            for(int i=0;i<subjects.length;i++){
                obj3.print(subjects[i]+": "+attendanceRecord[i]);
            }
    }
}
