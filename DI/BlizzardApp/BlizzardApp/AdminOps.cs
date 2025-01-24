using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics.Eventing.Reader;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySqlConnector;

namespace BlizzardApp
{
    public partial class AdminOps : Form
    {
        public AdminOps()
        {
            InitializeComponent();
            
        }

        private void btnMostrarUsuarios_Click(object sender, EventArgs e)
        {
            string connectionString = "Server=localhost;Database=prueba_users;User ID=root;Password=1234";
            string query = "SELECT name, email, password, rol FROM users";

            using(MySqlConnection conection = new MySqlConnection(connectionString))
            {
                MySqlCommand command = new MySqlCommand(query, conection);
                MySqlDataAdapter adapter = new MySqlDataAdapter(command);
                DataTable dt = new DataTable();
                adapter.Fill(dt);


                dataGridView1.DataSource = dt;
            }
        }

        private void btnGuardarDatos_Click(object sender, EventArgs e)
        {
            try
            {
                string connectionString = "Server=localhost;Database=prueba_users;User ID=root;Password=1234";
                int totalFilasAgregadas = 0;
                for (int i=0;i<dataGridView1.Rows.Count-1;i++)
                {
                    string nombre = dataGridView1.Rows[i].Cells[0].Value.ToString();
                    string email = dataGridView1.Rows[i].Cells[1].Value.ToString();
                    string password = dataGridView1.Rows[i].Cells[2].Value.ToString();
                    string rol = dataGridView1.Rows[i].Cells[3].Value.ToString();

                    if (!rol.Equals("user") || !rol.Equals("admin"))
                        throw new Exception();
                    else if (rol.Equals("admin") && !password.Equals("Admin1234"))
                        throw new Exception();
                    else
                    {
                        using (MySqlConnection conection = new MySqlConnection(connectionString))
                        {
                            conection.Open();
                            MySqlCommand usuarioEncontrado = new MySqlCommand("Select name, email, password from users where name=@name AND email=@email AND password=@password", conection);
                            usuarioEncontrado.Parameters.AddWithValue("name", nombre);
                            usuarioEncontrado.Parameters.AddWithValue("email", email);
                            usuarioEncontrado.Parameters.AddWithValue("password", password);

                            object filasRegresadas = usuarioEncontrado.ExecuteScalar();

                            if (filasRegresadas == null)
                            {
                                MySqlCommand agregarUsuario = new MySqlCommand("Insert into users (name,email,password,rol) values (@name, @email, @password, @rol)", conection);
                                agregarUsuario.Parameters.AddWithValue("name", nombre);
                                agregarUsuario.Parameters.AddWithValue("email", email);
                                agregarUsuario.Parameters.AddWithValue("password", password);
                                agregarUsuario.Parameters.AddWithValue("rol", rol);
                                int filasAgregadas = agregarUsuario.ExecuteNonQuery();
                                totalFilasAgregadas++;
                            }
                        }
                    }
                }

                MessageBox.Show($"Filas añadidas: {totalFilasAgregadas}", "Mensaje!!", MessageBoxButtons.OK);

            }
            catch (Exception ex) 
            {
                MessageBox.Show("Ha ocurrido un error, comprueba que los datos están bien introducidos!",null,MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }
    }
}
