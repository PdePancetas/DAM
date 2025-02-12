
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class Videojuego : UserControl
    {
        public Videojuego(string titulo, decimal precio_original, byte[] imagenBytes)
        {
            InitializeComponent();
            SetVideojuego(titulo, precio_original, imagenBytes);
        }

        private void SetVideojuego(string titulo, decimal precio_original, byte[] imagenBytes)
        {
            // Establecer la imagen si existe
            if (imagenBytes != null)
            {
                using (MemoryStream ms = new MemoryStream(imagenBytes))
                {
                    pictureBox.Image = Image.FromStream(ms);
                }
            }
            pictureBox.SizeMode = PictureBoxSizeMode.Zoom;
            pictureBox.Width = 100;  // Establecer tamaño de la imagen
            pictureBox.Height = 100;

            // Establecer el título y la valoración
            lblTitulo.Text = titulo;
            lblPrecio_original.Text = $"Precio: {precio_original}";
        }
    }
}
