using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            string usuario = txtUsuario.Text;
            string contrasena = txtContrasena.Text;


             if (usuario == "" || contrasena == "") 
             {
                MessageBox.Show("Por favor, rellene todos los campos", null, MessageBoxButtons.OK, MessageBoxIcon.Information);
             } 
             else if (Func.UserExists(usuario) && !Func.PasswordMatches(usuario, contrasena))
             {
                
                MessageBox.Show("La contraseña introducida no es correcta", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                //Control de bloqueo de usuario si se introduce 3 veces mal la contraseña
                txtContrasena.Text = "";
             }
             else if (Func.UserExists(usuario))
             {
                if (Func.PasswordMatches(usuario, "Admin1234"))
                {
                    MessageBox.Show($"Bienvenido admin {usuario}", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    Usuario user = Func.getUser(usuario, contrasena);
                    LoggedUser.User = user;

                    Home home = new Home();
                    home.Show();
                    this.Hide();
                }
                else if (Func.PasswordMatches(usuario, contrasena))
                {
                    MessageBox.Show($"Bienvenido {usuario}", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    Usuario user = Func.getUser(usuario, contrasena);
                    LoggedUser.User = user;

                    Home home = new Home();
                    home.Show();
                    this.Hide();
                }
             }
            else
             {
                MessageBox.Show("No se encontro al usuario, puede registrarlo pulsando en 'Registrarse'", null, MessageBoxButtons.OK, MessageBoxIcon.Warning);
                btnRegister.Visible = true;
             }
        }
        
        private void btnListUsers_Click(object sender, EventArgs e)
        {
            List<Usuario> users = Func.getUsers();

            string data = "Usuarios registrados: ";
            foreach (Usuario usuario in users)
            {
                data += $"\n {usuario.Nombre} - {usuario.Email}";
            }

            MessageBox.Show(data, "Usuarios",MessageBoxButtons.OK, MessageBoxIcon.Information);
        }
        
        private void btnRegister_Click(object sender, EventArgs e)
        {
            
            RegisterForm registro = new RegisterForm();
            registro.Show();

        }

        private void LoginForm_FormClosing(object sender, FormClosingEventArgs e)
        {
            Application.Exit(); 
        }

        private void Salir_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }


        private void labelUsuario_Click(object sender, EventArgs e)
        {

        }

        
    }
}
