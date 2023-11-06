package LessonAboutJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * На ряду с интерфейсом Statement есть интерфейс PreparedStatement.
 * С помощью которого так же можно передавать команды на сервер, но с PreparedStatement это делаеться немного по другому.
 * PreparedStatement подготавливает и форматирует запрос.
 * 
 * Для того что бы создать обьект PreparedStatement нужно вызвать метод prepareStatement() у обьекта Connection.
 * (В общем все тоже самое что и с Statement)
 * В атрибуты метода prepareStatement() сразу кладеться SQL запрос к серверу(таблице), именно этот запрос и будет позже выполняться.
 * (По сути как я понимаю, один запрос = один обьект)
 * И в этот SQL запрос устанавливаються знаки подстановки, которые выглядят как знаки вопроса, то есть вот так ?.
 * На место этих знаков потом будут подставляться значения.
 * Таким образом мы можем записать сразу кучку однотипных запросов просто подставляя под знаки подстановки разные данные,
 * а не писать одниковые запросы с разницей в одно значение по 10 раз.
 * 
 * Для того что бы подставить определенные значения под знаки подстановки (?), в интерфейса PreparedStatement есть методы:
 * 1)setString()
 * 2)setInt()
 * 3)setDate()
 * 4)setFloat()
 * 5)setBoolean()
 * 6)setNull()
 * 7)setTime()
 * 8)setBigDecimal()
 * 9)setDouble()
 * 10)setLong()
 * 
 * Все эти методы принимают в атрибуты два значения:
 * Первое это число обозначающее порядковый номер знака подстановки(?) в который мы хотим положить значение,
 * тут тоже счет начинаеться не 0, а с 1
 * Второе это само значение
 * 
 * Так же PreparedStatement имеет все три метода для выполнения запроса как и у Statement, это
 * execute() executeUpdate() executeQuery() и делают они все РОВНО ТОЖЕ САМОЕ что и в Statement.
 * Только за одним исключением они НЕ принимают запрос в аргументы, потому как сам запрос пишеться в начале в метод prepareStatement(),
 * эти методы лишь выполняют/отправляют запрос и все(ну и возвращают то что должны boolean или количество строк и все такое)
 * 
 */

public class LessonAboutJDBC_PreparedStatement {

	public static void main(String[] args) {

		/*
		 * Пишем final запрос на добавление работника который нельзя изменить, и устанавливаем знаки подстановки (?) в местах куда хотим класть наши данные
		 */
		final String queryAddWorkers_ss13 = "INSERT workers_ss13 (nameWorker, numberJob) VALUES (?, ?)";
		Workers_ss13 worker = new Workers_ss13("Алина",2);
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test1", "root", "root")) {
			PreparedStatement ps = connection.prepareStatement(queryAddWorkers_ss13);//создаем обьект PreparedStatement и сразу передаем в метод запрос который мы будем выполнять
			
			ps.setString(1,worker.getNameWorker());//Устанавливаем что мы передаем имя работника на место первого знака подстановки (?)
			ps.setInt(2, 2);//Устанавлвиаем что мы передаем номер рабооты на место второго знака подстановки (?)
			
			ps.executeUpdate();//просто выполняем запрос 
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
