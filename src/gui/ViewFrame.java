package com.sierox.genetic.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sierox.genetic.Main;
import com.sierox.genetic.algorithm.Algorithm;
import com.sierox.genetic.algorithm.DnaCreator;
import com.sierox.genetic.model.Individual;

public class ViewFrame extends JFrame{

	private static final long serialVersionUID = 2281911085883546069L;
	
	Container contentPane;
	
	JMenuBar menuBar;
	JMenu menuSettings;
	JMenuItem menuItemReset;
	JMenuItem menuItemSetTarget;
	JMenuItem menuItemSetGeneImportance;
	JMenuItem menuItemSetGridSize;
	
	JPanel panelOfInfo;
	JPanel panelOfColors;
	JPanel panelOfControls;

	JLabel labelGeneration;
	JLabel labelIsFound;
	JLabel labelPopulation;
	JLabel labelGeneImportance;
	JPanel panelTarget;
	JPanel panelTargetColor;
	JLabel labelTarget;
	JButton buttonSimulate;
	JTextField increment;
	
	public ViewFrame() {
		
		/* Menu */
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);{
			menuSettings = new JMenu("Settings");
			menuBar.add(menuSettings);{
				menuItemReset = new JMenuItem("Reset");
				menuSettings.add(menuItemReset);
				menuItemReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Algorithm.reset();
						Main.reset();
					}
				});
				
				menuItemSetTarget = new JMenuItem("Set Target DNA");
				menuSettings.add(menuItemSetTarget);
				menuItemSetTarget.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int[] setGenes = new RGBInputBox().getRGB();
						boolean correct = true;
						for (int i = 0; i < setGenes.length; i++) {
							if(!(setGenes[i]>=0 && setGenes[i]<=255)){
								correct = false;
							}
						}
						if(correct){
							Main.TARGET_DNA = DnaCreator.dnaFromPhenotype(setGenes);
							panelTargetColor.setBackground(new Individual(Main.TARGET_DNA).getDnaPhenotype(8));
						} else {
							JOptionPane.showMessageDialog(null, "Each color value should be between 0 and 255");
						}
					}
				});
				
				menuItemSetGeneImportance = new JMenuItem("Set Gene Importance");
				menuSettings.add(menuItemSetGeneImportance);
				menuItemSetGeneImportance.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int setSurvivorCount = Integer.parseInt(
								JOptionPane.showInputDialog(null, "Gene Importance (0-5):"));
						if(setSurvivorCount>=0 && setSurvivorCount<=5){
							Main.GENE_FACTOR = setSurvivorCount;
							labelGeneImportance.setText("Gene Importance: " + Main.GENE_FACTOR);
						} else {
							JOptionPane.showMessageDialog(null, "Gene Importance must be between 0 and 5!");
						}
					}
				});
				
				menuItemSetGridSize = new JMenuItem("Set Grid Size (Reset)");
				menuSettings.add(menuItemSetGridSize);
				menuItemSetGridSize.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int setGridSize = Integer.parseInt(
								JOptionPane.showInputDialog(null, "Length of 1 side:"));
						if(setGridSize>1 && setGridSize<=100){
							Main.POPULATION_SIZE = setGridSize*setGridSize;
							Algorithm.reset();
							Main.reset();
						} else {
							JOptionPane.showMessageDialog(null, "Grid size must be between 2 and 100!");
						}
					}
				});
			}
		}
		
		/* Content pane */
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout()); {

			/* Information panel */
			panelOfInfo = new JPanel();
			panelOfInfo.setLayout(new GridLayout(1, 5)); {
				labelGeneration = new JLabel(" Generation #0");
				labelIsFound = new JLabel("Found: false");
				labelPopulation = new JLabel("Population: " + Main.POPULATION_SIZE);
				labelGeneImportance = new JLabel("Gene Importance: " + Main.GENE_FACTOR);
				panelTarget = new JPanel();
				panelTarget = new JPanel(new GridLayout(1, 2));{
					labelTarget = new JLabel("Target: ");
					panelTargetColor = new JPanel();
					panelTargetColor.setBackground(new Individual(Main.TARGET_DNA).getDnaPhenotype(8));
					panelTarget.add(labelTarget);
					panelTarget.add(panelTargetColor);
				}
				
				panelOfInfo.add(labelPopulation);
				panelOfInfo.add(labelGeneImportance);
				panelOfInfo.add(labelGeneration);
				panelOfInfo.add(labelIsFound);
				panelOfInfo.add(panelTarget);
				contentPane.add(panelOfInfo, BorderLayout.PAGE_START);
				
			}

			/* Color panel */
			panelOfColors = new JPanel();
			panelOfColors.setLayout(new GridLayout((int)Math.sqrt((double)Main.POPULATION_SIZE),(int) Math.sqrt((double)Main.POPULATION_SIZE)));{
				contentPane.add(panelOfColors, BorderLayout.CENTER);
			}
		
			/* Control panel */
			panelOfControls = new JPanel();
			panelOfControls.setLayout(new GridLayout(1, 2));{
				increment = new JTextField();
				buttonSimulate = new JButton("Simulate Generation");
				buttonSimulate.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(!Algorithm.found)
							Algorithm.simulateSteps(Main.population, Integer.parseInt(increment.getText()));
					}
				});
				panelOfControls.add(increment);
				panelOfControls.add(buttonSimulate);
				contentPane.add(panelOfControls, BorderLayout.PAGE_END);
			}
		}
		setTitle("Genetic Algorithm with Colors");
		setSize(600, 700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void fill(Color[] colors) {
		panelOfColors.removeAll();
		for (int i = 0; i < colors.length; i++) {
			Component colorPanel;
			if(Algorithm.found && i == Algorithm.foundNumber){
				colorPanel = new JTextField();
				colorPanel.setFocusable(false);
				colorPanel.setCursor(Cursor.getDefaultCursor());
				labelIsFound.setText("Found: true");
			}
			else {
				colorPanel = new JPanel();
			}
			colorPanel.setBackground(colors[i]);
			panelOfColors.add(colorPanel);
			labelGeneration.setText(" Generation #" + Main.population.getGenerationNumber());
		}
		this.setVisible(true);
		this.paint(this.getGraphics());
	}
}
