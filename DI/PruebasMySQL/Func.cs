using MySqlConnector;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PruebasMySQL
{
    internal class Func
    {

        public static MySqlConnection conectar_BD(string data_conexion)
        {
            MySqlConnection connection = new MySqlConnection(data_conexion);

            try
            {
                connection.Open();
                Console.WriteLine("Connected to MySQL!");
                return connection;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }
            
            return null;
        }

        /*
         Ejemplo para crear una tabla 
         */
        public static void createTable(MySqlConnection connection)
        {
            string createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                                    "email VARCHAR(255) NOT NULL," +
                                    "password VARCHAR(255) NOT NULL)";
            MySqlCommand createTableCommand = new MySqlCommand(createTableSql, connection);

            try
            {
                connection.Open();
                createTableCommand.ExecuteNonQuery();
                Console.WriteLine("Table created!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }

        }
        /*
         Ejemplo para insertar algo en una tabla
         */
        public static void insertData(MySqlConnection connection)
        {
            string insertSql = "INSERT INTO users (email, password) VALUES (@email, @password)";
            MySqlCommand insertCommand = new MySqlCommand(insertSql, connection);

            // Parameters
            insertCommand.Parameters.AddWithValue("@email", "john@example.com");
            insertCommand.Parameters.AddWithValue("@password", "1234");

            try
            {
                connection.Open();
                int rowsAffected = insertCommand.ExecuteNonQuery();
                Console.WriteLine($"Inserted {rowsAffected} row(s)!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }

        }

        /*
         Ejemplo de una consulta
         */ 
        public static void retrieveData(MySqlConnection connection)
        {
            string query = "SELECT * FROM users";
            MySqlCommand queryCommand = new MySqlCommand(query, connection);

            try
            {
                connection.Open();
                using (MySqlDataReader reader = queryCommand.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        Console.WriteLine($"ID: {reader["id"]}, Name: {reader["name"]}, Email: {reader["email"]}");
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }

        }

        /*
         Ejemplo de una actualización en una tabla
         */ 
        public static void updateData(MySqlConnection connection)
        {
            string updateSql = "UPDATE users SET email = @newEmail WHERE name = @name";
            MySqlCommand updateCommand = new MySqlCommand(updateSql, connection);

            // Parameters
            updateCommand.Parameters.AddWithValue("@newEmail", "updated@example.com");
            updateCommand.Parameters.AddWithValue("@name", "John Doe");

            try
            {
                connection.Open();
                int rowsAffected = updateCommand.ExecuteNonQuery();
                Console.WriteLine($"Updated {rowsAffected} row(s)!");
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }

        }

        /*
         Ejemplo para eliminar datos de una tabla
         */ 
        public static void deleteData(MySqlConnection connection) 
        {
            string deleteSql = "DELETE FROM users WHERE name = @name";
            MySqlCommand deleteCommand = new MySqlCommand(deleteSql, connection);

            // Parameter
            deleteCommand.Parameters.AddWithValue("@name", "John Doe");

        }
    }
}
