using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.StartPanel;

namespace appJuegos
{
    public partial class App : Form
    {
        private string username;

        public App(string username)
        {
            InitializeComponent();
            this.username = username;
        }

     
        private void contenedor_vjuegos_Paint(object sender, PaintEventArgs e)
        {

        }


        private void pictureBox1_Click(object sender, EventArgs e)
        {

        }



        private void label1_Click(object sender, EventArgs e)
        {

        }


        private void App_Load(object sender, EventArgs e)
        {
            // Agregar el nombre del usuario al texto existente
           label_usuario.Text = label_usuario.Text + $" {username}!";

            //Ajustar la posición del Label en la esquina superior derecha
           label_usuario.AutoSize = true; // Ajustar tamaño al texto
          // label_usuario.Location = new Point(this.ClientSize.Width - label_usuario.Width - 10, 10);

        }

        private void label1_Click_1(object sender, EventArgs e)
        {

        }

        private void btn_salir_click(object sender, EventArgs e)
        {
            // Crear una nueva instancia del formulario de login
            LoginForm loginForm = new LoginForm();

            // Mostrar el formulario de login
            loginForm.Show();

            // Cerrar el formulario de bienvenida
            this.Close();

        }
    }
}
