using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class LoginForm : Form
    {
        public static string loggedUser;



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
                MessageBox.Show("Hay campos vacíos!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Warning);
             } 
             else if (Func.UserExists(usuario) && Func.PasswordMatches(usuario, contrasena))
             {
                 MessageBox.Show("Inicio de sesión exitoso", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);

                 loggedUser = usuario;
                 this.Hide();  
                 Home home = new Home();
                 home.Show();
             }
             else if (Func.UserExists(usuario) && !Func.PasswordMatches(usuario, contrasena))
             {
                MessageBox.Show("Contraseña incorrecta", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                //Control de bloqueo de usuario si se introduce 3 veces mal la contraseña
                txtContrasena.Text = "";
             }
             else
             {
                DialogResult dr = MessageBox.Show("Usuario no encontrado, puede registrarlo pulsando en 'Registrarse'", "Error 404", MessageBoxButtons.OK, MessageBoxIcon.Warning);
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
            
            Registro registro = new Registro();
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
