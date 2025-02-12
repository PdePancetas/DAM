namespace BlizzardApp
{
    partial class AdminCatalogOps
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.btnInsertarVideojuego = new System.Windows.Forms.Button();
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblDescripcion = new System.Windows.Forms.Label();
            this.lblPrecioInicial = new System.Windows.Forms.Label();
            this.pBoxImgPreview = new System.Windows.Forms.PictureBox();
            this.btnSelecImg = new System.Windows.Forms.Button();
            this.btnSalir = new System.Windows.Forms.Button();
            this.txtTitulo = new System.Windows.Forms.TextBox();
            this.txtDescripcion = new System.Windows.Forms.TextBox();
            this.txtPrecioInicial = new System.Windows.Forms.TextBox();
            this.btnDeleteVideojuego = new System.Windows.Forms.Button();
            this.lstJuegos = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.lblBuscarJuego = new System.Windows.Forms.Label();
            this.txtBuscarJuego = new System.Windows.Forms.TextBox();
            ((System.ComponentModel.ISupportInitialize)(this.pBoxImgPreview)).BeginInit();
            this.SuspendLayout();
            // 
            // btnInsertarVideojuego
            // 
            this.btnInsertarVideojuego.Location = new System.Drawing.Point(332, 178);
            this.btnInsertarVideojuego.Name = "btnInsertarVideojuego";
            this.btnInsertarVideojuego.Size = new System.Drawing.Size(133, 42);
            this.btnInsertarVideojuego.TabIndex = 0;
            this.btnInsertarVideojuego.Text = "Insertar Videojuego";
            this.btnInsertarVideojuego.UseVisualStyleBackColor = true;
            this.btnInsertarVideojuego.Click += new System.EventHandler(this.btnInsertarVideojuego_Click);
            // 
            // lblTitulo
            // 
            this.lblTitulo.AutoSize = true;
            this.lblTitulo.Location = new System.Drawing.Point(12, 27);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(41, 13);
            this.lblTitulo.TabIndex = 1;
            this.lblTitulo.Text = "Título: ";
            // 
            // lblDescripcion
            // 
            this.lblDescripcion.AutoSize = true;
            this.lblDescripcion.Location = new System.Drawing.Point(15, 105);
            this.lblDescripcion.Name = "lblDescripcion";
            this.lblDescripcion.Size = new System.Drawing.Size(66, 13);
            this.lblDescripcion.TabIndex = 2;
            this.lblDescripcion.Text = "Descripción:";
            // 
            // lblPrecioInicial
            // 
            this.lblPrecioInicial.AutoSize = true;
            this.lblPrecioInicial.Location = new System.Drawing.Point(12, 57);
            this.lblPrecioInicial.Name = "lblPrecioInicial";
            this.lblPrecioInicial.Size = new System.Drawing.Size(69, 13);
            this.lblPrecioInicial.TabIndex = 3;
            this.lblPrecioInicial.Text = "Precio inicial:";
            // 
            // pBoxImgPreview
            // 
            this.pBoxImgPreview.Location = new System.Drawing.Point(332, 20);
            this.pBoxImgPreview.Name = "pBoxImgPreview";
            this.pBoxImgPreview.Size = new System.Drawing.Size(133, 114);
            this.pBoxImgPreview.TabIndex = 4;
            this.pBoxImgPreview.TabStop = false;
            // 
            // btnSelecImg
            // 
            this.btnSelecImg.Location = new System.Drawing.Point(332, 140);
            this.btnSelecImg.Name = "btnSelecImg";
            this.btnSelecImg.Size = new System.Drawing.Size(133, 23);
            this.btnSelecImg.TabIndex = 5;
            this.btnSelecImg.Text = "Seleccione la imagen";
            this.btnSelecImg.UseVisualStyleBackColor = true;
            this.btnSelecImg.Click += new System.EventHandler(this.btnSelecImg_Click);
            // 
            // btnSalir
            // 
            this.btnSalir.Location = new System.Drawing.Point(68, 356);
            this.btnSalir.Name = "btnSalir";
            this.btnSalir.Size = new System.Drawing.Size(104, 42);
            this.btnSalir.TabIndex = 6;
            this.btnSalir.Text = "Salir";
            this.btnSalir.UseVisualStyleBackColor = true;
            this.btnSalir.Click += new System.EventHandler(this.btnSalir_Click);
            // 
            // txtTitulo
            // 
            this.txtTitulo.Location = new System.Drawing.Point(118, 20);
            this.txtTitulo.Name = "txtTitulo";
            this.txtTitulo.Size = new System.Drawing.Size(195, 20);
            this.txtTitulo.TabIndex = 7;
            // 
            // txtDescripcion
            // 
            this.txtDescripcion.Location = new System.Drawing.Point(118, 102);
            this.txtDescripcion.Multiline = true;
            this.txtDescripcion.Name = "txtDescripcion";
            this.txtDescripcion.Size = new System.Drawing.Size(195, 118);
            this.txtDescripcion.TabIndex = 8;
            // 
            // txtPrecioInicial
            // 
            this.txtPrecioInicial.Location = new System.Drawing.Point(118, 54);
            this.txtPrecioInicial.Name = "txtPrecioInicial";
            this.txtPrecioInicial.Size = new System.Drawing.Size(195, 20);
            this.txtPrecioInicial.TabIndex = 9;
            // 
            // btnDeleteVideojuego
            // 
            this.btnDeleteVideojuego.Location = new System.Drawing.Point(584, 303);
            this.btnDeleteVideojuego.Name = "btnDeleteVideojuego";
            this.btnDeleteVideojuego.Size = new System.Drawing.Size(182, 48);
            this.btnDeleteVideojuego.TabIndex = 10;
            this.btnDeleteVideojuego.Text = "Eliminar videojuego";
            this.btnDeleteVideojuego.UseVisualStyleBackColor = true;
            this.btnDeleteVideojuego.Click += new System.EventHandler(this.btnDeleteVideojuego_Click);
            // 
            // lstJuegos
            // 
            this.lstJuegos.FormattingEnabled = true;
            this.lstJuegos.Location = new System.Drawing.Point(563, 59);
            this.lstJuegos.Name = "lstJuegos";
            this.lstJuegos.Size = new System.Drawing.Size(225, 225);
            this.lstJuegos.TabIndex = 11;
            this.lstJuegos.SelectedIndexChanged += new System.EventHandler(this.lstJuegos_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(560, 287);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(228, 13);
            this.label1.TabIndex = 12;
            this.label1.Text = "Seleccione un juego de la lista y pulse el botón";
            // 
            // lblBuscarJuego
            // 
            this.lblBuscarJuego.AutoSize = true;
            this.lblBuscarJuego.Location = new System.Drawing.Point(581, 9);
            this.lblBuscarJuego.Name = "lblBuscarJuego";
            this.lblBuscarJuego.Size = new System.Drawing.Size(184, 13);
            this.lblBuscarJuego.TabIndex = 13;
            this.lblBuscarJuego.Text = "Escribe el nombre del juego a buscar:";
            // 
            // txtBuscarJuego
            // 
            this.txtBuscarJuego.Location = new System.Drawing.Point(584, 33);
            this.txtBuscarJuego.Name = "txtBuscarJuego";
            this.txtBuscarJuego.Size = new System.Drawing.Size(178, 20);
            this.txtBuscarJuego.TabIndex = 14;
            this.txtBuscarJuego.TextChanged += new System.EventHandler(this.txtBuscarJuego_TextChanged);
            // 
            // AdminCatalogOps
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.txtBuscarJuego);
            this.Controls.Add(this.lblBuscarJuego);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lstJuegos);
            this.Controls.Add(this.btnDeleteVideojuego);
            this.Controls.Add(this.txtPrecioInicial);
            this.Controls.Add(this.txtDescripcion);
            this.Controls.Add(this.txtTitulo);
            this.Controls.Add(this.btnSalir);
            this.Controls.Add(this.btnSelecImg);
            this.Controls.Add(this.pBoxImgPreview);
            this.Controls.Add(this.lblPrecioInicial);
            this.Controls.Add(this.lblDescripcion);
            this.Controls.Add(this.lblTitulo);
            this.Controls.Add(this.btnInsertarVideojuego);
            this.Name = "AdminCatalogOps";
            this.Text = "Gestionar Videojuegos";
            ((System.ComponentModel.ISupportInitialize)(this.pBoxImgPreview)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnInsertarVideojuego;
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblDescripcion;
        private System.Windows.Forms.Label lblPrecioInicial;
        private System.Windows.Forms.PictureBox pBoxImgPreview;
        private System.Windows.Forms.Button btnSelecImg;
        private System.Windows.Forms.Button btnSalir;
        private System.Windows.Forms.TextBox txtTitulo;
        private System.Windows.Forms.TextBox txtDescripcion;
        private System.Windows.Forms.TextBox txtPrecioInicial;
        private System.Windows.Forms.Button btnDeleteVideojuego;
        private System.Windows.Forms.ListBox lstJuegos;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label lblBuscarJuego;
        private System.Windows.Forms.TextBox txtBuscarJuego;
    }
}