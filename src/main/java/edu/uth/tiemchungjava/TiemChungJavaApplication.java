package edu.uth.tiemchungjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TiemChungJavaApplication {

    public static void main(String[] args) {

        SpringApplication.run(TiemChungJavaApplication.class, args);
        //JPA ( Java Persisence API )
        //JPA chuan API cua JAVA ---> CSDL de khong can thong qua nam qua sau SQL
        //CSDL ( datab, table, column, row )
        //OOP khai bao class ---> Relationship cua cac bang
        //ORM : Mapping Object ---> Table
        //Property ---> Column
        //Primary key --->
        //CRUD co ban ( Object )
        //Lazy loading, Caching
        //Transaction :
        /*
        Begin tran
            //cau lenh sql
            commit tran
            catch :
                rollback
        End tran
         */
        //procedure, function, trigger...
        //JPA ho tro tren nhieu dang he quan tri co so du lieu
        //SQL server ( MS Sql ), Mysql, Oracle, Postgress
        //---> JPA khong phai mot cai famework ma la mot chuan ( specification )
        //Hibernate, EclipseLink, OpenJPA
    }

}
