package LessonAboutJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Для управления БД через JDBC есть интерфейс Statement.
 * С помощью этого интерйфеса можно посылать SQL команды через Java на сервер MySQL для взаимодействия с БД.
 * Для этого в интерфейсе есть три метода в которые мы вписываем операции MySQL:
 * 1) executeUpdate() - этот метод выполняет различные команды такие как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE, ALTER TABLE и так далее...(НО НЕ SELECT)
 * Он возвращает число количества строк затронутых операцией в таблице(например обновили три строчки командой UPDATE, вернуло 3), 
 * так же может вернуть 0 если ни одна строка не была затронута операицей или например команда вообще не затрагивает строки в таблице, как CREATE TABLE.
 * 2) executeQuery() - выполяняет команду SELECT. Возвращает обьект ResultSet который содержит результат команды.
 * Про этот метод чуть подробнее позже
 * 3) execute() - выполняет ВСЕ команды, но возвращает boolean значение:
 * true если команда возвращает набор строк командой SELECT, 
 * и false если не возвращает, а выполняет обычное действие типо INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE, ALTER TABLE и так далее...
 * 
 * Для того что бы вызвать эти методы необходимо создать обьект Statement.
 * Но как его создать если Statement это интерфейс?
 * В общем, для начала просто как это сделать:
 * Statement statement = connection.createStatement();
 * Мы создаем ссылочную переменную Statement, а затем вызываем у нашего обьекта Connection метод createStatement() который создаст обьект Statement.
 * Как я понимаю, тут используеться паттерн фабричный(или фабричный метод).
 * Как я понимаю в методе createStatement() создаеться обьект какого то класса который реализовует интерфейс Statement, и все.
 * Вот...
 * 
 * Еще есть возможность выполнять команды пакетным способом, то есть сразу несколько штук за раз.
 * Это повышает производительность и скорость приложения, потому как java будет обращяться к СУБД значительно меньше раз.
 * Что бы отправлять запросы пакетами в Statement есть методы:
 * 1) addBatch() - этот метод кладет указаный в атрибутах запрос в пакет
 * 2) executeBatch() - этот метод выполняет все запросы в пакете
 * 3) clearBatch() - очищает пакет ПОЛНОСТЬЮ, удаляя ВСЕ запросы. Удалить из пакета какой то запрос отдельно от остальных нельзя.
 * 
 */
public class LessonAboutJDBC_Statement {

	public static void main(String[] args) {
		
		try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test1","root","root")){
			System.out.println("Соединение установленно");
			/*
			 * Создаем обьект Statement методом createStatement() обьекта connection
			 */
			Statement statement = connection.createStatement();
			/*
			 * Посылаем команду добавления столбика в таблицу на сервер MySQL 
			 * Столбик добавиться, но мы получим на консоль false, потому как мы не возвращали никаких строк командой SELECT
			 */
			System.out.println(statement.execute("ALTER TABLE weapons ADD weight int"));
			/*
			 * Посылаем команду об удалении только что созданого столбика в таблице на сервер MySQL
			 * Столбик удалиться, но мы получим на консоль 0, потому как мы не задели никаких существующих строк в этой таблице
			 */
			System.out.println(statement.executeUpdate("ALTER TABLE weapons DROP COLUMN weight"));
			
			
			statement.addBatch("INSERT weapons(damageWeapons, typeWeapons) VALUES (4, 'Knife')");//Кладем запрос в пакет
			statement.addBatch("INSERT weapons(damageWeapons, typeWeapons) VALUES (6, 'Sword')");//Кладем запрос в пакет
			statement.addBatch("INSERT weapons(damageWeapons, typeWeapons) VALUES (6, 'Axe')");//Кладем запрос в пакет
			
			statement.executeBatch();//Выполняем сразу весь пакет запросов

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
