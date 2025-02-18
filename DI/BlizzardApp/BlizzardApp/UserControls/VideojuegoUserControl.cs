
using System.Drawing;
using System.IO;
using System.Windows.Forms;

namespace BlizzardApp
{
    public partial class VideojuegoUserControl : UserControl
    {
        string titulo;

        public VideojuegoUserControl(string titulo, decimal precio_original, byte[] imagenBytes)
        {
            InitializeComponent();
            this.titulo = titulo;
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

        private void Videojuego_DoubleClick(object sender, System.EventArgs e)
        {
            VideojuegoPropertiesForm juego = new VideojuegoPropertiesForm(titulo);
            juego.Show();
        }
    }
}
