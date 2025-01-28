namespace GestionTareas
{
    partial class TaskForm
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
            this.radioButtonPendiente = new System.Windows.Forms.RadioButton();
            this.radioButtonCompletado = new System.Windows.Forms.RadioButton();
            this.radioButtonEnCurso = new System.Windows.Forms.RadioButton();
            this.richTextBoxContent = new System.Windows.Forms.RichTextBox();
            this.btnCrearTarea = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btnCancelar = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.SuspendLayout();
            // 
            // radioButtonPendiente
            // 
            this.radioButtonPendiente.AutoSize = true;
            this.radioButtonPendiente.Location = new System.Drawing.Point(17, 19);
            this.radioButtonPendiente.Name = "radioButtonPendiente";
            this.radioButtonPendiente.Size = new System.Drawing.Size(73, 17);
            this.radioButtonPendiente.TabIndex = 0;
            this.radioButtonPendiente.TabStop = true;
            this.radioButtonPendiente.Text = "Pendiente";
            this.radioButtonPendiente.UseVisualStyleBackColor = true;
            // 
            // radioButtonCompletado
            // 
            this.radioButtonCompletado.AutoSize = true;
            this.radioButtonCompletado.Location = new System.Drawing.Point(17, 65);
            this.radioButtonCompletado.Name = "radioButtonCompletado";
            this.radioButtonCompletado.Size = new System.Drawing.Size(81, 17);
            this.radioButtonCompletado.TabIndex = 1;
            this.radioButtonCompletado.TabStop = true;
            this.radioButtonCompletado.Text = "Completado";
            this.radioButtonCompletado.UseVisualStyleBackColor = true;
            // 
            // radioButtonEnCurso
            // 
            this.radioButtonEnCurso.AutoSize = true;
            this.radioButtonEnCurso.Location = new System.Drawing.Point(17, 42);
            this.radioButtonEnCurso.Name = "radioButtonEnCurso";
            this.radioButtonEnCurso.Size = new System.Drawing.Size(68, 17);
            this.radioButtonEnCurso.TabIndex = 2;
            this.radioButtonEnCurso.TabStop = true;
            this.radioButtonEnCurso.Text = "En Curso";
            this.radioButtonEnCurso.UseVisualStyleBackColor = true;
            // 
            // richTextBoxContent
            // 
            this.richTextBoxContent.Location = new System.Drawing.Point(141, 12);
            this.richTextBoxContent.Name = "richTextBoxContent";
            this.richTextBoxContent.Size = new System.Drawing.Size(187, 62);
            this.richTextBoxContent.TabIndex = 3;
            this.richTextBoxContent.Text = "";
            // 
            // btnCrearTarea
            // 
            this.btnCrearTarea.Location = new System.Drawing.Point(141, 80);
            this.btnCrearTarea.Name = "btnCrearTarea";
            this.btnCrearTarea.Size = new System.Drawing.Size(75, 23);
            this.btnCrearTarea.TabIndex = 4;
            this.btnCrearTarea.Text = "Crear";
            this.btnCrearTarea.UseVisualStyleBackColor = true;
            this.btnCrearTarea.Click += new System.EventHandler(this.btnCrearTarea_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.radioButtonPendiente);
            this.groupBox1.Controls.Add(this.radioButtonEnCurso);
            this.groupBox1.Controls.Add(this.radioButtonCompletado);
            this.groupBox1.Location = new System.Drawing.Point(12, 2);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(123, 91);
            this.groupBox1.TabIndex = 5;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Estado de la tarea";
            // 
            // btnCancelar
            // 
            this.btnCancelar.Location = new System.Drawing.Point(241, 80);
            this.btnCancelar.Name = "btnCancelar";
            this.btnCancelar.Size = new System.Drawing.Size(75, 23);
            this.btnCancelar.TabIndex = 6;
            this.btnCancelar.Text = "Cancelar";
            this.btnCancelar.UseVisualStyleBackColor = true;
            this.btnCancelar.Click += new System.EventHandler(this.btnCancelar_Click_1);
            // 
            // TaskForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(337, 105);
            this.Controls.Add(this.btnCancelar);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.btnCrearTarea);
            this.Controls.Add(this.richTextBoxContent);
            this.Name = "TaskForm";
            this.Text = "TaskForm";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.RadioButton radioButtonPendiente;
        private System.Windows.Forms.RadioButton radioButtonCompletado;
        private System.Windows.Forms.RadioButton radioButtonEnCurso;
        private System.Windows.Forms.RichTextBox richTextBoxContent;
        private System.Windows.Forms.Button btnCrearTarea;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnCancelar;
    }
}