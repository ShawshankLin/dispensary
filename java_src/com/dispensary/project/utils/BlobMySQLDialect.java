package com.dispensary.project.utils;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class BlobMySQLDialect extends MySQLDialect {
      public BlobMySQLDialect(){
            super();
            registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
          }

}
