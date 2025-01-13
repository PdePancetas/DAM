using System;
using System.Diagnostics;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class Home : Form
    {
        public Home()
        {
           
            InitializeComponent();
            comboBox_Perfil.DropDownStyle = ComboBoxStyle.DropDownList;
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void btnTienda_Click(object sender, EventArgs e)
        {
            
        }

        private void btnCommunity_Click(object sender, EventArgs e)
        {
            
        }

        private void btnSupport_Click(object sender, EventArgs e)
        {
            
        }

        private void Home_FormClosing(object sender, FormClosingEventArgs e)
        {
            
        }


        private void comboBox_Perfil_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox_Perfil.SelectedItem.Equals("Cerrar sesión"))
            {
                this.Hide();
                LoginForm loginForm = new LoginForm();
                loginForm.Show();
            }
            else if (comboBox_Perfil.SelectedItem.Equals("Salir"))
            {
                Application.Exit();
            }
        }
    }
}
