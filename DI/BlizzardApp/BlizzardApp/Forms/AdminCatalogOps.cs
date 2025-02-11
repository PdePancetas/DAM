using System;
using MySqlConnector;
using System.IO;
using System.Windows.Forms;

namespace BlizzardApp
{

    public partial class AdminCatalogOps : Form
    {
        private Catalogo catalogoControl;
        private string imgPath;
        private string juegoSeleccionado;


        public AdminCatalogOps(Catalogo catalogoControl)
        {
            InitializeComponent();
            CargarJuegosLista();
            this.catalogoControl = catalogoControl;
        }

        private void CargarJuegosLista()
        {
            lstJuegos.Items.Clear();
            string query = "SELECT titulo FROM videojogos";
            using (MySqlConnection conexion = Func.Conectar_BD())
            {
                conexion.Open();

                MySqlCommand command = new MySqlCommand(query, conexion);


                using (MySqlDataReader reader = command.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        string titulo = reader["titulo"].ToString();
                        lstJuegos.Items.Add(titulo);

                        // Crear un nuevo VideojuegoUserControl y agregarlo al TableLayoutPanel
                        
                    }
                }

            }
        }

        private void btnInsertarVideojuego_Click(object sender, EventArgs e)
        {
            // Aquí se agregan los videojuegos a la base de datos, usando un formulario para obtener los datos
            // Ejemplo de código de inserción de videojuego en la base de datos (ajustar según tu modelo)

            
            string query = "INSERT INTO videojogos (titulo, precio_original, img_src) VALUES (@titulo, @precioOriginal, @img_src)";

            using (MySqlConnection connection = Func.Conectar_BD())
            {
                connection.Open();
                
                MySqlCommand command = new MySqlCommand(query, connection);
                command.Parameters.AddWithValue("@titulo", txtTitulo.Text);
                command.Parameters.AddWithValue("@precioOriginal", Double.Parse(txtPrecioInicial.Text));
                command.Parameters.AddWithValue("@img_src", File.ReadAllBytes(pBoxImgPreview.ImageLocation)); // imageBytes es el byte[] de la imagen*/

                command.ExecuteNonQuery();
            }

            
            // Notificar al CatalogoUserControl para recargar el catálogo y mostrar el nuevo videojuego
            CargarJuegosLista();
            catalogoControl.RecargarCatalogo();
        }

        private void btnSalir_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnSelecImg_Click(object sender, EventArgs e)
        {
            // Crear una instancia del OpenFileDialog
            OpenFileDialog openFileDialog = new OpenFileDialog();

            // Establecer el filtro para seleccionar solo imágenes
            openFileDialog.Filter = "Archivos de Imagen|*.jpg;*.jpeg;*.png;*.bmp;*.gif|Todos los Archivos|*.*";
            openFileDialog.Title = "Selecciona una imagen";

            // Mostrar el diálogo y verificar si el usuario seleccionó un archivo
            if (openFileDialog.ShowDialog() == DialogResult.OK)
            {
                // Obtener la ruta del archivo seleccionado
                imgPath = openFileDialog.FileName;

                // Mostrar la imagen en un PictureBox
                pBoxImgPreview.ImageLocation = imgPath;
                pBoxImgPreview.SizeMode = PictureBoxSizeMode.Zoom; // Para ajustar la imagen al tamaño del PictureBox
            }
        }

        private void lstJuegos_SelectedIndexChanged(object sender, EventArgs e)
        {
            juegoSeleccionado = lstJuegos.SelectedItem.ToString();
        }

        private void btnDeleteVideojuego_Click(object sender, EventArgs e)
        {
            
            if (lstJuegos.SelectedItem != null)
            {
                string juegoSeleccionado = lstJuegos.SelectedItem.ToString();

                using (MySqlConnection conexion = Func.Conectar_BD())
                {
                    conexion.Open();
                    string query = "DELETE FROM videojogos WHERE titulo = @nombre";
                    MySqlCommand cmd = new MySqlCommand(query, conexion);
                    cmd.Parameters.AddWithValue("@nombre", juegoSeleccionado);
                    int filasAfectadas = cmd.ExecuteNonQuery();

                    if (filasAfectadas > 0)
                    {
                        MessageBox.Show("Juego eliminado correctamente.", "Éxito", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        CargarJuegosLista(); // Recargar la lista
                        catalogoControl.RecargarCatalogo();
                    }
                    else
                    {
                        MessageBox.Show("No se pudo eliminar el juego.", "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    }
                }
            }
            else
            {
                MessageBox.Show("Selecciona un juego antes de eliminar.", "Aviso", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
            
        }

        private void txtBuscarJuego_TextChanged(object sender, EventArgs e)
        {
            for (int i = 0; i < lstJuegos.Items.Count; i++)
            {
                if (lstJuegos.Items[i].ToString().Contains(txtBuscarJuego.Text))
                    lstJuegos.SelectedItems.Add(lstJuegos.Items[i]);
            }
        }


        private void lstJuegos_DoubleClick(object sender, EventArgs e)
        {
            if (lstJuegos.SelectedItem != null)
            {
                string juegoSeleccionado = lstJuegos.SelectedItem.ToString();
                VideojuegoPropertiesForm  formDetalles = new VideojuegoPropertiesForm(juegoSeleccionado);
                formDetalles.ShowDialog();
            }
        }


    }

}


