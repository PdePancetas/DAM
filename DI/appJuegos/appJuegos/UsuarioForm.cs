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
    public partial class UsuarioForm : Form
    {

        public UsuarioForm(string username)
        {
            InitializeComponent();
            lblWelcome.Text = $"¡Bienvenido, {username}!";
        }

        private void btnLogout_Click(object sender, EventArgs e)
        {
            LoginForm loginForm = new LoginForm();
            loginForm.Show();
            this.Close();
        }

        private void btnExit_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void UsuarioForm_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

            // Buscar y cerrar el formulario de login
            foreach (Form form in Application.OpenForms)
            {
                if (form is LoginForm)
                {
                    form.Close(); // Cerrar el formulario de login si está abierto
                }
            }

            // Crear y mostrar el formulario de login nuevamente
            LoginForm loginForm = new LoginForm();
            loginForm.Show();

            // Cerrar el formulario de bienvenida
            this.Close();


        }

        private void UsuarioForm_Load_1(object sender, EventArgs e)
        {

        }
    }
}
