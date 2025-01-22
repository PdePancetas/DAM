using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace appJuegos
{
    public partial class RegisterForm : Form
    {
        public RegisterForm()
        {
            InitializeComponent();
        }

        private void btnRegister_Click(object sender, EventArgs e)
        {
            string nombre = txtNombre.Text.Trim();
            string password = txtPassword.Text.Trim();
            string confirmPassword = txtConfirmPassword.Text.Trim();

            // Validar campos no vacíos
            if (string.IsNullOrEmpty(nombre) || string.IsNullOrEmpty(password) || string.IsNullOrEmpty(confirmPassword))
            {
                lblMessage.Text = "Por favor, complete todos los campos.";
                return;
            }

            // Validar que las contraseñas coincidan
            if (password != confirmPassword)
            {
                lblMessage.Text = "Las contraseñas no coinciden.";
                return;
            }

            // Lógica para registrar el usuario en la base de datos (ejemplo):
            bool registroExitoso = RegistrarUsuario(nombre, password);

            if (registroExitoso)
            {
                lblMessage.ForeColor = System.Drawing.Color.Green;
                lblMessage.Text = "Usuario registrado exitosamente.";
            }
            else
            {
                lblMessage.Text = "Error al registrar el usuario.";
            }
        }

        private bool RegistrarUsuario(string nombre, string password)
        {
            string connectionString = System.Configuration.ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;

            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    string query = "INSERT INTO usuarios (nombre, pw) VALUES (@nombre, @pw)";
                    using (var command = new MySqlCommand(query, connection))
                    {
                        command.Parameters.AddWithValue("@nombre", nombre);
                        command.Parameters.AddWithValue("@pw", password);
                        command.ExecuteNonQuery();
                    }
                }

                return true;
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al registrar usuario: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }
        }




        private void RegisterForm_Load(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void btnVolverLogin_Click(object sender, EventArgs e)
        {
            LoginForm loginForm = new LoginForm();
            loginForm.Show();
            this.Hide();
        }

        private void RegisterForm_Load_1(object sender, EventArgs e)
        {

        }
    }
}
