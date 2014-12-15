hive-ormlite
============

Light Weight ORM which convert Hive Table records on HDFS to an Java object using annotation.


Usage:

Lets assume you have following table in Hive 

hive> describe student;
OK
name                	string
age                 	int
phoneno             	double
subject             	array<string>
numbers             	map<string,int>
size                	struct<st:string>
union                	uniontype<int,string>


To Map Java Student class, following is class information:
Using Student Class assuming thrift server is running on machine 10.0.0.1 and port 9083

    public class Main 
    {

     public static void main(String args[]) throws TException, DbConfigAnnotaionMissingException, InstantiationException, IllegalAccessException, FactoryInstantiationException {
          HiveBuilderFactory hiveBuilderFactory = HiveBuilderFactory.Instance("thrift://10.0.0.1:9083");
          Text studentRecords = new Text("Akash,1,7798987147,,,");
          Student student = hiveBuilderFactory.deSerialize(Student.class, studentRecords);
          System.out.println(student.age);
          System.out.println(student.phoneNo);
          System.out.println(student.name);
          System.out.println(student.subject);
     }
    
      @HiveDbConfig(db="test",table ="student")
      public class Student {

        String name;
        Integer age;
        Double phoneNo;
        String subject;
        String numbers;
        String size;
        String uni;
      }
    }



This will result in output 

1
7.798987147E9
Akash
null



