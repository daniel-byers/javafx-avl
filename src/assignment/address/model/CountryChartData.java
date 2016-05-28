package assignment.address.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 *@author Daniel Byers | 13121312
 */
public class CountryChartData
{
    private ObservableList<XYChart.Series<String, Double>> chartData;
    private XYChart.Series<String, Double> values = new Series<>();
    private AVLTree<Country> avlTree;
    private Node<Country> root;

    /**
     * Sets the {@link AVLTree} that is to be used to generate data
     * @param avlTree Passed in to generate data.
     */ 
    public void setAvlTree (AVLTree<Country> avlTree)
    {
        this.avlTree = avlTree;
        root = avlTree.getRoot();
    }
    
    /**
     * Initialiser.
     */
    public CountryChartData ()
    {
        chartData = FXCollections.observableArrayList();
    }
    
    /**
     * Public accessor to get the data for the chart.
     * @return data for the chart.
     */
    public XYChart.Series<String, Double> GetChartData ()
    {
        generateChartData(root);
        chartData.add(values);
        return values;
    }
    
    /**
     * Method to generate chart data from the {@link AVLTree}.
     * Traverses the {@link AVLTree} and produces data that can be displayed in an 
     * X, Y bar chart.
     * @param tree 
     */
    private void generateChartData (Node<Country> tree)
    {
        if (tree != null)
        {
            values.getData().add(new XYChart.Data<>(tree.getData().getName(), tree.getData().getTradePotential()));
            generateChartData(tree.Left);
            generateChartData(tree.Right);
        }
    }
}
