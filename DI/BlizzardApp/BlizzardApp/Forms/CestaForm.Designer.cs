namespace BlizzardApp.Forms
{
    partial class CestaForm
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
            this.catalogo = new BlizzardApp.Catalogo();
            this.lblCesta = new System.Windows.Forms.Label();
            this.button1 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // catalogo
            // 
            catalogo.BackColor = System.Drawing.SystemColors.InactiveCaption;
            catalogo.Location = new System.Drawing.Point(12, 53);
            catalogo.Name = "catalogo";
            catalogo.Size = new System.Drawing.Size(522, 200);
            catalogo.TabIndex = 12;
            // 
            // lblCesta
            // 
            this.lblCesta.AutoSize = true;
            this.lblCesta.Font = new System.Drawing.Font("Mistral", 20.25F, System.Drawing.FontStyle.Underline, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblCesta.Location = new System.Drawing.Point(22, 10);
            this.lblCesta.Name = "lblCesta";
            this.lblCesta.Size = new System.Drawing.Size(91, 33);
            this.lblCesta.TabIndex = 13;
            this.lblCesta.Text = "Mi Cesta";
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("Microsoft Sans Serif", 30F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(254)));
            this.button1.Location = new System.Drawing.Point(565, 116);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(208, 86);
            this.button1.TabIndex = 14;
            this.button1.Text = "Comprar";
            this.button1.UseVisualStyleBackColor = true;
            // 
            // CestaForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 279);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.lblCesta);
            this.Controls.Add(catalogo);
            this.Name = "CestaForm";
            this.Text = "CestaForm";
            this.Load += new System.EventHandler(this.CestaForm_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private BlizzardApp.Catalogo catalogo;
        private System.Windows.Forms.Label lblCesta;
        private System.Windows.Forms.Button button1;
    }
}