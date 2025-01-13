namespace BlizzardApp
{
    partial class Home
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.TreeNode treeNode1 = new System.Windows.Forms.TreeNode("Mis juegos");
            System.Windows.Forms.TreeNode treeNode2 = new System.Windows.Forms.TreeNode("Vendidos");
            System.Windows.Forms.TreeNode treeNode3 = new System.Windows.Forms.TreeNode("Destacados", new System.Windows.Forms.TreeNode[] {
            treeNode1,
            treeNode2});
            System.Windows.Forms.TreeNode treeNode4 = new System.Windows.Forms.TreeNode("Galería");
            System.Windows.Forms.TreeNode treeNode5 = new System.Windows.Forms.TreeNode("Fondos de pantalla");
            System.Windows.Forms.TreeNode treeNode6 = new System.Windows.Forms.TreeNode("Perfil", new System.Windows.Forms.TreeNode[] {
            treeNode4,
            treeNode5});
            this.btnShop = new System.Windows.Forms.Button();
            this.btnCommunity = new System.Windows.Forms.Button();
            this.btnSupport = new System.Windows.Forms.Button();
            this.panel_ultimosLanzamientos = new System.Windows.Forms.Panel();
            this.pictureBox4 = new System.Windows.Forms.PictureBox();
            this.pictureBox3 = new System.Windows.Forms.PictureBox();
            this.pictureBox2 = new System.Windows.Forms.PictureBox();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.comboBox_Perfil = new System.Windows.Forms.ComboBox();
            this.treeView1 = new System.Windows.Forms.TreeView();
            this.panel1 = new System.Windows.Forms.Panel();
            this.pictureBox6 = new System.Windows.Forms.PictureBox();
            this.pictureBox5 = new System.Windows.Forms.PictureBox();
            this.oferta1 = new System.Windows.Forms.PictureBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.textoBienvenida = new System.Windows.Forms.TextBox();
            this.panel_ultimosLanzamientos.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox4)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.panel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox6)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox5)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.oferta1)).BeginInit();
            this.SuspendLayout();
            // 
            // btnShop
            // 
            this.btnShop.BackColor = System.Drawing.Color.CadetBlue;
            this.btnShop.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnShop.Location = new System.Drawing.Point(256, 12);
            this.btnShop.Name = "btnShop";
            this.btnShop.Size = new System.Drawing.Size(105, 28);
            this.btnShop.TabIndex = 0;
            this.btnShop.Text = "Tienda";
            this.btnShop.UseMnemonic = false;
            this.btnShop.UseVisualStyleBackColor = false;
            this.btnShop.Click += new System.EventHandler(this.btnTienda_Click);
            // 
            // btnCommunity
            // 
            this.btnCommunity.BackColor = System.Drawing.Color.CadetBlue;
            this.btnCommunity.Location = new System.Drawing.Point(391, 11);
            this.btnCommunity.Name = "btnCommunity";
            this.btnCommunity.Size = new System.Drawing.Size(105, 28);
            this.btnCommunity.TabIndex = 1;
            this.btnCommunity.Text = "Comunidad";
            this.btnCommunity.UseVisualStyleBackColor = false;
            this.btnCommunity.Click += new System.EventHandler(this.btnCommunity_Click);
            // 
            // btnSupport
            // 
            this.btnSupport.BackColor = System.Drawing.Color.CadetBlue;
            this.btnSupport.Location = new System.Drawing.Point(535, 12);
            this.btnSupport.Name = "btnSupport";
            this.btnSupport.Size = new System.Drawing.Size(105, 28);
            this.btnSupport.TabIndex = 2;
            this.btnSupport.Text = "Soporte";
            this.btnSupport.UseVisualStyleBackColor = false;
            this.btnSupport.Click += new System.EventHandler(this.btnSupport_Click);
            // 
            // panel_ultimosLanzamientos
            // 
            this.panel_ultimosLanzamientos.BackColor = System.Drawing.Color.Transparent;
            this.panel_ultimosLanzamientos.Controls.Add(this.pictureBox4);
            this.panel_ultimosLanzamientos.Controls.Add(this.pictureBox3);
            this.panel_ultimosLanzamientos.Controls.Add(this.pictureBox2);
            this.panel_ultimosLanzamientos.Location = new System.Drawing.Point(230, 108);
            this.panel_ultimosLanzamientos.Name = "panel_ultimosLanzamientos";
            this.panel_ultimosLanzamientos.Size = new System.Drawing.Size(433, 161);
            this.panel_ultimosLanzamientos.TabIndex = 4;
            // 
            // pictureBox4
            // 
            this.pictureBox4.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox4.Image = global::BlizzardApp.Properties.Resources.juego3;
            this.pictureBox4.Location = new System.Drawing.Point(297, 0);
            this.pictureBox4.Name = "pictureBox4";
            this.pictureBox4.Size = new System.Drawing.Size(120, 164);
            this.pictureBox4.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox4.TabIndex = 8;
            this.pictureBox4.TabStop = false;
            // 
            // pictureBox3
            // 
            this.pictureBox3.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.pictureBox3.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox3.Image = global::BlizzardApp.Properties.Resources.juego2;
            this.pictureBox3.Location = new System.Drawing.Point(152, 0);
            this.pictureBox3.Name = "pictureBox3";
            this.pictureBox3.Size = new System.Drawing.Size(132, 164);
            this.pictureBox3.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox3.TabIndex = 7;
            this.pictureBox3.TabStop = false;
            // 
            // pictureBox2
            // 
            this.pictureBox2.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox2.Image = global::BlizzardApp.Properties.Resources.juego1;
            this.pictureBox2.Location = new System.Drawing.Point(13, 0);
            this.pictureBox2.Margin = new System.Windows.Forms.Padding(300, 32, 30, 32);
            this.pictureBox2.Name = "pictureBox2";
            this.pictureBox2.Size = new System.Drawing.Size(118, 164);
            this.pictureBox2.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox2.TabIndex = 6;
            this.pictureBox2.TabStop = false;
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pictureBox1.Image = global::BlizzardApp.Properties.Resources.blizzardLogo1;
            this.pictureBox1.Location = new System.Drawing.Point(10, 11);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(220, 91);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox1.TabIndex = 5;
            this.pictureBox1.TabStop = false;
            // 
            // comboBox_Perfil
            // 
            this.comboBox_Perfil.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.comboBox_Perfil.BackColor = System.Drawing.Color.SkyBlue;
            this.comboBox_Perfil.FormattingEnabled = true;
            this.comboBox_Perfil.Items.AddRange(new object[] {
            "Ver mis datos",
            "Detalles de mi cuenta",
            "Filtros de la tienda",
            "Cerrar sesión",
            "Salir"});
            this.comboBox_Perfil.Location = new System.Drawing.Point(750, 10);
            this.comboBox_Perfil.Margin = new System.Windows.Forms.Padding(2);
            this.comboBox_Perfil.Name = "comboBox_Perfil";
            this.comboBox_Perfil.Size = new System.Drawing.Size(128, 21);
            this.comboBox_Perfil.TabIndex = 6;
            this.comboBox_Perfil.Text = "Mi Perfil";
            this.comboBox_Perfil.SelectedIndexChanged += new System.EventHandler(this.comboBox_Perfil_SelectedIndexChanged);
            // 
            // treeView1
            // 
            this.treeView1.BackColor = System.Drawing.SystemColors.InactiveCaption;
            this.treeView1.LineColor = System.Drawing.Color.White;
            this.treeView1.Location = new System.Drawing.Point(12, 108);
            this.treeView1.Name = "treeView1";
            treeNode1.ForeColor = System.Drawing.Color.Red;
            treeNode1.Name = "MisJuegos";
            treeNode1.NodeFont = new System.Drawing.Font("MV Boli", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode1.Text = "Mis juegos";
            treeNode2.ForeColor = System.Drawing.Color.Red;
            treeNode2.Name = "Vendidos";
            treeNode2.NodeFont = new System.Drawing.Font("MV Boli", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode2.Text = "Vendidos";
            treeNode3.ForeColor = System.Drawing.Color.Blue;
            treeNode3.Name = "Destacados";
            treeNode3.NodeFont = new System.Drawing.Font("Modern No. 20", 8.249999F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode3.Text = "Destacados";
            treeNode4.ForeColor = System.Drawing.Color.Red;
            treeNode4.Name = "Galeria";
            treeNode4.NodeFont = new System.Drawing.Font("MV Boli", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode4.Text = "Galería";
            treeNode5.ForeColor = System.Drawing.Color.Red;
            treeNode5.Name = "FondosPantalla";
            treeNode5.NodeFont = new System.Drawing.Font("MV Boli", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode5.Text = "Fondos de pantalla";
            treeNode6.ForeColor = System.Drawing.Color.Blue;
            treeNode6.Name = "Perfil";
            treeNode6.NodeFont = new System.Drawing.Font("Modern No. 20", 8.249999F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode6.Text = "Perfil";
            this.treeView1.Nodes.AddRange(new System.Windows.Forms.TreeNode[] {
            treeNode3,
            treeNode6});
            this.treeView1.Size = new System.Drawing.Size(199, 106);
            this.treeView1.TabIndex = 0;
            // 
            // panel1
            // 
            this.panel1.BackColor = System.Drawing.Color.MintCream;
            this.panel1.Controls.Add(this.pictureBox6);
            this.panel1.Controls.Add(this.pictureBox5);
            this.panel1.Controls.Add(this.oferta1);
            this.panel1.Location = new System.Drawing.Point(246, 307);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(401, 167);
            this.panel1.TabIndex = 7;
            // 
            // pictureBox6
            // 
            this.pictureBox6.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.pictureBox6.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox6.Image = global::BlizzardApp.Properties.Resources.starcraft_remastered_portada;
            this.pictureBox6.Location = new System.Drawing.Point(268, 0);
            this.pictureBox6.Name = "pictureBox6";
            this.pictureBox6.Size = new System.Drawing.Size(133, 164);
            this.pictureBox6.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox6.TabIndex = 11;
            this.pictureBox6.TabStop = false;
            // 
            // pictureBox5
            // 
            this.pictureBox5.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.pictureBox5.BackColor = System.Drawing.Color.Transparent;
            this.pictureBox5.Image = global::BlizzardApp.Properties.Resources.StarWarsOutlaws;
            this.pictureBox5.Location = new System.Drawing.Point(136, 0);
            this.pictureBox5.Name = "pictureBox5";
            this.pictureBox5.Size = new System.Drawing.Size(132, 164);
            this.pictureBox5.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pictureBox5.TabIndex = 10;
            this.pictureBox5.TabStop = false;
            // 
            // oferta1
            // 
            this.oferta1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.oferta1.BackColor = System.Drawing.Color.Transparent;
            this.oferta1.Image = global::BlizzardApp.Properties.Resources.CoD;
            this.oferta1.Location = new System.Drawing.Point(0, 0);
            this.oferta1.Name = "oferta1";
            this.oferta1.Size = new System.Drawing.Size(138, 164);
            this.oferta1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.oferta1.TabIndex = 9;
            this.oferta1.TabStop = false;
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(243, 66);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(117, 20);
            this.textBox1.TabIndex = 8;
            this.textBox1.Text = "Últimos lanzamientos";
            this.textBox1.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(243, 281);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(117, 20);
            this.textBox2.TabIndex = 9;
            this.textBox2.Text = "Ofertas";
            this.textBox2.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
            // 
            // textoBienvenida
            // 
            this.textoBienvenida.Location = new System.Drawing.Point(10, 575);
            this.textoBienvenida.Name = "textoBienvenida";
            this.textoBienvenida.Size = new System.Drawing.Size(100, 20);
            this.textoBienvenida.TabIndex = 10;
            this.textoBienvenida.Text = "Bienvenido "+LoginForm.loggedUser;
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.ControlLight;
            this.BackgroundImage = global::BlizzardApp.Properties.Resources.fondo;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(886, 607);
            this.Controls.Add(this.textoBienvenida);
            this.Controls.Add(this.textBox2);
            this.Controls.Add(this.textBox1);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.treeView1);
            this.Controls.Add(this.comboBox_Perfil);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.panel_ultimosLanzamientos);
            this.Controls.Add(this.btnSupport);
            this.Controls.Add(this.btnCommunity);
            this.Controls.Add(this.btnShop);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Home";
            this.Text = "BlizzForm";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.panel_ultimosLanzamientos.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox4)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox3)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox2)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.panel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox6)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox5)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.oferta1)).EndInit();

            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Home_FormClosing);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnShop;
        private System.Windows.Forms.Button btnCommunity;
        private System.Windows.Forms.Button btnSupport;
        private System.Windows.Forms.Panel panel_ultimosLanzamientos;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.PictureBox pictureBox4;
        private System.Windows.Forms.PictureBox pictureBox3;
        private System.Windows.Forms.PictureBox pictureBox2;
        private System.Windows.Forms.ComboBox comboBox_Perfil;
        private System.Windows.Forms.TreeView treeView1;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.PictureBox oferta1;
        private System.Windows.Forms.PictureBox pictureBox5;
        private System.Windows.Forms.PictureBox pictureBox6;
        private System.Windows.Forms.TextBox textoBienvenida;
    }
}

