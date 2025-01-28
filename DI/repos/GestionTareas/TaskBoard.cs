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
    public partial class TaskBoard : UserControl
    {
        public TaskBoard()
        {
            InitializeComponent();

            // Configuración inicial del TableLayoutPanel
            tableLayoutPanel1.ColumnCount = 3;
            tableLayoutPanel1.RowCount = 1; // Para incluir los encabezados
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33)); // Columna para "Pendiente"
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33)); // Columna para "En curso"
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33)); // Columna para "Completado"

            
        }

        private void CreateColumnHeader(string headerText, int columnIndex)
        {
            Panel headerPanel = new Panel();
            headerPanel.Dock = DockStyle.Fill;
            Label headerLabel = new Label
            {
                Text = headerText,
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill
            };
            headerPanel.Controls.Add(headerLabel);
            tableLayoutPanel1.Controls.Add(headerPanel, columnIndex, 0); // Fila 0, columna "columnIndex"
        }

        private void tableLayoutPanel1_Paint(object sender, PaintEventArgs e)
        {
            // Definir los colores para cada columna (por ejemplo, 3 columnas)
            Color[] colores = { Color.Red, Color.Orange, Color.LightGreen };

            // Obtener las dimensiones de cada columna
            int totalColumnas = tableLayoutPanel1.ColumnCount;
            int x = 0;

            for (int col = 0; col < totalColumnas; col++)
            {
                // Obtiene el ancho de la columna
                int columnWidth = tableLayoutPanel1.GetColumnWidths()[col];

                // Dibujar el color de fondo para cada columna
                using (Brush brush = new SolidBrush(colores[col % colores.Length]))  // Utiliza un color para cada columna
                {
                    e.Graphics.FillRectangle(brush, x, 0, columnWidth, tableLayoutPanel1.Height);
                }

                // Actualizar la posición X para la siguiente columna
                x += columnWidth;
            }
        }

        /* private void btnAddTarea_Click(object sender, EventArgs e)
         {
             // Mostrar el formulario emergente para agregar una tarea
             TaskForm taskForm = new TaskForm();

             if (taskForm.ShowDialog() == DialogResult.OK)
             {
                 // Al pulsar Aceptar, obtenemos la información de la tarea
                 string taskContent = taskForm.TaskContent;
                 string taskType = taskForm.TaskType;

                 // Crear un nuevo control para mostrar la tarea (puede ser un Label o un TextBox)
                 TaskItem task = new TaskItem(taskContent);

                 // Según el tipo de tarea, agregamos el Label en la columna correspondiente
                 int columnIndex = taskType == "Pendiente" ? 0 : taskType == "En curso" ? 1 : 2;

                 // Añadir la tarea en una nueva fila
                 tableLayoutPanel1.RowCount++;
                 tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.AutoSize));

                 // Añadir el Label a la celda correspondiente
                 tableLayoutPanel1.Controls.Add(task, columnIndex, tableLayoutPanel1.RowCount - 1);
             }
         }*/

        private void btnAddTarea_Click(object sender, EventArgs e)
        {
            // Mostrar el formulario emergente para agregar una tarea
            TaskForm taskForm = new TaskForm();

            if (taskForm.ShowDialog() == DialogResult.OK)
            {
                // Al pulsar Aceptar, obtenemos la información de la tarea
                string taskContent = taskForm.TaskContent;
                string taskType = taskForm.TaskType;

                // Crear un nuevo control para mostrar la tarea
                TaskItem task = new TaskItem(taskContent, taskType);

                // Según el tipo de tarea, agregamos el Label en la columna correspondiente
                int columnIndex = taskType == "Pendiente" ? 0 : taskType == "En curso" ? 1 : 2;

                // Encontrar la primera fila vacía en la columna especificada
                int rowIndex = 0;
                while (rowIndex < tableLayoutPanel1.RowCount && tableLayoutPanel1.GetControlFromPosition(columnIndex, rowIndex) != null)
                {
                    rowIndex++;
                }

                // Si hemos llegado al final de las filas, agregamos una nueva fila
                if (rowIndex == tableLayoutPanel1.RowCount)
                {
                    tableLayoutPanel1.RowCount++;
                }

                // Insertamos el TaskItem en la fila y columna correspondientes
                tableLayoutPanel1.Controls.Add(task, columnIndex, rowIndex);

                // Si hay otros elementos en la misma columna, desplazamos las filas hacia abajo
                for (int i = rowIndex; i < tableLayoutPanel1.RowCount - 1; i++)
                {
                    var control = tableLayoutPanel1.GetControlFromPosition(columnIndex, i);
                    if (control != null)
                    {
                        // Desplazamos el control hacia la fila siguiente
                        tableLayoutPanel1.SetCellPosition(control, new TableLayoutPanelCellPosition(columnIndex, i));
                    }
                }
            }
        }

        private void tableLayoutPanel_DragEnter(object sender, DragEventArgs e)
        {
            // Permitir el drop solo si el objeto arrastrado es un TaskItem
            if (e.Data.GetDataPresent(typeof(TaskItem)))
            {
                e.Effect = DragDropEffects.Move;
            }
            else
            {
                e.Effect = DragDropEffects.None;
            }
        }

        private void tableLayoutPanel_DragDrop(object sender, DragEventArgs e)
        {
            // Obtener el TaskItem arrastrado
            TaskItem draggedTask = e.Data.GetData(typeof(TaskItem)) as TaskItem;

            if (draggedTask != null)
            {
                // Eliminar el TaskItem del panel original (si es necesario)
                // Este paso es opcional dependiendo de si deseas copiar o mover el control
                ((Panel)sender).Controls.Add(draggedTask);
                draggedTask.Location = ((Panel)sender).PointToClient(new Point(e.X, e.Y));

                // Cambiar de columna (panel) según el tipo de tarea
                int columnIndex = draggedTask.TaskType == "Pendiente" ? 0 : draggedTask.TaskType == "En curso" ? 1 : 2;

                if (columnIndex == 0)
                {
                    tableLayoutPanel1.Controls.Add(draggedTask);
                }
                else if (columnIndex == 1)
                {
                    panelEnCurso.Controls.Add(draggedTask);
                }
                else
                {
                    tableLayoutPanel1.Controls.Add(draggedTask);
                }
            }
        }
    }
}
