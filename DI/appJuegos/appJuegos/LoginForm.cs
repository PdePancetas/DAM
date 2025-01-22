using System;
using System.Drawing;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace appJuegos
{
    public partial class LoginForm : Form
    {
 
        private int intentosFallidos = 0;
        private const int maxIntentos = 3;

        public LoginForm()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string username = txtUsername.Text.Trim();
            string password = txtPassword.Text.Trim();

            // Validar que los campos no estén vacíos
            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
            {
                lblMessage.Text = "Por favor, complete todos los campos.";
                lblMessage.BringToFront();
                return;
            }

            // Validar las credenciales con la base de datos
            bool usuarioValido = ValidarUsuario(username, password);

            if (usuarioValido)
            {
                
                intentosFallidos = 0;

                App homeForm = new App(username);
                homeForm.Show();
                this.Hide();
            }
            else
            {

                intentosFallidos++;

                if (intentosFallidos >= maxIntentos)
                {
                    lblMessage.Text = "Demasiados intentos fallidos. Intente más tarde.";
                    btnLogin.Enabled = false; // Deshabilitar el botón de login
                }
                else
                {
                    // Mostrar el mensaje de error desde `ValidarUsuario`
                    lblMessage.BringToFront();
                }
            }
        }

        private void btnRegister_Click(object sender, EventArgs e)
        {
            RegisterForm registerForm = new RegisterForm();
            registerForm.Show();
            this.Hide(); 
        }


        private bool ValidarUsuario(string username, string password)
        {
            // Obtener la cadena de conexión desde App.config
            string connectionString = System.Configuration.ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString;

            try
            {
                using (var connection = new MySqlConnection(connectionString))
                {
                    connection.Open();

                    // Consulta para verificar si el usuario existe
                    string queryUsuarioExiste = "SELECT COUNT(*) FROM usuarios WHERE nombre = @nombre";
                    using (var commandUsuarioExiste = new MySqlCommand(queryUsuarioExiste, connection))
                    {
                        commandUsuarioExiste.Parameters.AddWithValue("@nombre", username);
                        int usuarioExiste = Convert.ToInt32(commandUsuarioExiste.ExecuteScalar());

                        if (usuarioExiste == 0)
                        {
                            lblMessage.Text = "El usuario no existe.";
                            btnRegistro.Visible = true; // Mostrar el botón de registro si el usuario no existe
                            return false;
                        }
                    }

                    // Consulta para verificar si la contraseña es correcta
                    string queryCredencialesValidas = "SELECT COUNT(*) FROM usuarios WHERE nombre = @nombre AND pw = @pw";
                    using (var commandCredencialesValidas = new MySqlCommand(queryCredencialesValidas, connection))
                    {
                        commandCredencialesValidas.Parameters.AddWithValue("@nombre", username);
                        commandCredencialesValidas.Parameters.AddWithValue("@pw", password);
                        int credencialesValidas = Convert.ToInt32(commandCredencialesValidas.ExecuteScalar());

                        if (credencialesValidas == 0)
                        {
                            // Mostrar mensaje si la contraseña es incorrecta
                            lblMessage.Text = "La contraseña es incorrecta.";
                            return false;
                        }
                    }

                    // Si pasa ambas verificaciones, el usuario y la contraseña son válidos
                    return true;
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Error al conectar con la base de datos: {ex.Message}", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }
        }


        private void FormLogin_Load(object sender, EventArgs e)
        {
           
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }

        private void Salir_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }
    }
}
