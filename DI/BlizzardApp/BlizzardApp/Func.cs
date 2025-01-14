using MySqlConnector;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.StartPanel;

namespace BlizzardApp
{
    internal class Func
    {

        /*
         Establecer la conexión con la base de datos
        */
        public static MySqlConnection Conectar_BD()
        {

            string datos_conexion = @"server=localhost;userid=root;password=1234;database=prueba_users";
            MySqlConnection connection = new MySqlConnection(datos_conexion);

            try
            {
                connection.Open();
                Console.WriteLine("Connected to MySQL DB!");
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
         
        public static void CreateTable(MySqlConnection connection)
        {
            string createTableSql = "CREATE TABLE IF NOT EXISTS users (" +
                                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                                    "name VARCHAR(255) NOT NULL," +
                                    "email VARCHAR(255) NOT NULL)";
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
        */

        /*
         Ejemplo para insertar algo en una tabla
         
        public static void InsertData(MySqlConnection connection)
        {
            string insertSql = "INSERT INTO users (name, email) VALUES (@name, @email)";
            MySqlCommand insertCommand = new MySqlCommand(insertSql, connection);

            // Parameters
            insertCommand.Parameters.AddWithValue("@name", "John Doe");
            insertCommand.Parameters.AddWithValue("@email", "john@example.com");

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
        */

        /*
         Ejemplo de una consulta
         
        public static void RetrieveData(MySqlConnection connection)
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
        */

        /*
         Ejemplo de una actualización en una tabla
         
        public static void UpdateData(MySqlConnection connection)
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
        */

        /*
         Ejemplo para eliminar datos de una tabla
         
        public static void DeleteData(MySqlConnection connection)
        {
            string deleteSql = "DELETE FROM users WHERE name = @name";
            MySqlCommand deleteCommand = new MySqlCommand(deleteSql, connection);

            // Parameter
            deleteCommand.Parameters.AddWithValue("@name", "John Doe");

        }
        */

        internal static bool UserExists(string usuario)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "SELECT * FROM users";
            MySqlCommand query = new MySqlCommand(sql, connection);

            try
            {
                connection.Open();
                using (MySqlDataReader reader = query.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        if (reader["name"].Equals(usuario))
                        {
                            Console.WriteLine("Usuario "+usuario+" encontrado!");
                            return true;
                        }
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

            return false;

        }

        internal static bool PasswordMatches(string usuario, string contrasena)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "SELECT password FROM users WHERE name = @name";
            MySqlCommand query = new MySqlCommand(sql, connection);
            query.Parameters.AddWithValue("@name",usuario);
            bool coincide = false;
            try
            {
                connection.Open();
                using (MySqlDataReader reader = query.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        if (reader["password"].Equals(contrasena))
                        {
                            Console.WriteLine("Usuario y contraseña correctos!");
                            coincide = true;
                        }
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

            return coincide;

        }

        internal static bool registerUser(string userName, string email, string password)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "INSERT INTO users (name, email, password) VALUES (@name, @email, @password)";

            MySqlCommand statement = new MySqlCommand(sql, connection);

            // Parameters
            statement.Parameters.AddWithValue("@name", userName);
            statement.Parameters.AddWithValue("@email", email);
            statement.Parameters.AddWithValue("@password", password);

            try
            {
                connection.Open();
                int rowsAffected = statement.ExecuteNonQuery();
                Console.WriteLine($"Inserted {rowsAffected} row(s)!");

                return true;
            }
            catch (Exception ex)
            {
                Console.WriteLine($"Error: {ex.Message}");
            }
            finally
            {
                connection.Close();
            }

            return false;
        }

        internal static List<Usuario> getUsers()
        {
            List<Usuario> users = new List<Usuario>();

            MySqlConnection connection = Conectar_BD();

            string sql = "SELECT * FROM users";
            MySqlCommand statement = new MySqlCommand( sql, connection);

            try
            {
                connection.Open();
                MySqlDataReader reader = statement.ExecuteReader();

                while (reader.Read())
                {
                    users.Add(new Usuario(reader.GetString("name"), reader.GetString("email"), reader.GetString("password")));
                }

                return users;
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

        internal static bool UserExists(string userName, string email, string password)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "SELECT * FROM users";
            MySqlCommand query = new MySqlCommand(sql, connection);

            try
            {
                connection.Open();
                using (MySqlDataReader reader = query.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        if (reader["name"].Equals(userName) && reader["email"].Equals(email) && reader["password"].Equals(password))
                        {
                            return true;
                        }
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

            return false;
        }

        internal static bool EmailExists(string email)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "SELECT * FROM users";
            MySqlCommand query = new MySqlCommand(sql, connection);

            try
            {
                connection.Open();
                using (MySqlDataReader reader = query.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        if (reader["email"].Equals(email))
                        {
                            return true;
                        }
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

            return false;
        }
    }
}
