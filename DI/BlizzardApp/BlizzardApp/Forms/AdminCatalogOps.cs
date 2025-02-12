using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using MySqlConnector;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace BlizzardApp
{

    public partial class AdminCatalogOps : Form
    {
        private Catalogo catalogoControl;
        private string imgPath;

        public AdminCatalogOps(Catalogo catalogoControl)
        {
            InitializeComponent();
            this.catalogoControl = catalogoControl;
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
    }
}


