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
            if (LoggedUser.User.rol.Equals("admin"))
            {
                comboBox_Perfil.Items.Add("Gestion de Usuarios");
                comboBox_Perfil.Items.Add("Gestion de Videojuegos");

            }
            comboBox_Perfil.DropDownStyle = ComboBoxStyle.DropDownList;

        }

        private void Home_Load(object sender, EventArgs e)
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
            Application.Exit();
        }


        private void comboBox_Perfil_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox_Perfil.SelectedIndex == -1)
            {

            }
            else if (comboBox_Perfil.SelectedItem.Equals("Cerrar sesión"))
            {
                this.Hide();
                LoginForm loginForm = new LoginForm();
                loginForm.Show();
            }
            else if (comboBox_Perfil.SelectedItem.Equals("Salir"))
            {
                Application.Exit();
            }
            else if (comboBox_Perfil.SelectedItem.Equals("Gestion de Usuarios"))
            {
                AdminUserOps adminUserOps = new AdminUserOps();
                adminUserOps.Show();
            }
            else if (comboBox_Perfil.SelectedItem.Equals("Gestion de Videojuegos"))
            {
                AdminCatalogOps adminCatalogOps= new AdminCatalogOps(catalogo);
                adminCatalogOps.Show();
            }
        }
    }
}
