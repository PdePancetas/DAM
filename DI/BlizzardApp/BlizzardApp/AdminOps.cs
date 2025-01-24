using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySqlConnector;

namespace BlizzardApp
{
    public partial class AdminOps : Form
    {
        public AdminOps()
        {
            InitializeComponent();
            
        }

        private void btnMostrarUsuarios_Click(object sender, EventArgs e)
        {
            string connectionString = "Server=localhost;Database=prueba_users;User ID=root;Password=1234";
            string query = "SELECT name, email, password FROM users";

            using(MySqlConnection conection = new MySqlConnection(connectionString))
            {
                MySqlCommand command = new MySqlCommand(query, conection);
                MySqlDataAdapter adapter = new MySqlDataAdapter(command);
                DataTable dt = new DataTable();
                adapter.Fill(dt);


                dataGridView1.DataSource = dt;
            }
        }
    }
}
