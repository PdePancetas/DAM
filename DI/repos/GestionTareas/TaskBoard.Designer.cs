namespace GestionTareas
{
    partial class TaskBoard
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

        #region Código generado por el Diseñador de componentes

        /// <summary> 
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.tableLayoutPanel1 = new System.Windows.Forms.TableLayoutPanel();
            this.panelPendiente = new System.Windows.Forms.Panel();
            this.lblPendiente = new System.Windows.Forms.Label();
            this.panelEnCurso = new System.Windows.Forms.Panel();
            this.lblEnCurso = new System.Windows.Forms.Label();
            this.panelCompletado = new System.Windows.Forms.Panel();
            this.lblCompletado = new System.Windows.Forms.Label();
            this.btnAddTarea = new System.Windows.Forms.Button();
            this.panelPendiente.SuspendLayout();
            this.panelEnCurso.SuspendLayout();
            this.panelCompletado.SuspendLayout();
            this.SuspendLayout();
            // 
            // tableLayoutPanel1
            // 
            this.tableLayoutPanel1.AllowDrop = true;
            this.tableLayoutPanel1.CellBorderStyle = System.Windows.Forms.TableLayoutPanelCellBorderStyle.InsetDouble;
            this.tableLayoutPanel1.ColumnCount = 3;
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33.33333F));
            this.tableLayoutPanel1.Location = new System.Drawing.Point(3, 42);
            this.tableLayoutPanel1.Name = "tableLayoutPanel1";
            this.tableLayoutPanel1.RowCount = 1;
            this.tableLayoutPanel1.RowStyles.Add(new System.Windows.Forms.RowStyle());
            this.tableLayoutPanel1.Size = new System.Drawing.Size(502, 336);
            this.tableLayoutPanel1.TabIndex = 0;
            this.tableLayoutPanel1.DragDrop += new System.Windows.Forms.DragEventHandler(this.tableLayoutPanel_DragDrop);
            this.tableLayoutPanel1.DragEnter += new System.Windows.Forms.DragEventHandler(this.tableLayoutPanel_DragEnter);
            this.tableLayoutPanel1.Paint += new System.Windows.Forms.PaintEventHandler(this.tableLayoutPanel1_Paint);
            // 
            // panelPendiente
            // 
            this.panelPendiente.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(128)))), ((int)(((byte)(128)))));
            this.panelPendiente.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelPendiente.Controls.Add(this.lblPendiente);
            this.panelPendiente.Location = new System.Drawing.Point(3, 3);
            this.panelPendiente.Name = "panelPendiente";
            this.panelPendiente.Size = new System.Drawing.Size(160, 33);
            this.panelPendiente.TabIndex = 1;
            // 
            // lblPendiente
            // 
            this.lblPendiente.AutoSize = true;
            this.lblPendiente.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(254)));
            this.lblPendiente.Location = new System.Drawing.Point(51, 9);
            this.lblPendiente.Name = "lblPendiente";
            this.lblPendiente.Size = new System.Drawing.Size(64, 13);
            this.lblPendiente.TabIndex = 0;
            this.lblPendiente.Text = "Pendiente";
            this.lblPendiente.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelEnCurso
            // 
            this.panelEnCurso.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(192)))), ((int)(((byte)(128)))));
            this.panelEnCurso.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelEnCurso.Controls.Add(this.lblEnCurso);
            this.panelEnCurso.Location = new System.Drawing.Point(169, 3);
            this.panelEnCurso.Name = "panelEnCurso";
            this.panelEnCurso.Size = new System.Drawing.Size(167, 33);
            this.panelEnCurso.TabIndex = 2;
            // 
            // lblEnCurso
            // 
            this.lblEnCurso.AutoSize = true;
            this.lblEnCurso.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(254)));
            this.lblEnCurso.Location = new System.Drawing.Point(49, 9);
            this.lblEnCurso.Name = "lblEnCurso";
            this.lblEnCurso.Size = new System.Drawing.Size(58, 13);
            this.lblEnCurso.TabIndex = 1;
            this.lblEnCurso.Text = "En Curso";
            this.lblEnCurso.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // panelCompletado
            // 
            this.panelCompletado.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(128)))), ((int)(((byte)(255)))), ((int)(((byte)(128)))));
            this.panelCompletado.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panelCompletado.Controls.Add(this.lblCompletado);
            this.panelCompletado.Location = new System.Drawing.Point(342, 3);
            this.panelCompletado.Name = "panelCompletado";
            this.panelCompletado.Size = new System.Drawing.Size(163, 33);
            this.panelCompletado.TabIndex = 3;
            // 
            // lblCompletado
            // 
            this.lblCompletado.AutoSize = true;
            this.lblCompletado.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(254)));
            this.lblCompletado.Location = new System.Drawing.Point(49, 9);
            this.lblCompletado.Name = "lblCompletado";
            this.lblCompletado.Size = new System.Drawing.Size(73, 13);
            this.lblCompletado.TabIndex = 2;
            this.lblCompletado.Text = "Completado";
            this.lblCompletado.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // btnAddTarea
            // 
            this.btnAddTarea.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(128)))), ((int)(((byte)(128)))), ((int)(((byte)(255)))));
            this.btnAddTarea.FlatAppearance.BorderColor = System.Drawing.Color.Navy;
            this.btnAddTarea.FlatAppearance.BorderSize = 10;
            this.btnAddTarea.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnAddTarea.Location = new System.Drawing.Point(169, 384);
            this.btnAddTarea.Name = "btnAddTarea";
            this.btnAddTarea.Size = new System.Drawing.Size(167, 22);
            this.btnAddTarea.TabIndex = 4;
            this.btnAddTarea.Text = "Añadir Tarea";
            this.btnAddTarea.UseVisualStyleBackColor = false;
            this.btnAddTarea.Click += new System.EventHandler(this.btnAddTarea_Click);
            // 
            // TaskBoard
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.Controls.Add(this.btnAddTarea);
            this.Controls.Add(this.panelCompletado);
            this.Controls.Add(this.panelEnCurso);
            this.Controls.Add(this.panelPendiente);
            this.Controls.Add(this.tableLayoutPanel1);
            this.Name = "TaskBoard";
            this.Size = new System.Drawing.Size(510, 414);
            this.panelPendiente.ResumeLayout(false);
            this.panelPendiente.PerformLayout();
            this.panelEnCurso.ResumeLayout(false);
            this.panelEnCurso.PerformLayout();
            this.panelCompletado.ResumeLayout(false);
            this.panelCompletado.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel1;
        private System.Windows.Forms.Panel panelPendiente;
        private System.Windows.Forms.Panel panelCompletado;
        private System.Windows.Forms.Panel panelEnCurso;
        private System.Windows.Forms.Button btnAddTarea;
        private System.Windows.Forms.Label lblPendiente;
        private System.Windows.Forms.Label lblEnCurso;
        private System.Windows.Forms.Label lblCompletado;
    }
}
