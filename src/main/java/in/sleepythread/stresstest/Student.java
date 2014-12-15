package in.sleepythread.stresstest;

import in.sleepythread.HiveDbConfig;
import in.sleepythread.HiveDbConfig;
import in.sleepythread.HiveDbConfig;

@HiveDbConfig(db="default",table = "default")
public class Student {

    String name;
    Integer age;
    Double phoneNo;

    public Student(){}

    public Student(String name,Integer age, Double phoneNo){
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
    }
}
