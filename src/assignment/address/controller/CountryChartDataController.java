package assignment.address.controller;

import assignment.address.model.AVLTree;
import assignment.address.model.CountryChartData;
import javafx.scene.chart.XYChart;

/** 
 * Controller class to control the generation of the chart data relating to the data  in the {@link AVLTree}.
 * @author Daniel Byers | 13121312
 */
public class CountryChartDataController extends Controller
{
    final private CountryChartData chartData = new CountryChartData();
    
    /**
     * Accessor for method {@link CountryChartData#GetChartData() GetChartData}.
     * @return {@link XYChart} data relating to the {@link AVLTree}
     */
    public XYChart.Series<String, Double> getChartData ()
    {
        chartData.setAvlTree(mainApp.getAvlTree());
        return chartData.GetChartData();
    }
}
