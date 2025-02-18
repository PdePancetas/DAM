using MySqlConnector;
using System;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class Catalogo : UserControl
    {
        public Catalogo()
        {
            InitializeComponent();
            CargarVideojuegos();
        }

        public void RecargarCatalogo()
        {
            // Limpiar el TableLayoutPanel antes de cargar los nuevos videojuegos
            FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)this.Controls[0]; // Obtener el TableLayoutPanel
            flowLayoutPanel.Controls.Clear(); // Limpiar los controles actuales

            // Llamar a CargarVideojuegos para llenar el catalogo
            CargarVideojuegos();
        }

        private void MostrarMensajeSinVideojuegos()
        {
            Label lblMensaje = new Label();
            lblMensaje.Text = "Aún no hay videojuegos en el catálogo.";
            lblMensaje.Dock = DockStyle.Fill;
            lblMensaje.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            lblMensaje.Font = new System.Drawing.Font("Arial", 14, System.Drawing.FontStyle.Bold);

            FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)this.Controls[0];
            flowLayoutPanel.Controls.Add(lblMensaje);
        }

        private void CargarVideojuegos()
        {
            
            string query = "SELECT * FROM videojogos";

            // Conexión a la base de datos
            using (MySqlConnection connection = Func.Conectar_BD())
            {
                MySqlCommand command = new MySqlCommand(query, connection);
                connection.Open();

                using (MySqlDataReader reader = command.ExecuteReader())
                {
                    if (!reader.HasRows)
                    {
                        MostrarMensajeSinVideojuegos(); // Si no hay videojuegos, mostrar mensaje
                        return;
                    }

                    while (reader.Read())
                    {
                        string titulo = reader["titulo"].ToString();
                        decimal precio_original = Convert.ToDecimal(reader["precio_original"]);
                        byte[] imagenBytes = reader["img_src"] as byte[];

                        // Crear un nuevo VideojuegoUserControl y agregarlo al TableLayoutPanel
                        AgregarVideojuego(titulo, precio_original, imagenBytes);
                    }
                }
            }
        }

        private void AgregarVideojuego(string titulo, decimal precio_original, byte[] imagenBytes)
        {
            // Crear una nueva instancia del VideojuegoUserControl
            VideojuegoUserControl videojuegoControl = new VideojuegoUserControl(titulo, precio_original, imagenBytes);

            // Obtener el TableLayoutPanel
            FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)this.Controls[0]; // Suponiendo que es el primer control

            // Configurar el 

            // Añadir el VideojuegoUserControl al TableLayoutPanel
            flowLayoutPanel.Controls.Add(videojuegoControl);
            
        }
    }
}
