using Microsoft.Extensions.Logging.Abstractions;
using MySqlConnector;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BlizzardApp.Forms
{
    public partial class CestaForm : Form
    {
       

        public CestaForm()
        {
            InitializeComponent();
            RecargarCesta();
        }

        private void CestaForm_Load(object sender, EventArgs e)
        {

        }

        public void AddJuego(string juego)
        {
            Videojuego vjuego = Func.getJuego(juego) as Videojuego;
            Console.WriteLine(vjuego.Id);
            Usuario user = Func.getUser(LoggedUser.User.Nombre, LoggedUser.User.Password);

            AnadirACesta(user, vjuego);
            RecargarCesta();
        }


        private void AnadirACesta(Usuario user, Videojuego juego)
        {
            using (MySqlConnection connection = Func.Conectar_BD())
            {
                // Sentencia SQL para insertar en la tabla relacional
                
                string checkQuery = "SELECT COUNT(*) FROM videojogos WHERE id = @idJogo";
                using (MySqlCommand checkCmd = new MySqlCommand(checkQuery, connection))
                {
                    connection.Open();
                    checkCmd.Parameters.AddWithValue("@idJogo", juego.Id);
                    int count = Convert.ToInt32(checkCmd.ExecuteScalar());
                    if (count == 0)
                    {
                        Console.WriteLine("El juego con ID " + juego.Id + " no existe en la base de datos.");
                        return;
                    }
                }
            }
            string sql = "INSERT INTO cestajogos_usuario (idUsuario, idJogo) VALUES (@idUsuario, @idJogo)";

            using (MySqlConnection connection2 = Func.Conectar_BD())
            {
                using (MySqlCommand cmd = new MySqlCommand(sql, connection2))
                {
                    cmd.Parameters.AddWithValue("@idUsuario", LoggedUser.User.Id);
                    cmd.Parameters.AddWithValue("@idJogo", juego.Id);

                    try
                    {
                        connection2.Open();
                        int filasAfectadas = cmd.ExecuteNonQuery();
                        
                    }
                    catch (Exception ex)
                    {
                        // Manejo de errores (puedes personalizarlo)
                        Console.WriteLine($"Error al asociar juego con usuario: {ex.Message}");
                        
                    }
                }
            }

            RecargarCesta();
        }

        public void RecargarCesta()
        {
            // Limpiar el TableLayoutPanel antes de cargar los nuevos videojuegos
            try
            {
                FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)catalogo.Controls[0]; // Obtener el TableLayoutPanel
                flowLayoutPanel.Controls.Clear();
            }
            catch(Exception e)
            {
                Console.WriteLine(e.Message);
            }
                 // Limpiar los controles actuales

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

            FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)catalogo.Controls[0];
            flowLayoutPanel.Controls.Add(lblMensaje);
        }

        private void CargarVideojuegos()
        {

            string query = @"
            SELECT v.*
            FROM cestajogos_usuario cesta
            JOIN videojogos v ON cesta.idJogo = v.id
            WHERE cesta.idUsuario = @idUsuario";
            // Conexión a la base de datos
            using (MySqlConnection connection = Func.Conectar_BD())
            {
                MySqlCommand command = new MySqlCommand(query, connection);
                command.Parameters.AddWithValue("@idUsuario", LoggedUser.User.Id);
                
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
            FlowLayoutPanel flowLayoutPanel = (FlowLayoutPanel)catalogo.Controls[0]; // Suponiendo que es el primer control

            // Configurar el 

            // Añadir el VideojuegoUserControl al TableLayoutPanel
            flowLayoutPanel.Controls.Add(videojuegoControl);

        }
    }
}
