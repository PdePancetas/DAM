using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.DataVisualization.Charting;

namespace GestionTareas
{
    public partial class InformeTareas : Form
    {
        List<TaskItem> tasks;

        public InformeTareas(List<TaskItem> tareas)
        {
            this.tasks = tareas;
            InitializeComponent();
            CargarDatosEnCharts(this.tasks);
        }


        private Dictionary<string, int> ContarTareasPorTipo(List<TaskItem> tareas)
        {
            var tareasPorTipo = new Dictionary<string, int>
                {
                    { "Pendiente", 0 },
                    { "En curso", 0 },
                    { "Completado", 0 }
                };

            foreach (var tarea in tareas)
            {
                if (tareasPorTipo.ContainsKey(tarea.TaskType))
                {
                    tareasPorTipo[tarea.TaskType]++;
                }
            }

            return tareasPorTipo;
        }

        public void CargarDatosEnCharts(List<TaskItem> tareas)
        {
            CargarDatosEnChartAreas(tareas);
            
        }

        private void CargarDatosEnChartAreas(List<TaskItem> tareas)
        {
           
            datosTareas.Series.Clear();
            datosTareas.ChartAreas.Clear();
            datosTareas.Titles.Clear();
            datosTareas.Titles.Add("Tareas por Tipo");

            
            ChartArea chartAreaBar = new ChartArea("Area1")
            {
                Name = "Area1",
                Position = new ElementPosition(0, 0, 50, 100)  
            };

            chartAreaBar.Area3DStyle.Enable3D = true;
            chartAreaBar.Area3DStyle.Perspective = 1;
            chartAreaBar.Area3DStyle.Inclination = 1;
            chartAreaBar.Area3DStyle.Rotation = 5;
            chartAreaBar.Area3DStyle.IsClustered = true;

            ChartArea chartAreaPie = new ChartArea("Area2")
            {
                Name = "Area2",
                Position = new ElementPosition(50, 0, 50, 100) 
            };


            datosTareas.ChartAreas.Add(chartAreaBar);
            datosTareas.ChartAreas.Add(chartAreaPie);

            
            Series serieBarra = new Series()
            {
                ChartType = SeriesChartType.Bar,
                ChartArea = "Area1",
                XValueType = ChartValueType.String,
                YValueType = ChartValueType.Int64,
                IsVisibleInLegend = false

            };

            
           
            Series serieDonut = new Series()
            {
                ChartType = SeriesChartType.Doughnut,
                ChartArea = "Area2",
                XValueType = ChartValueType.String,
                YValueType = ChartValueType.Int64,
                IsValueShownAsLabel = true
            };

            
            var tareasPorTipo = ContarTareasPorTipo(tareas);

           
            foreach (var item in tareasPorTipo)
            {
                serieBarra.Points.AddXY(item.Key, item.Value);
            }

            foreach (var item in tareasPorTipo)
            {
                serieDonut.Points.AddXY(item.Key, item.Value);
            }

            
            for (int i = 0; i < serieBarra.Points.Count; i++)
            {
                switch (serieBarra.Points[i].AxisLabel)
                {
                    case "Pendiente":
                        serieBarra.Points[i].Color = Color.LightCoral;
                        serieDonut.Points[i].Color = Color.LightCoral;
                        break;
                    case "En curso":
                        serieBarra.Points[i].Color = Color.LightGoldenrodYellow;
                        serieDonut.Points[i].Color = Color.LightGoldenrodYellow;
                        break;
                    case "Completado":
                        serieBarra.Points[i].Color = Color.LightGreen;
                        serieDonut.Points[i].Color = Color.LightGreen;
                        break;
                    default:
                        serieBarra.Points[i].Color = Color.Gray;
                        serieDonut.Points[i].Color = Color.Gray;
                        break;
                }
            }


            
            datosTareas.Series.Add(serieBarra);
            datosTareas.Series.Add(serieDonut);
        }
    }
}
