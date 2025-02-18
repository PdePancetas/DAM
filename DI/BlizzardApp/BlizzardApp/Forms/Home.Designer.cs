using System.Windows.Forms;

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
            System.Windows.Forms.TreeNode treeNode1 = new System.Windows.Forms.TreeNode("Mi Cesta");
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
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.comboBox_Perfil = new System.Windows.Forms.ComboBox();
            this.treeView1 = new System.Windows.Forms.TreeView();
            this.textoBienvenida = new System.Windows.Forms.TextBox();
            this.catalogo = new BlizzardApp.Catalogo();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
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
            treeNode1.Name = "MiCesta";
            treeNode1.NodeFont = new System.Drawing.Font("MV Boli", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            treeNode1.Text = "Mi Cesta";
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
            this.treeView1.AfterSelect += new System.Windows.Forms.TreeViewEventHandler(this.treeView1_AfterSelect);
            // 
            // textoBienvenida
            // 
            this.textoBienvenida.Location = new System.Drawing.Point(10, 575);
            this.textoBienvenida.Name = "textoBienvenida";
            this.textoBienvenida.Size = new System.Drawing.Size(100, 20);
            this.textoBienvenida.TabIndex = 10;
            this.textoBienvenida.Text = "Bienvenido ";
            // 
            // catalogo
            // 
            this.catalogo.BackColor = System.Drawing.SystemColors.InactiveCaption;
            this.catalogo.Location = new System.Drawing.Point(217, 108);
            this.catalogo.Name = "catalogo";
            this.catalogo.Size = new System.Drawing.Size(522, 200);
            this.catalogo.TabIndex = 11;
            // 
            // Home
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.AutoSize = true;
            this.BackColor = System.Drawing.SystemColors.ControlLight;
            this.BackgroundImage = global::BlizzardApp.Properties.Resources.fondo;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(886, 607);
            this.Controls.Add(this.catalogo);
            this.Controls.Add(this.textoBienvenida);
            this.Controls.Add(this.treeView1);
            this.Controls.Add(this.comboBox_Perfil);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.btnSupport);
            this.Controls.Add(this.btnCommunity);
            this.Controls.Add(this.btnShop);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.Fixed3D;
            this.Name = "Home";
            this.Text = "BlizzForm";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Home_FormClosing);
            this.Load += new System.EventHandler(this.Home_Load);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button btnShop;
        private System.Windows.Forms.Button btnCommunity;
        private System.Windows.Forms.Button btnSupport;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.ComboBox comboBox_Perfil;
        private System.Windows.Forms.TreeView treeView1;
        private System.Windows.Forms.TextBox textoBienvenida;
        private BlizzardApp.Catalogo catalogo;
    }
}

