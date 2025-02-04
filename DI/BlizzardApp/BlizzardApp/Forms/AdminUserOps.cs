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
    public partial class AdminUserOps : Form
    {
        public AdminUserOps()
        {
            InitializeComponent();
            
        }

        private void btnMostrarUsuarios_Click(object sender, EventArgs e)
        {
            string connectionString = "Server=localhost;Database=catalogovideojogos;User ID=root;Password=1234";
            string query = "SELECT * FROM users";

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
                string connectionString = "Server=localhost;Database=catalogovideojogos;User ID=root;Password=1234";
                using (MySqlConnection connection = new MySqlConnection(connectionString))
                {
                    connection.Open();
                    using (MySqlTransaction transaction = connection.BeginTransaction())
                    {
                        try
                        {
                            
                            MySqlCommand getExistingRowsCommand = new MySqlCommand("SELECT id FROM users", connection, transaction);
                            List<int> existingIds = new List<int>();
                            using (MySqlDataReader reader = getExistingRowsCommand.ExecuteReader())
                            {
                                while (reader.Read())
                                {
                                    existingIds.Add(reader.GetInt32("id"));
                                }
                            }

                            
                            List<int> idsEnGrid = new List<int>();

                            foreach (DataGridViewRow row in dataGridView1.Rows)
                            {
                                if (row.IsNewRow) continue;

                                
                                string idStr = row.Cells["id"].Value?.ToString();
                                int id = string.IsNullOrEmpty(idStr) ? 0 : int.Parse(idStr);
                                string nombre = row.Cells["name"].Value?.ToString();
                                string email = row.Cells["email"].Value?.ToString();
                                string password = row.Cells["password"].Value?.ToString();
                                string rol = row.Cells["rol"].Value?.ToString();

                                // Validar rol y contraseña de administrador
                                if (string.IsNullOrEmpty(rol) || (rol != "user" && rol != "admin"))
                                {
                                    throw new Exception($"Rol inválido en la fila {row.Index + 1}. Solo se permiten 'user' y 'admin'.");
                                }

                                if (rol == "admin" && password != "Admin1234")
                                {
                                    throw new Exception($"Contraseña incorrecta para el administrador en la fila {row.Index + 1}. La contraseña debe ser 'Admin1234'.");
                                }

                                
                                if (id == 0) // Fila nueva (sin ID)
                                {
                                   
                                    MySqlCommand insertCommand = new MySqlCommand(
                                        "INSERT INTO users (name, email, password, rol) VALUES (@name, @email, @password, @rol)", connection, transaction);
                                    insertCommand.Parameters.AddWithValue("@name", nombre);
                                    insertCommand.Parameters.AddWithValue("@email", email);
                                    insertCommand.Parameters.AddWithValue("@password", password);
                                    insertCommand.Parameters.AddWithValue("@rol", rol);
                                    insertCommand.ExecuteNonQuery();
                                }
                                else
                                {
                                    
                                    if (!existingIds.Contains(id))
                                    {
                                        MessageBox.Show($"La fila con ID {id} no es válida como nueva fila. Asegúrate de que las filas nuevas tengan ID = 0.",
                                            "Error en los datos", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                                        transaction.Rollback();
                                        return;
                                    }

                                   
                                    MySqlCommand checkCommand = new MySqlCommand(
                                        "SELECT COUNT(*) FROM users WHERE id = @id AND name = @name AND email = @email AND password = @password AND rol = @rol", connection, transaction);
                                    checkCommand.Parameters.AddWithValue("@id", id);
                                    checkCommand.Parameters.AddWithValue("@name", nombre);
                                    checkCommand.Parameters.AddWithValue("@email", email);
                                    checkCommand.Parameters.AddWithValue("@password", password);
                                    checkCommand.Parameters.AddWithValue("@rol", rol);

                                    int count = Convert.ToInt32(checkCommand.ExecuteScalar());

                                    if (count == 0) 
                                    {
                                        MySqlCommand updateCommand = new MySqlCommand(
                                            "UPDATE users SET name = @name, email = @email, password = @password, rol = @rol WHERE id = @id", connection, transaction);
                                        updateCommand.Parameters.AddWithValue("@id", id);
                                        updateCommand.Parameters.AddWithValue("@name", nombre);
                                        updateCommand.Parameters.AddWithValue("@email", email);
                                        updateCommand.Parameters.AddWithValue("@password", password);
                                        updateCommand.Parameters.AddWithValue("@rol", rol);
                                        updateCommand.ExecuteNonQuery();
                                    }
                                }

                                
                                idsEnGrid.Add(id);
                            }

                            
                            List<int> idsParaEliminar = existingIds.Except(idsEnGrid).ToList();

                            foreach (int id in idsParaEliminar)
                            {
                                MySqlCommand deleteCommand = new MySqlCommand("DELETE FROM users WHERE id = @id", connection, transaction);
                                deleteCommand.Parameters.AddWithValue("@id", id);
                                deleteCommand.ExecuteNonQuery();
                            }

                            transaction.Commit();
                            MessageBox.Show("Base de datos actualizada correctamente.");
                        }
                        catch (Exception ex)
                        {
                            transaction.Rollback();
                            MessageBox.Show($"Error al actualizar la base de datos: {ex.Message}");
                        }
                    }
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show("Ha ocurrido un error, comprueba que los datos están bien introducidos!", null, MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }

        private void btnEliminarUsuario_Click(object sender, EventArgs e)
        {
            string connectionString = "Server=localhost;Database=catalogovideojogos;User ID=root;Password=1234";

            if (string.IsNullOrWhiteSpace(txtUsuarioID.Text) || !int.TryParse(txtUsuarioID.Text, out int id))
            {
                MessageBox.Show("Por favor, introduce un ID válido.");
                return;
            }

            using (MySqlConnection connection = new MySqlConnection(connectionString))
            {
                connection.Open();
                MySqlCommand cmd = new MySqlCommand("DELETE FROM users WHERE id = @id AND rol = 'user'", connection);
                cmd.Parameters.AddWithValue("@id", id);

                int rowsAffected = cmd.ExecuteNonQuery();
                if (rowsAffected > 0)
                {
                    MessageBox.Show("Usuario eliminado con éxito.");
                    btnMostrarUsuarios_Click(null, null); // Actualizar DataGridView
                }
                else
                {
                    MessageBox.Show("El usuario no existe o no tiene rol 'user'.");
                }
            }
        }

        private void btnConsultaUsuario_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtUsuarioName.Text))
            {
                MessageBox.Show("Por favor, introduce un nombre válido.");
                return;
            }

            string name = txtUsuarioName.Text;
            bool found = false;

            
            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                row.Selected = false;
            }

            
            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                if (row.Cells["name"].Value != null && row.Cells["name"].Value.ToString() == name)
                {
                    row.Selected = true;
                    found = true;
                    dataGridView1.FirstDisplayedScrollingRowIndex = row.Index;
                    break;
                }
            }

            if (!found)
            {
                MessageBox.Show("No se encontró ningún usuario con el ID especificado.");
            }
        }




        private void btnSalirAdminOps_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
