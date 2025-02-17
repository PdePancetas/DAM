using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;

namespace GestionTareas
{
    public partial class TaskBoard : UserControl
    {
        private FlowLayoutPanel[] columnPanels;

        public TaskBoard()
        {
            InitializeComponent();
            InitializeBoard();
        }

        private void InitializeBoard()
        {
            tableLayoutPanel1.ColumnCount = 3;
            tableLayoutPanel1.RowCount = 1;
            tableLayoutPanel1.ColumnStyles.Clear();
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33f));
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33f));
            tableLayoutPanel1.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33f));

            CreateColumnHeader("Pendiente", 0);
            CreateColumnHeader("En curso", 1);
            CreateColumnHeader("Completado", 2);

            columnPanels = new FlowLayoutPanel[3];
            for (int i = 0; i < 3; i++)
            {
                columnPanels[i] = new FlowLayoutPanel
                {
                    Dock = DockStyle.Fill,
                    AutoScroll = true,
                    BackColor = GetColumnColor(i),
                    Padding = new Padding(5),
                    AllowDrop = true // Asegúrate de que el panel permita el arrastre
                };
                tableLayoutPanel1.Controls.Add(columnPanels[i], i, 1);

                columnPanels[i].DragEnter += ColumnPanel_DragEnter;
                columnPanels[i].DragLeave += ColumnPanel_DragLeave;
                columnPanels[i].DragDrop += ColumnPanel_DragDrop;
            }

            tableLayoutPanel1.RowCount++;
            tableLayoutPanel1.RowStyles.Add(new RowStyle(SizeType.Percent, 100f));
        }

        private Color GetColumnColor(int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    return Color.LightCoral;
                case 1:
                    return Color.LightGoldenrodYellow;
                case 2:
                    return Color.LightGreen;
                default:
                    return Color.White;
            }
        }

        private void CreateColumnHeader(string headerText, int columnIndex)
        {
            Panel headerPanel = new Panel
            {
                Dock = DockStyle.Fill,
                BackColor = GetColumnColor(columnIndex)
            };

            Label headerLabel = new Label
            {
                Text = headerText,
                TextAlign = ContentAlignment.MiddleCenter,
                Dock = DockStyle.Fill,
                Font = new Font("Segoe UI", 10, FontStyle.Bold)
            };

            headerPanel.Controls.Add(headerLabel);
            tableLayoutPanel1.Controls.Add(headerPanel, columnIndex, 0);
        }

        private void btnAddTarea_Click(object sender, EventArgs e)
        {
            using (TaskForm taskForm = new TaskForm())
                if (taskForm.ShowDialog() == DialogResult.OK)
                {
                    TaskItem newTask = new TaskItem(taskForm.TaskContent, taskForm.TaskType)
                    {
                        Margin = new Padding(3, 3, 3, 10)
                    };

                    int targetColumn;
                    switch (taskForm.TaskType)
                    {
                        case "Pendiente":
                            targetColumn = 0;
                            break;
                        case "En curso":
                            targetColumn = 1;
                            break;
                        default:
                            targetColumn = 2;
                            break;
                    }

                    columnPanels[targetColumn].Controls.Add(newTask);
                    ConfigureTaskDragEvents(newTask);
                    if (this.informeTareas != null)
                    {
                        informeTareas.CargarDatosEnCharts(obtenerTareas(tableLayoutPanel1));

                    }
                }
        }

        private void ConfigureTaskDragEvents(TaskItem task)
        {
            task.MouseDown += (sender, e) =>
            {
                if (e.Button == MouseButtons.Left)
                {
                    task.DoDragDrop(task, DragDropEffects.Move);
                }
            };
        }

        private void ColumnPanel_DragEnter(object sender, DragEventArgs e)
        {
            if (e.Data.GetDataPresent(typeof(TaskItem)))
            {
                e.Effect = DragDropEffects.Move;
                (sender as Control).BackColor = Color.LightGray;
                if (this.informeTareas == null)
                {
                    informeTareas.CargarDatosEnCharts(obtenerTareas(tableLayoutPanel1));

                }
            }
        }

        private void ColumnPanel_DragLeave(object sender, EventArgs e)
        {
            int columnIndex = Array.IndexOf(columnPanels, sender);
            (sender as Control).BackColor = GetColumnColor(columnIndex);
            if (this.informeTareas != null)
            {
                informeTareas.CargarDatosEnCharts(obtenerTareas(tableLayoutPanel1));

            }
        }

        private void ColumnPanel_DragDrop(object sender, DragEventArgs e)
        {
            FlowLayoutPanel targetPanel = sender as FlowLayoutPanel;
            if (e.Data.GetDataPresent(typeof(TaskItem)))
            {
                TaskItem task = (TaskItem)e.Data.GetData(typeof(TaskItem));

                var parentPanel = task.Parent as FlowLayoutPanel;
                parentPanel?.Controls.Remove(task);

                targetPanel.Controls.Add(task);

                string taskType;
                int columnIndex = Array.IndexOf(columnPanels, targetPanel);
                switch (columnIndex)
                {
                    case 0:
                        taskType = "Pendiente";
                        break;
                    case 1:
                        taskType = "En curso";
                        break;
                    default:
                        taskType = "Completado";
                        break;
                }

                task.UpdateTaskType(taskType);
            }
            targetPanel.BackColor = GetColumnColor(Array.IndexOf(columnPanels, targetPanel));
            if (this.informeTareas != null)
            {
                informeTareas.CargarDatosEnCharts(obtenerTareas(tableLayoutPanel1));

            }
        }

        private void btnInforme_Click(object sender, EventArgs e)
        {

            List<TaskItem> tareas = obtenerTareas(tableLayoutPanel1);
            if (this.informeTareas == null || this.informeTareas.IsDisposed)
            {
                this.informeTareas = new InformeTareas(tareas);
                informeTareas.Show();
            }
            if (this.informeTareas != null)
            {
                informeTareas.CargarDatosEnCharts(obtenerTareas(tableLayoutPanel1));

            }


        }

        private List<TaskItem> obtenerTareas(TableLayoutPanel tableLayoutPanel1)
        {
            List<TaskItem> tareas = new List<TaskItem>();

            foreach (FlowLayoutPanel panel in columnPanels)
            {
                tareas.AddRange(panel.Controls.OfType<TaskItem>());
            }

            return tareas;
        }
    }
}