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
    public partial class TaskForm : Form
    {
        public string TaskContent { get; private set; }
        public string TaskType { get; private set; }

        public TaskForm()
        {
            InitializeComponent();

            // Configurar los RadioButtons (Por ejemplo)
            radioButtonPendiente.Text = "Pendiente";
            radioButtonEnCurso.Text = "En curso";
            radioButtonCompletado.Text = "Completado";

            // El tipo de tarea será "Pendiente" por defecto
            radioButtonPendiente.Checked = true;
        }

        private void btnCancelar_Click_1(object sender, EventArgs e)
        {
            // Si el usuario cancela, no se hace nada y simplemente cerramos el formulario
            this.DialogResult = DialogResult.Cancel;
            this.Close();
        }

        private void btnCrearTarea_Click(object sender, EventArgs e)
        {
            // Obtenemos el tipo de tarea seleccionada
            if (radioButtonPendiente.Checked)
                TaskType = "Pendiente";
            else if (radioButtonEnCurso.Checked)
                TaskType = "En curso";
            else if (radioButtonCompletado.Checked)
                TaskType = "Completado";

            // Obtenemos el contenido ingresado en el área de texto
            TaskContent = richTextBoxContent.Text;

            // Cerramos el formulario
            this.DialogResult = DialogResult.OK;
            this.Close();
        }
    }

}
