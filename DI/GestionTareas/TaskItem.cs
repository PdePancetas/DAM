using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GestionTareas
{
    public partial class TaskItem : UserControl
    {
        public string TaskContent { get; private set; }
        public string TaskType { get; private set; }

        public TaskItem()
        {
        }
        public TaskItem(string taskText, string taskType)
        {
            InitializeComponent();
            // Establecemos el texto de la tarea
            richTextBox.Text = taskText;
            TaskContent = taskText;
            TaskType = taskType;
        }

        // Evento para eliminar el TaskItem del TableLayoutPanel
        private void buttonRemove_Click(object sender, EventArgs e)
        {
            // Este control se elimina del contenedor (TableLayoutPanel)
            this.Parent.Controls.Remove(this);
        }
        

        private void btnEdit_Click(object sender, EventArgs e)
        {
            
            // Crear un "formulario emergente" con un RichTextBox editable
            Form editForm = new Form();
            editForm.Text = "Editar Texto";
            editForm.Size = new Size(400, 300);

            RichTextBox tempRichTextBox = new RichTextBox();
            tempRichTextBox.Size = new Size(360, 180);
            tempRichTextBox.Location = new Point(20, 20);
            tempRichTextBox.Text = richTextBox.Text;  // Cargar el texto original
            editForm.Controls.Add(tempRichTextBox);

            // Crear un botón OK para guardar los cambios
            Button btnOk = new Button();
            btnOk.Text = "OK";
            btnOk.Size = new Size(100, 30);
            btnOk.Location = new Point(20, 210);
            btnOk.Click += (s, args) =>
            {
                richTextBox.Text = tempRichTextBox.Text;  // Guardar el texto editado
                richTextBox.ReadOnly = true;  // Hacer el RichTextBox no editable después de editar
                editForm.Close();  // Cerrar el formulario
            };
            editForm.Controls.Add(btnOk);

            // Crear un botón Cancelar para cerrar sin guardar cambios
            Button btnCancel = new Button();
            btnCancel.Text = "Cancelar";
            btnCancel.Size = new Size(100, 30);
            btnCancel.Location = new Point(150, 210);
            btnCancel.Click += (s, args) =>
            {
                editForm.Close();  // Cerrar el formulario sin hacer cambios
            };
            editForm.Controls.Add(btnCancel);

            // Mostrar el formulario emergente
            editForm.ShowDialog();
            
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            this.Parent.Controls.Remove(this);
        }
        
        public void UpdateTaskType(string newType)
        {
            // Actualizar lógica interna y visualización según el tipo
            TaskType = newType;
            // (Ej: cambiar color del borde, iconos, etc)
        }

        private void TaskItem_MouseDown(object sender, MouseEventArgs e)
        {
            // Inicia el arrastre
            if (e.Button == MouseButtons.Left)
            {
                DoDragDrop(this, DragDropEffects.Move); // Mover el control
            }
        }
    }
}
