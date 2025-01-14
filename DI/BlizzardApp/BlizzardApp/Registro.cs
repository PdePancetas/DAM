using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class Registro : Form
    {
        public Registro()
        {
            InitializeComponent();
        }

        private void Registro_Load(object sender, EventArgs e)
        {

        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnRegistro_Click(object sender, EventArgs e)
        {
            string userName = txtUserName.Text;
            string email = txtEmail.Text;
            string password = txtPassword.Text;

            if (userName == "" || email == "" || password == "")
            {
                MessageBox.Show("Por favor, rellene todos los campos", null, MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else if (Func.UserExists(userName))
            {
                MessageBox.Show("Este nombre de usuario no está disponible.", null, MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtUserName.Text = "";
            } else if (Func.EmailExists(email))
            {
                MessageBox.Show("Este email ya está en uso.", null, MessageBoxButtons.OK, MessageBoxIcon.Warning);
                txtEmail.Text = "";
            }
            else if (Func.registerUser(userName, email, password))
            {
                MessageBox.Show("Usuario registrado correctamente!", "Registro completado", MessageBoxButtons.OK, MessageBoxIcon.Information);
                txtUserName.Text = "";
                txtEmail.Text = "";
                txtPassword.Text = "";
            }
            else
                MessageBox.Show("No se pudo registrar al usuario!", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        
    }
}
