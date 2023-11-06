package LessonAboutJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * У класса Statement есть метод executeQeury() который выполняет выборку, то есть команду SELECT.
 * Этот метод помещяет все что мы взяли из таблицы в обьект ResultSet и возвращает его.
 * В общем что бы сохранить то что мы получили с метода executeQeury() нужно создать обьект ResultSet и сохранить туда
 * ResultSet resultSet = statement.executeQuery("SELECT * FROM workers_ss13");
 * 
 * (ResultSet имеет в названии Set, но нечего общего с Set из колекций он не имеет, это просто грубо говоря обозначение что ResultSet - это набор результата)
 * 
 * Результат метода executeQeury() сохраняеться в обьекте ResultSet, но что бы получить этот результат из обьекта в ResultSet есть методы:
 * 1)getBoolean() - возвращает значение boolean
 * 2)getInt() - возвращает значение int
 * 3)getLong() - возвращает значение long
 * 4)getString() - возвращает значение String
 * 6)getDate() - возвращает значение Date, дату
 * 7)getDouble() - возвращает значение double
 * 8)getFloat() - возвращает значение float
 * 9)getObject() - возвращает обьект Object
 * В строке таблицы может быть несколько столбцов, и для того что бы определить из какого столбца мы хотим получить данные нужно указать в параметры:
 * или номер столбика (нумерация столбцов начинаетсья с 1, а не с 0 как везде)
 * или название столбика
 * 
 * Все эти методы для работы именно в конкретной строке, но в таблице может быть больше одной строки
 * Для того что бы получить данные из несольких строк, в ResultSet есть метод next()
 * next() - это по сути указатель, который сначала указывает на условное пространство ПЕРЕД первой строкой,
 * а затем после его вызова он сначала проверяет есть ли строка дальше и если она есть(true) он переходит к ней,
 * потом снова проверяет и переходит(если есть следующая строка, true), как только следующей строки не будет он выкинет false и перкратит работу.
 * 
 * Если положить этот метод в цикл while() тогда можно проитерировать/перебрать все содержимое обьекта ResultSet, то есть все полученные из таблицы
 * 
 * 
 */
public class LessonAboutJDBC_executeQuery {

	public static void main(String[] args) {

		Workers_ss13 worker1 = new Workers_ss13("Леша", 1);
		Workers_ss13 worker2 = new Workers_ss13("Илюха", 4);
		Workers_ss13 worker3 = new Workers_ss13("Бобас", 3);

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test1", "root", "root")) {
			System.out.println("Подключились");

			Statement statement = connection.createStatement();
			/*
			 * Вписываем значения в таблицу
			 * ВАЖНО! 
			 * Если мы впишем строки с двойными кавычками "" Java не поймет и просто закроет одну строку и начнет новую,
			 * Потому строки вписываем в обычных ковычках '', то есть:
			 * " 'строка' "
			 */
			statement.executeUpdate("INSERT workers_ss13 (nameWorker, numberJob) VALUES('"+worker1.getNameWorker()+"', "+worker1.getNumberJob()+"), "
					+ "('"+worker2.getNameWorker()+"', "+worker2.getNumberJob()+"), "
					+ "('"+worker3.getNameWorker()+"', "+worker3.getNumberJob()+")");

			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM workers_ss13");//Хотим получить все данные из таблицы
			
			while(resultSet.next()) {//Устанавливаем наш указатель, он вернет false и прекратит цикл как только строки закончатсья
				int id = resultSet.getInt(1);//Получаем значение из 1-ого столбца
				String name = resultSet.getString(2);//Получаем значение из 2-ого столбца
				int number = resultSet.getInt(3);//Получаем значение из 3-ого столбца
				String race = resultSet.getString(4);//Получаем значение из 4-ого столбца
				
				System.out.println(id+" "+name+" "+number+" "+race);
			}

			
			ResultSet resultSet1 = statement.executeQuery("SELECT * FROM workers_ss13 WHERE race = 'Человек'");//Хотим получить все данные из таблицы но только тех строк где раса указана как Человек
			while(resultSet1.next()) {//Проверяем есть ли слеующая строка и переходим на неё если она есть, если нет false и выходим
				String name = resultSet1.getString("nameWorker");//Указываем в параметры название столбика из которого мы хотим получить данные 
				String race = resultSet1.getString("race");//Указываем в параметры название столбика из которого мы хотим получить данные 
				
				System.out.println(name+" "+race);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
