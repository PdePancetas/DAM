using System.Windows.Forms;
using System.Drawing;

namespace BlizzardApp
{
    partial class Videojuego : UserControl
    {
        private System.ComponentModel.IContainer components = null;
        private PictureBox pictureBox;
        private Label lblTitulo;
        private Label lblPrecio_original;

        private void InitializeComponent()
        {
            this.pictureBox = new System.Windows.Forms.PictureBox();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblPrecio_original = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).BeginInit();
            this.SuspendLayout();
            // 
            // pictureBox
            // 
            this.pictureBox.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox.Location = new System.Drawing.Point(10, 10);
            this.pictureBox.Name = "pictureBox";
            this.pictureBox.Padding = new System.Windows.Forms.Padding(5);
            this.pictureBox.Size = new System.Drawing.Size(124, 110);
            this.pictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox.TabIndex = 0;
            this.pictureBox.TabStop = false;
            // 
            // lblTitulo
            // 
            this.lblTitulo.Font = new System.Drawing.Font("Arial", 10F, System.Drawing.FontStyle.Bold);
            this.lblTitulo.ForeColor = System.Drawing.Color.Black;
            this.lblTitulo.Location = new System.Drawing.Point(10, 120);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(104, 25);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // lblPrecio_original
            // 
            this.lblPrecio_original.BackColor = System.Drawing.Color.Transparent;
            this.lblPrecio_original.Font = new System.Drawing.Font("Arial", 9F, System.Drawing.FontStyle.Italic);
            this.lblPrecio_original.ForeColor = System.Drawing.Color.DarkGreen;
            this.lblPrecio_original.Location = new System.Drawing.Point(10, 145);
            this.lblPrecio_original.Name = "lblPrecio_original";
            this.lblPrecio_original.Size = new System.Drawing.Size(104, 25);
            this.lblPrecio_original.TabIndex = 2;
            this.lblPrecio_original.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // Videojuego
            // 
            this.BackColor = System.Drawing.SystemColors.InactiveCaption;
            this.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.Controls.Add(this.pictureBox);
            this.Controls.Add(this.lblTitulo);
            this.Controls.Add(this.lblPrecio_original);
            this.Name = "Videojuego";
            this.Padding = new System.Windows.Forms.Padding(5);
            this.Size = new System.Drawing.Size(122, 171);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox)).EndInit();
            this.ResumeLayout(false);

        }
    }
}
