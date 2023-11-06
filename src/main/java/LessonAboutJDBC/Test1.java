package LessonAboutJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test1 {
	
		static ResultSet resultSet1;
		
	    public static void main(String[] args) {
	    	
	    	Workers_ss13 ws1 = new Workers_ss13("Бобас",3);
	    	Workers_ss13 ws2 = new Workers_ss13("Илюха",4);
	    	
	    	
	        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root")) {
				Statement statement = connection.createStatement();
				
				System.out.println("Подключились");
				
				//statement.executeUpdate("CREATE TABLE weapons( idWeapons int primary key auto_increment, damageWeapons int, typeWeapons varchar(20))");
				
				String s = ws1.getNameWorker();
				
				int i = statement.executeUpdate("INSERT workers_ss13 (nameWorker, numberJob) VALUES ('"+ws1.getNameWorker()+"', "+ws1.getNumberJob()+"), ('"+ws2.getNameWorker()+"',"+ws2.getNumberJob()+")");
				
				System.out.println(i);
				
		    	ResultSet resultSet2 = statement.executeQuery("SELECT * FROM workers_ss13 WHERE race = 'Человек'");//Хотим получить все данные из таблицы но только тех строк где раса указана как Человек
				while(resultSet2.next()) {//Проверяем есть ли слеующая строка и переходим на неё если она есть, если нет false и выходим
					String name = resultSet2.getString("nameWorker");//Указываем в параметры название столбика из которого мы хотим получить данные 
					String race = resultSet2.getString("numberJob");//Указываем в параметры название столбика из которого мы хотим получить данные 
					
					System.out.println(name+" "+race);
				}
				
				System.out.println();
				
				int f=0;
				resultSet1 = statement.executeQuery("SELECT * FROM workers_ss13 WHERE race = 'Человек'");
				while(f<=4) {
					d(statement);
					f++;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    
	    public static void d(Statement statement) throws SQLException {
	    	//resultSet1 = statement.executeQuery("SELECT * FROM workers_ss13 WHERE race = 'Человек'");
			//while(resultSet1.next()) {//Проверяем есть ли слеующая строка и переходим на неё если она есть, если нет false и выходим
	    		resultSet1.next();
				String name = resultSet1.getString("nameWorker");//Указываем в параметры название столбика из которого мы хотим получить данные 
				String race = resultSet1.getString("numberJob");//Указываем в параметры название столбика из которого мы хотим получить данные 
				
				System.out.println(name+" "+race);
			//}
	    }
	    
	    public void n(ResultSet resultSet1) throws SQLException {
	    	resultSet1.next();
	    }
	}


