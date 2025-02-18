namespace BlizzardApp
{
    partial class VideojuegoPropertiesForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;
        private string juegoSeleccionado;

        // Labels
        private System.Windows.Forms.Label lblTitulo;
        private System.Windows.Forms.Label lblDescripcion;
        private System.Windows.Forms.Label lblGenero;
        private System.Windows.Forms.Label lblDesarrollador;
        private System.Windows.Forms.Label lblEditor;
        private System.Windows.Forms.Label lblPrecio;
        private System.Windows.Forms.Label lblPrecioOriginal;
        private System.Windows.Forms.Label lblValoracion;
        private System.Windows.Forms.Label lblSoporteVR;
        private System.Windows.Forms.Label lblTamaño;
        private System.Windows.Forms.Label lblEstado;
        private System.Windows.Forms.Label lblImagen;

        // TextBox para texto editable
        private System.Windows.Forms.TextBox txtTitulo;
        private System.Windows.Forms.TextBox txtDescripcion;
        private System.Windows.Forms.TextBox txtGenero;
        private System.Windows.Forms.TextBox txtDesarrollador;
        private System.Windows.Forms.TextBox txtEditor;
        private System.Windows.Forms.TextBox txtPrecio;
        private System.Windows.Forms.TextBox txtPrecioOriginal;
        private System.Windows.Forms.TextBox txtValoracion;
        private System.Windows.Forms.TextBox txtTamaño;

        // CheckBox para soporte VR
        private System.Windows.Forms.CheckBox chkSoporteVR;

        // ComboBox para estado del juego
        private System.Windows.Forms.ComboBox cmbEstado;

        // PictureBox para la imagen del juego
        private System.Windows.Forms.PictureBox pbImagen;

        // Botones de acción
        private System.Windows.Forms.Button btnEditar;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.Button btnCancelar;


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
            this.lblTitulo = new System.Windows.Forms.Label();
            this.lblDescripcion = new System.Windows.Forms.Label();
            this.lblGenero = new System.Windows.Forms.Label();
            this.lblDesarrollador = new System.Windows.Forms.Label();
            this.lblEditor = new System.Windows.Forms.Label();
            this.lblPrecio = new System.Windows.Forms.Label();
            this.lblPrecioOriginal = new System.Windows.Forms.Label();
            this.lblValoracion = new System.Windows.Forms.Label();
            this.lblSoporteVR = new System.Windows.Forms.Label();
            this.lblTamaño = new System.Windows.Forms.Label();
            this.lblEstado = new System.Windows.Forms.Label();
            this.lblImagen = new System.Windows.Forms.Label();
            this.txtTitulo = new System.Windows.Forms.TextBox();
            this.txtDescripcion = new System.Windows.Forms.TextBox();
            this.txtGenero = new System.Windows.Forms.TextBox();
            this.txtDesarrollador = new System.Windows.Forms.TextBox();
            this.txtEditor = new System.Windows.Forms.TextBox();
            this.txtPrecio = new System.Windows.Forms.TextBox();
            this.txtPrecioOriginal = new System.Windows.Forms.TextBox();
            this.txtValoracion = new System.Windows.Forms.TextBox();
            this.chkSoporteVR = new System.Windows.Forms.CheckBox();
            this.txtTamaño = new System.Windows.Forms.TextBox();
            this.cmbEstado = new System.Windows.Forms.ComboBox();
            this.pbImagen = new System.Windows.Forms.PictureBox();
            this.btnEditar = new System.Windows.Forms.Button();
            this.btnGuardar = new System.Windows.Forms.Button();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.btnAddCesta = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.pbImagen)).BeginInit();
            this.SuspendLayout();
            // 
            // lblTitulo
            // 
            this.lblTitulo.Location = new System.Drawing.Point(20, 20);
            this.lblTitulo.Name = "lblTitulo";
            this.lblTitulo.Size = new System.Drawing.Size(77, 23);
            this.lblTitulo.TabIndex = 0;
            this.lblTitulo.Text = "Título:";
            // 
            // lblDescripcion
            // 
            this.lblDescripcion.Location = new System.Drawing.Point(20, 50);
            this.lblDescripcion.Name = "lblDescripcion";
            this.lblDescripcion.Size = new System.Drawing.Size(77, 23);
            this.lblDescripcion.TabIndex = 2;
            this.lblDescripcion.Text = "Descripción:";
            // 
            // lblGenero
            // 
            this.lblGenero.Location = new System.Drawing.Point(20, 140);
            this.lblGenero.Name = "lblGenero";
            this.lblGenero.Size = new System.Drawing.Size(77, 23);
            this.lblGenero.TabIndex = 4;
            this.lblGenero.Text = "Género:";
            // 
            // lblDesarrollador
            // 
            this.lblDesarrollador.Location = new System.Drawing.Point(20, 170);
            this.lblDesarrollador.Name = "lblDesarrollador";
            this.lblDesarrollador.Size = new System.Drawing.Size(77, 23);
            this.lblDesarrollador.TabIndex = 6;
            this.lblDesarrollador.Text = "Desarrollador:";
            // 
            // lblEditor
            // 
            this.lblEditor.Location = new System.Drawing.Point(20, 200);
            this.lblEditor.Name = "lblEditor";
            this.lblEditor.Size = new System.Drawing.Size(77, 23);
            this.lblEditor.TabIndex = 8;
            this.lblEditor.Text = "Editor:";
            // 
            // lblPrecio
            // 
            this.lblPrecio.Location = new System.Drawing.Point(20, 230);
            this.lblPrecio.Name = "lblPrecio";
            this.lblPrecio.Size = new System.Drawing.Size(77, 23);
            this.lblPrecio.TabIndex = 10;
            this.lblPrecio.Text = "Precio:";
            // 
            // lblPrecioOriginal
            // 
            this.lblPrecioOriginal.Location = new System.Drawing.Point(197, 227);
            this.lblPrecioOriginal.Name = "lblPrecioOriginal";
            this.lblPrecioOriginal.Size = new System.Drawing.Size(85, 23);
            this.lblPrecioOriginal.TabIndex = 12;
            this.lblPrecioOriginal.Text = "Precio Original:";
            // 
            // lblValoracion
            // 
            this.lblValoracion.Location = new System.Drawing.Point(360, 227);
            this.lblValoracion.Name = "lblValoracion";
            this.lblValoracion.Size = new System.Drawing.Size(63, 23);
            this.lblValoracion.TabIndex = 14;
            this.lblValoracion.Text = "Valoración:";
            // 
            // lblSoporteVR
            // 
            this.lblSoporteVR.Location = new System.Drawing.Point(20, 260);
            this.lblSoporteVR.Name = "lblSoporteVR";
            this.lblSoporteVR.Size = new System.Drawing.Size(77, 23);
            this.lblSoporteVR.TabIndex = 16;
            this.lblSoporteVR.Text = "Soporte VR:";
            // 
            // lblTamaño
            // 
            this.lblTamaño.Location = new System.Drawing.Point(20, 290);
            this.lblTamaño.Name = "lblTamaño";
            this.lblTamaño.Size = new System.Drawing.Size(77, 23);
            this.lblTamaño.TabIndex = 18;
            this.lblTamaño.Text = "Tamaño:";
            // 
            // lblEstado
            // 
            this.lblEstado.Location = new System.Drawing.Point(192, 290);
            this.lblEstado.Name = "lblEstado";
            this.lblEstado.Size = new System.Drawing.Size(48, 23);
            this.lblEstado.TabIndex = 20;
            this.lblEstado.Text = "Estado:";
            // 
            // lblImagen
            // 
            this.lblImagen.Location = new System.Drawing.Point(575, 23);
            this.lblImagen.Name = "lblImagen";
            this.lblImagen.Size = new System.Drawing.Size(100, 23);
            this.lblImagen.TabIndex = 22;
            this.lblImagen.Text = "Imagen:";
            // 
            // txtTitulo
            // 
            this.txtTitulo.Location = new System.Drawing.Point(126, 17);
            this.txtTitulo.Name = "txtTitulo";
            this.txtTitulo.ReadOnly = true;
            this.txtTitulo.Size = new System.Drawing.Size(220, 20);
            this.txtTitulo.TabIndex = 1;
            // 
            // txtDescripcion
            // 
            this.txtDescripcion.Location = new System.Drawing.Point(126, 47);
            this.txtDescripcion.Multiline = true;
            this.txtDescripcion.Name = "txtDescripcion";
            this.txtDescripcion.Size = new System.Drawing.Size(220, 80);
            this.txtDescripcion.TabIndex = 3;
            // 
            // txtGenero
            // 
            this.txtGenero.Location = new System.Drawing.Point(126, 137);
            this.txtGenero.Name = "txtGenero";
            this.txtGenero.Size = new System.Drawing.Size(220, 20);
            this.txtGenero.TabIndex = 5;
            // 
            // txtDesarrollador
            // 
            this.txtDesarrollador.Location = new System.Drawing.Point(126, 167);
            this.txtDesarrollador.Name = "txtDesarrollador";
            this.txtDesarrollador.Size = new System.Drawing.Size(220, 20);
            this.txtDesarrollador.TabIndex = 7;
            // 
            // txtEditor
            // 
            this.txtEditor.Location = new System.Drawing.Point(126, 197);
            this.txtEditor.Name = "txtEditor";
            this.txtEditor.Size = new System.Drawing.Size(220, 20);
            this.txtEditor.TabIndex = 9;
            // 
            // txtPrecio
            // 
            this.txtPrecio.Location = new System.Drawing.Point(126, 227);
            this.txtPrecio.Name = "txtPrecio";
            this.txtPrecio.Size = new System.Drawing.Size(40, 20);
            this.txtPrecio.TabIndex = 11;
            // 
            // txtPrecioOriginal
            // 
            this.txtPrecioOriginal.Location = new System.Drawing.Point(288, 227);
            this.txtPrecioOriginal.Name = "txtPrecioOriginal";
            this.txtPrecioOriginal.Size = new System.Drawing.Size(40, 20);
            this.txtPrecioOriginal.TabIndex = 13;
            // 
            // txtValoracion
            // 
            this.txtValoracion.Location = new System.Drawing.Point(441, 227);
            this.txtValoracion.Name = "txtValoracion";
            this.txtValoracion.Size = new System.Drawing.Size(40, 20);
            this.txtValoracion.TabIndex = 15;
            // 
            // chkSoporteVR
            // 
            this.chkSoporteVR.Location = new System.Drawing.Point(100, 260);
            this.chkSoporteVR.Name = "chkSoporteVR";
            this.chkSoporteVR.Size = new System.Drawing.Size(20, 20);
            this.chkSoporteVR.TabIndex = 17;
            // 
            // txtTamaño
            // 
            this.txtTamaño.Location = new System.Drawing.Point(126, 287);
            this.txtTamaño.Name = "txtTamaño";
            this.txtTamaño.Size = new System.Drawing.Size(60, 20);
            this.txtTamaño.TabIndex = 19;
            // 
            // cmbEstado
            // 
            this.cmbEstado.Items.AddRange(new object[] {
            "Disponible",
            "No disponible",
            "En revisión"});
            this.cmbEstado.Location = new System.Drawing.Point(246, 287);
            this.cmbEstado.Name = "cmbEstado";
            this.cmbEstado.Size = new System.Drawing.Size(120, 21);
            this.cmbEstado.TabIndex = 21;
            // 
            // pbImagen
            // 
            this.pbImagen.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbImagen.Location = new System.Drawing.Point(578, 53);
            this.pbImagen.Name = "pbImagen";
            this.pbImagen.Size = new System.Drawing.Size(200, 200);
            this.pbImagen.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbImagen.TabIndex = 23;
            this.pbImagen.TabStop = false;
            // 
            // btnEditar
            // 
            this.btnEditar.Location = new System.Drawing.Point(20, 330);
            this.btnEditar.Name = "btnEditar";
            this.btnEditar.Size = new System.Drawing.Size(100, 30);
            this.btnEditar.TabIndex = 24;
            this.btnEditar.Text = "Editar";
            this.btnEditar.Click += new System.EventHandler(this.btnEditar_Click);
            // 
            // btnGuardar
            // 
            this.btnGuardar.Enabled = false;
            this.btnGuardar.Location = new System.Drawing.Point(140, 330);
            this.btnGuardar.Name = "btnGuardar";
            this.btnGuardar.Size = new System.Drawing.Size(100, 30);
            this.btnGuardar.TabIndex = 25;
            this.btnGuardar.Text = "Guardar";
            this.btnGuardar.Click += new System.EventHandler(this.btnGuardar_Click);
            // 
            // btnCancelar
            // 
            this.btnCancelar.Enabled = false;
            this.btnCancelar.Location = new System.Drawing.Point(260, 330);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(100, 30);
            this.btnCancelar.TabIndex = 26;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click);
            // 
            // btnAddCesta
            // 
            this.btnAddCesta.Location = new System.Drawing.Point(678, 330);
            this.btnAddCesta.Name = "btnAddCesta";
            this.btnAddCesta.Size = new System.Drawing.Size(100, 30);
            this.btnAddCesta.TabIndex = 27;
            this.btnAddCesta.Text = "Añadir a mi cesta";
            this.btnAddCesta.Click += new System.EventHandler(this.btnAddCesta_Click);
            // 
            // VideojuegoPropertiesForm
            // 
            this.ClientSize = new System.Drawing.Size(839, 400);
            this.Controls.Add(this.btnAddCesta);
            this.Controls.Add(this.lblTitulo);
            this.Controls.Add(this.txtTitulo);
            this.Controls.Add(this.lblDescripcion);
            this.Controls.Add(this.txtDescripcion);
            this.Controls.Add(this.lblGenero);
            this.Controls.Add(this.txtGenero);
            this.Controls.Add(this.lblDesarrollador);
            this.Controls.Add(this.txtDesarrollador);
            this.Controls.Add(this.lblEditor);
            this.Controls.Add(this.txtEditor);
            this.Controls.Add(this.lblPrecio);
            this.Controls.Add(this.txtPrecio);
            this.Controls.Add(this.lblPrecioOriginal);
            this.Controls.Add(this.txtPrecioOriginal);
            this.Controls.Add(this.lblValoracion);
            this.Controls.Add(this.txtValoracion);
            this.Controls.Add(this.lblSoporteVR);
            this.Controls.Add(this.chkSoporteVR);
            this.Controls.Add(this.lblTamaño);
            this.Controls.Add(this.txtTamaño);
            this.Controls.Add(this.lblEstado);
            this.Controls.Add(this.cmbEstado);
            this.Controls.Add(this.lblImagen);
            this.Controls.Add(this.pbImagen);
            this.Controls.Add(this.btnEditar);
            this.Controls.Add(this.btnGuardar);
            this.Controls.Add(this.btnCancelar);
            this.Name = "VideojuegoPropertiesForm";
            this.Text = "Detalles del Juego";
            ((System.ComponentModel.ISupportInitialize)(this.pbImagen)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }



        #endregion

        private System.Windows.Forms.Button btnAddCesta;
    }
}