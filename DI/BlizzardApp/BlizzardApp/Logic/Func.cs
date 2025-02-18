using MySqlConnector;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms.VisualStyles;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.ListView;
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

            string datos_conexion = @"server=localhost;userid=root;password=1234;database=catalogovideojogos";
            MySqlConnection connection = new MySqlConnection(datos_conexion);

            try
            {
                connection.Open();
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

            string sql = "INSERT INTO users (name, email, password, rol) VALUES (@name, @email, @password, @rol)";

            MySqlCommand statement = new MySqlCommand(sql, connection);

            // Parameters
            statement.Parameters.AddWithValue("@name", userName);
            statement.Parameters.AddWithValue("@email", email);
            statement.Parameters.AddWithValue("@password", password);
            statement.Parameters.AddWithValue("@rol", "user");

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

        internal static bool registerAdmin(string userName, string email, string password)
        {
            MySqlConnection connection = Conectar_BD();

            string sql = "INSERT INTO users (name, email, password, rol) VALUES (@name, @email, @password, @rol)";

            MySqlCommand statement = new MySqlCommand(sql, connection);

            // Parameters
            statement.Parameters.AddWithValue("@name", userName);
            statement.Parameters.AddWithValue("@email", email);
            statement.Parameters.AddWithValue("@password", password);
            statement.Parameters.AddWithValue("@rol", "admin");

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

            string sql = "SELECT * FROM users WHERE rol = @rol";
            MySqlCommand statement = new MySqlCommand( sql, connection);
            statement.Parameters.AddWithValue("@rol", "user");
            try
            {
                connection.Open();
                MySqlDataReader reader = statement.ExecuteReader();

                while (reader.Read())
                {
                    users.Add(new Usuario(reader.GetInt32("id"), reader.GetString("name"), reader.GetString("email"), reader.GetString("password"), reader.GetString("rol")));
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

        internal static bool AdminExists(string userName, string email, string password)
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
                        if (reader["name"].Equals(userName) && reader["email"].Equals(email) && reader["password"].Equals(password) && reader["rol"].Equals("admin"))
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

        internal static Usuario getUser(string usuario, string contrasena)
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

                        if(reader.GetString("name").Equals(usuario) && reader.GetString("password").Equals(contrasena))
                            return new Usuario(reader.GetInt32("id"), reader.GetString("name"), reader.GetString("email"), reader.GetString("password"), reader.GetString("rol"));
                    
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

            return null;
        }

        internal static Videojuego getJuego(string titulo)
        {
            Videojuego videojuego = null;

            MySqlConnection connection = Conectar_BD();

            string sql = @"SELECT * FROM videojogos WHERE titulo = @titulo";
            MySqlCommand query = new MySqlCommand(sql, connection);
            query.Parameters.AddWithValue("@titulo", titulo);

            try
            {
                connection.Open();
                using (MySqlDataReader reader = query.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        string descripcion = reader.IsDBNull(reader.GetOrdinal("descripcion")) ? null : reader.GetString("descripcion");
                        string genero = reader.IsDBNull(reader.GetOrdinal("genero")) ? null : reader.GetString("genero");
                        string desarrollador = reader.IsDBNull(reader.GetOrdinal("desarrollador")) ? null : reader.GetString("desarrollador");
                        string editor = reader.IsDBNull(reader.GetOrdinal("editor")) ? null : reader.GetString("editor");
                        decimal? precio = reader.IsDBNull(reader.GetOrdinal("precio")) ? (decimal?)null : reader.GetDecimal("precio");
                        decimal? precioOriginal = reader.IsDBNull(reader.GetOrdinal("precio_original")) ? (decimal?)null : reader.GetDecimal("precio_original");
                        decimal? valoracion = reader.IsDBNull(reader.GetOrdinal("valoracion")) ? (decimal?)null : reader.GetDecimal("valoracion");
                        bool soporteVR = reader.GetBoolean(reader.GetOrdinal("soporteVR"));
                        string tamano = reader.IsDBNull(reader.GetOrdinal("tamano")) ? null : reader.GetString("tamano");
                        string estado = reader.IsDBNull(reader.GetOrdinal("estado")) ? null : reader.GetString("estado");

                        videojuego = new Videojuego(
                            reader.GetString("titulo"),
                            descripcion,
                            genero,
                            desarrollador,
                            editor,
                            precio,
                            precioOriginal,
                            valoracion,
                            soporteVR,
                            tamano,
                            estado
                        );

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


            return videojuego;
        

    }
    }
}
