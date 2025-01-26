namespace BlizzardApp
{
    partial class AdminOps
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
            this.btnMostrarUsuarios = new System.Windows.Forms.Button();
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.btnGuardarCambios = new System.Windows.Forms.Button();
            this.btnEliminarUsuario = new System.Windows.Forms.Button();
            this.txtUsuarioID = new System.Windows.Forms.TextBox();
            this.lblUsuarioID = new System.Windows.Forms.Label();
            this.btnConsultaUsuario = new System.Windows.Forms.Button();
            this.btnSalirAdminOps = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // btnMostrarUsuarios
            // 
            this.btnMostrarUsuarios.Location = new System.Drawing.Point(20, 20);
            this.btnMostrarUsuarios.Name = "btnMostrarUsuarios";
            this.btnMostrarUsuarios.Size = new System.Drawing.Size(145, 38);
            this.btnMostrarUsuarios.TabIndex = 1;
            this.btnMostrarUsuarios.Text = "Mostrar Usuarios";
            this.btnMostrarUsuarios.UseVisualStyleBackColor = true;
            this.btnMostrarUsuarios.Click += new System.EventHandler(this.btnMostrarUsuarios_Click);
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Location = new System.Drawing.Point(204, 60);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowHeadersWidth = 51;
            this.dataGridView1.Size = new System.Drawing.Size(638, 300);
            this.dataGridView1.TabIndex = 2;
            // 
            // btnGuardarCambios
            // 
            this.btnGuardarCambios.Location = new System.Drawing.Point(692, 366);
            this.btnGuardarCambios.Name = "btnGuardarCambios";
            this.btnGuardarCambios.Size = new System.Drawing.Size(150, 35);
            this.btnGuardarCambios.TabIndex = 3;
            this.btnGuardarCambios.Text = "Aplicar Cambios";
            this.btnGuardarCambios.UseVisualStyleBackColor = true;
            this.btnGuardarCambios.Click += new System.EventHandler(this.btnGuardarDatos_Click);
            // 
            // btnEliminarUsuario
            // 
            this.btnEliminarUsuario.Location = new System.Drawing.Point(20, 110);
            this.btnEliminarUsuario.Name = "btnEliminarUsuario";
            this.btnEliminarUsuario.Size = new System.Drawing.Size(145, 30);
            this.btnEliminarUsuario.TabIndex = 6;
            this.btnEliminarUsuario.Text = "Eliminar Usuario";
            this.btnEliminarUsuario.UseVisualStyleBackColor = true;
            this.btnEliminarUsuario.Click += new System.EventHandler(this.btnEliminarUsuario_Click);
            // 
            // txtUsuarioID
            // 
            this.txtUsuarioID.Location = new System.Drawing.Point(20, 80);
            this.txtUsuarioID.Name = "txtUsuarioID";
            this.txtUsuarioID.Size = new System.Drawing.Size(145, 22);
            this.txtUsuarioID.TabIndex = 4;
            // 
            // lblUsuarioID
            // 
            this.lblUsuarioID.AutoSize = true;
            this.lblUsuarioID.Location = new System.Drawing.Point(20, 60);
            this.lblUsuarioID.Name = "lblUsuarioID";
            this.lblUsuarioID.Size = new System.Drawing.Size(73, 16);
            this.lblUsuarioID.TabIndex = 5;
            this.lblUsuarioID.Text = "ID Usuario:";
            // 
            // btnConsultaUsuario
            // 
            this.btnConsultaUsuario.Location = new System.Drawing.Point(20, 150);
            this.btnConsultaUsuario.Name = "btnConsultaUsuario";
            this.btnConsultaUsuario.Size = new System.Drawing.Size(145, 30);
            this.btnConsultaUsuario.TabIndex = 7;
            this.btnConsultaUsuario.Text = "Consultar Usuario";
            this.btnConsultaUsuario.UseVisualStyleBackColor = true;
            this.btnConsultaUsuario.Click += new System.EventHandler(this.btnConsultaUsuario_Click);
            // 
            // btnSalirAdminOps
            // 
            this.btnSalirAdminOps.Location = new System.Drawing.Point(20, 200);
            this.btnSalirAdminOps.Name = "btnSalirAdminOps";
            this.btnSalirAdminOps.Size = new System.Drawing.Size(145, 30);
            this.btnSalirAdminOps.TabIndex = 8;
            this.btnSalirAdminOps.Text = "Salir";
            this.btnSalirAdminOps.UseVisualStyleBackColor = true;
            this.btnSalirAdminOps.Click += new System.EventHandler(this.btnSalirAdminOps_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(217, 375);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(469, 16);
            this.label1.TabIndex = 9;
            this.label1.Text = "Una vez realizadas modificaciones, guarde los cambios pulsando este botón:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(201, 31);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(392, 16);
            this.label2.TabIndex = 10;
            this.label2.Text = "Al añadir un nuevo campo a la tabla, asegurese de que el id es 0";
            // 
            // AdminOps
            // 
            this.ClientSize = new System.Drawing.Size(895, 450);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.btnSalirAdminOps);
            this.Controls.Add(this.btnConsultaUsuario);
            this.Controls.Add(this.btnEliminarUsuario);
            this.Controls.Add(this.lblUsuarioID);
            this.Controls.Add(this.txtUsuarioID);
            this.Controls.Add(this.btnGuardarCambios);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.btnMostrarUsuarios);
            this.Name = "AdminOps";
            this.Text = "Administración de Usuarios";
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }


        #endregion
        private System.Windows.Forms.Button btnMostrarUsuarios;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button btnGuardarCambios;
        private System.Windows.Forms.Button btnSalirAdminOps;
        private System.Windows.Forms.Button btnConsultaUsuario;
        private System.Windows.Forms.Button btnEliminarUsuario;
        private System.Windows.Forms.Label lblUsuarioID;
        private System.Windows.Forms.TextBox txtUsuarioID;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
    }
}